#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )
package ${package}.${artifactId}.configuration;

import com.rebuy.library.security.cache.TokenCache;
import com.rebuy.library.security.cache.TokenCacheLoader;
import com.rebuy.library.security.client.PermissionClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TokenCacheConfiguration
{
    @Bean
    public TokenCache tokenCache(PermissionClient permissionClient)
    {
        return new TokenCache(new TokenCacheLoader(permissionClient));
    }
}
