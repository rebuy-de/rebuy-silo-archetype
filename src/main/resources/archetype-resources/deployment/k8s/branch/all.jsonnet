local vars = std.extVar('rebuyTemplateVariables');

local presets = {
  production: {
    replicas: 2,
    liveTraffic: true,
    resources: {
      limits: { cpu: '1000m', memory: '768Mi' },
      requests: { cpu: '100m', memory: '768Mi' },
    },
  },
  testing: {
    replicas: 1,
    liveTraffic: false,
    resources: {
      limits: { cpu: '1000m', memory: '768Mi' },
      requests: { cpu: '100m', memory: '768Mi' },
    },
  },
  staging: {
    replicas: 2,
    liveTraffic: true,
    resources: {
      limits: { cpu: '1000m', memory: '768Mi' },
      requests: { cpu: '100m', memory: '768Mi' },
    },
  },
};

local preset = presets[vars.presetName];

local metadata = {
  name: vars.applicationName,
  labels: {
    name: vars.applicationName,
    'app.kubernetes.io/name': vars.projectName,
    'app.kubernetes.io/version': vars.release,
    'rebuy.com/release': vars.release,
    'rebuy.com/preset': vars.presetName,
    'rebuy.com/live-traffic': if preset.liveTraffic then 'enabled' else 'disabled',
    'rebuy.com/toolstack': 'jvm.spring-boot.v2.6',
  },
};

local selector = {
  name: vars.applicationName,
};

{
  apiVersion: 'v1',
  kind: 'List',
  items: [
    {
      apiVersion: 'apps/v1',
      kind: 'Deployment',
      metadata: metadata,

      spec: {
        revisionHistoryLimit: 5,
        selector: { matchLabels: selector },
        replicas: preset.replicas,

        strategy: {
          rollingUpdate: {
            maxUnavailable: 0,
          },
        },

        template: {
          metadata: metadata {
            annotations: {
              'prometheus.io/scrape': 'true',
              'prometheus.io/port': '8080',
              'prometheus.io/path': '/actuator/prometheus',
              'sidecar.istio.io/inject': 'true',
            },
          },
          spec: {
            terminationGracePeriodSeconds: 120,
            serviceAccountName: vars.projectName,
            topologySpreadConstraints: [
              {
                maxSkew: 1,
                topologyKey: 'topology.kubernetes.io/zone',
                whenUnsatisfiable: 'ScheduleAnyway',
                labelSelector: { matchLabels: selector },
              },
            ],
            containers: [
              {
                name: 'silo',
                image: std.format('074509403805.dkr.ecr.eu-west-1.amazonaws.com/%s:%s', [vars.projectName, vars.revision]),
                imagePullPolicy: 'Always',
                env: [
                  {
                    name: 'SILO_PROFILE',
                    value: std.join(',', ['kubernetes', vars.clusterProfile]),
                  },
                  {
                    name: 'SILO_JAVA_ARGS',
                    value: std.join(' ', [
                      '-XX:MaxRAMPercentage=70',
                      '-XX:+UseG1GC',
                    ]),
                  },
                ],
                ports: [{
                  containerPort: 8080,
                }],
                resources: preset.resources,
                startupProbe: {
                  httpGet: {
                    path: '/actuator/health',
                    port: 8080,
                  },
                  failureThreshold: 60,
                },
                readinessProbe: {
                  httpGet: {
                    path: '/actuator/health/readiness',
                    port: 8080,
                  },
                  initialDelaySeconds: 15,
                },
                livenessProbe: {
                  httpGet: {
                    path: '/actuator/health/liveness',
                    port: 8080,
                  },
                  initialDelaySeconds: 300,
                },
              },
            ],
          },
        },
      },
    },
    {
      apiVersion: 'v1',
      kind: 'Service',
      metadata: metadata,
      spec: {
        selector: selector,
        ports: [{
          port: 80,
          targetPort: 8080,
          name: 'http',
        }],
      },
    },
    {
      apiVersion: 'networking.k8s.io/v1',
      kind: 'Ingress',
      metadata: metadata {
        annotations: {
          'traefik.ingress.kubernetes.io/router.middlewares': 'default-ssl@kubernetescrd',
        },
      },

      spec: {
        rules: [{
          host: std.format('%s.%s.rebuy.cloud', [vars.applicationName, vars.clusterName]),
          http: {
            paths: [{
              path: '/',
              pathType: 'Prefix',
              backend: {
                service: {
                  name: vars.applicationName,
                  port: { number: 80 },
                },
              },
            }],
          },
        }],
      },
    },
  ],
}
