#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )
package ${package}.${artifactId}.configuration;

import com.rebuy.library.security.test.AuthBuilder;
import com.rebuy.library.security.test.AuthProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;

@Configuration
@Profile("testing")
public class AuthProviderConfiguration
{
    @Bean
    public AuthProvider authProvider(OpaqueTokenIntrospector opaqueTokenIntrospector)
    {
        return new AuthProvider(new AuthBuilder(), opaqueTokenIntrospector);
    }
}
