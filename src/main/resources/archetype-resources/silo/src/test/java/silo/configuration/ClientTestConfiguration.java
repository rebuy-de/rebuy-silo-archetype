#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )
package ${package}.${artifactId}.configuration;

import com.rebuy.consul.ConsulService;
import com.rebuy.library.security.client.PermissionClient;
import com.rebuy.library.security.client.TokenDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

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
        PermissionClient permissionClient = mock(PermissionClient.class);

        TokenDto tokenDto = new TokenDto();
        tokenDto.accessToken = UUID.randomUUID();
        tokenDto.expiresIn = TimeUnit.MINUTES.toMillis(5);
        when(permissionClient.createToken()).thenReturn(tokenDto);

        return permissionClient;
    }

    @Bean
    public ConsulService consulService()
    {
        return mock(ConsulService.class);
    }
}
