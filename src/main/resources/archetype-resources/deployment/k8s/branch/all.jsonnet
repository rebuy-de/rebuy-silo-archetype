local silo = import '@rebuy/silo/v1.libsonnet';

silo({
  replicas: 2,
  jvm: {
    ramPercentage: 70,
  },
  resources: {
    requests: { memory: '768Mi', cpu: '100m' },
    limits: { memory: '768Mi' },
  },
  staging: {
    replicas: 1,
    resources: {
      requests: { memory: '600Mi', cpu: '50m' },
      limits: { memory: '600Mi' },
    },
  },
})
