#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )
package ${package}.${artifactId}.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rebuy.consul.ConsulService;
import com.rebuy.consul.ConsulServiceBuilder;
import com.rebuy.library.security.client.PermissionClient;
import com.rebuy.library.security.client.PermissionClientConfig;
import com.rebuy.library.security.builder.RemoteTokenServicesBuilder;
import ${package}.${artifactId}.configuration.settings.PermissionClientSettings;
import ${package}.${artifactId}.configuration.settings.RemoteTokenServicesSettings;
import ${package}.${artifactId}.configuration.settings.ConsulSettings;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import java.util.concurrent.TimeUnit;

@Configuration
@Profile("!testing")
public class ClientConfiguration
{
    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public RemoteTokenServices remoteTokenServices(RemoteTokenServicesSettings remoteTokenServicesSettings)
    {
        return new RemoteTokenServicesBuilder()
            .setClientId(remoteTokenServicesSettings.getClientId())
            .setClientSecret(remoteTokenServicesSettings.getSecret())
            .setHost(remoteTokenServicesSettings.getEndpoint())
            .build();
    }

    @Bean
    public PermissionClient permissionClient(PermissionClientSettings permissionClientSettings)
    {
        PermissionClientConfig config = new PermissionClientConfig();
        config.clientId = permissionClientSettings.getClientId();
        config.clientSecret = permissionClientSettings.getSecret();
        config.host = permissionClientSettings.getHost();
        config.port = permissionClientSettings.getPort();

        config.scheme = "http://";

        OkHttpClient client = new OkHttpClient.Builder()
            .connectionPool(new ConnectionPool(2, permissionClientSettings.getKeepAliveDurationMs(), TimeUnit.MILLISECONDS))
            .build();

        return new PermissionClient(config, client, objectMapper);
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
