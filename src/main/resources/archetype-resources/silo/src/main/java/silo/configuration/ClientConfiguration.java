#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )
package ${package}.${artifactId}.configuration;

import com.rebuy.sdk.customer.CustomerClient;
import ${package}.${artifactId}.configuration.settings.CommonClientSettings;
import ${package}.${artifactId}.configuration.settings.CustomerClientSettings;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!testing")
public class ClientConfiguration
{
    @Bean
    public CustomerClient customerClient(
        CustomerClientSettings clientSettings, CommonClientSettings commonClientSettings
    )
    {
        ClientConfig config = getClientConfig(commonClientSettings);

        CustomerClient customerClient = new CustomerClient(clientSettings.host, config);
        customerClient.setOAuthToken(commonClientSettings.oauthToken);

        return customerClient;
    }

    private ClientConfig getClientConfig(CommonClientSettings commonClientSettings)
    {
        ClientConfig config = new ClientConfig();

        config.property(ClientProperties.READ_TIMEOUT, commonClientSettings.readTimeout);
        config.property(ClientProperties.CONNECT_TIMEOUT, commonClientSettings.connectTimeout);
        return config;
    }
}
