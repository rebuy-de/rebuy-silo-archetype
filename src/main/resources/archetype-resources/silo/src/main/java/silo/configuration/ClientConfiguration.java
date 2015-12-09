#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )
package ${package}.${artifactId}.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rebuy.library.security.client.PermissionClient;
import com.rebuy.library.security.client.PermissionClientConfig;
import com.rebuy.library.security.service.RemoteTokenServicesBuilder;
import com.rebuy.sdk.customer.CustomerClient;
import ${package}.${artifactId}.configuration.settings.CommonClientSettings;
import ${package}.${artifactId}.configuration.settings.CustomerClientSettings;
import ${package}.${artifactId}.configuration.settings.RemoteTokenServicesSettings;
import com.squareup.okhttp.ConnectionPool;
import com.squareup.okhttp.OkHttpClient;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
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
    public CustomerClient customerClient(
        CustomerClientSettings clientSettings, CommonClientSettings commonClientSettings
    )
    {
        ClientConfig config = getClientConfig(commonClientSettings);

        CustomerClient customerClient = new CustomerClient(clientSettings.host, config);

        return customerClient;
    }

    private ClientConfig getClientConfig(CommonClientSettings commonClientSettings)
    {
        ClientConfig config = new ClientConfig();

        config.property(ClientProperties.READ_TIMEOUT, commonClientSettings.readTimeout);
        config.property(ClientProperties.CONNECT_TIMEOUT, commonClientSettings.connectTimeout);

        return config;
    }

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
}
