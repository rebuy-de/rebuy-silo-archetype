#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )
package ${package}.${artifactId}.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rebuy.library.security.client.PermissionClient;
import com.rebuy.library.security.client.PermissionClientConfig;
import com.rebuy.library.security.configuration.RemoteTokenServicesConfig;
import com.rebuy.consul.ConsulService;
import com.rebuy.consul.ConsulServiceBuilder;
import com.rebuy.library.security.service.RemoteTokenServicesBuilder;
import ${package}.${artifactId}.configuration.settings.PermissionClientSettings;
import ${package}.${artifactId}.configuration.settings.RemoteTokenServicesSettings;
import ${package}.${artifactId}.configuration.settings.ConsulSettings;
import com.squareup.okhttp.ConnectionPool;
import com.squareup.okhttp.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

@Configuration
@Profile("!testing")
public class ClientConfiguration
{
    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public RemoteTokenServices remoteTokenServices(RemoteTokenServicesSettings remoteTokenServicesSettings)
    {
        RemoteTokenServicesConfig config = new RemoteTokenServicesConfig(
            remoteTokenServicesSettings.clientId,
            remoteTokenServicesSettings.secret,
            remoteTokenServicesSettings.endpoint
        );

        return new RemoteTokenServicesBuilder().build(config);
    }

    @Bean
    public PermissionClient permissionClient(PermissionClientSettings permissionClientSettings)
    {
        PermissionClientConfig config = new PermissionClientConfig();
        config.clientId = permissionClientSettings.clientId;
        config.clientSecret = permissionClientSettings.secret;
        config.host = permissionClientSettings.host;
        config.port = permissionClientSettings.port;

        config.scheme = "http://";

        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectionPool(new ConnectionPool(2, permissionClientSettings.keepAliveDurationMs));

        return new PermissionClient(config, okHttpClient, objectMapper);
    }

    @Bean
    public ConsulService consulService(ConsulSettings consulSettings)
    {
        ConsulServiceBuilder consulServiceBuilder = new ConsulServiceBuilder();

        return consulServiceBuilder
            .agent(consulSettings.agent)
            .port(consulSettings.siloPort)
            .name(consulSettings.name)
            .tag("silo")
            .tag("vhost")
            .build();
    }
}
