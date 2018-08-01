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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Configuration
@Profile("testing")
public class ClientTestConfiguration
{
    public static final UUID ACCESS_TOKEN = UUID.randomUUID();

    @Bean
    public RemoteTokenServices remoteTokenServices()
    {
        return mock(RemoteTokenServices.class);
    }

    @Bean
    public PermissionClient permissionClient()
    {
        PermissionClient client = mock(PermissionClient.class);

        TokenDto tokenDto = new TokenDto();
        tokenDto.accessToken = ACCESS_TOKEN;
        tokenDto.expiresIn = TimeUnit.HOURS.toSeconds(1);

        when(client.createToken()).thenReturn(tokenDto);

        return client;
    }

    @Bean
    public ConsulService consulService()
    {
        return mock(ConsulService.class);
    }
}
