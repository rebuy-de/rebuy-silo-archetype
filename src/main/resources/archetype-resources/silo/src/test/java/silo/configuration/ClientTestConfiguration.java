#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )
package ${package}.${artifactId}.configuration;

import com.rebuy.consul.ConsulService;
import com.rebuy.library.security.client.PermissionClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

import static org.mockito.Mockito.*;

@Configuration
@Profile("testing")
public class ClientTestConfiguration
{
    @Bean
    public RemoteTokenServices remoteTokenServices()
    {
        return mock(RemoteTokenServices.class);
    }

    @Bean
    public PermissionClient permissionClient()
    {
        return mock(PermissionClient.class);
    }

    @Bean
    public ConsulService consulService()
    {
        return mock(ConsulService.class);
    }
}
