#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.configuration;

import com.rebuy.library.security.cache.PermissionCache;
import com.rebuy.library.security.cache.PermissionCacheLoader;
import com.rebuy.library.security.cache.TokenCache;
import com.rebuy.library.security.client.PermissionClient;
import com.rebuy.library.security.service.AuthorizationService;
import com.rebuy.library.security.service.RebuyPermissionEvaluator;
import ${package}.${artifactId}.configuration.settings.PermissionCacheSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityLibraryConfiguration
{
    @Autowired
    private OpaqueTokenIntrospector opaqueTokenIntrospector;

    @Bean(name = "permissionEvaluator")
    public RebuyPermissionEvaluator rebuyPermissionEvaluator(PermissionCacheSettings permissionCacheSettings, TokenCache tokenCache, PermissionClient permissionClient)
    {

        PermissionCacheLoader permissionCacheLoader = new PermissionCacheLoader(permissionClient, tokenCache);
        PermissionCache permissionCache = new PermissionCache(
            permissionCacheLoader, permissionCacheSettings.getDuration(), permissionCacheSettings.getTimeUnit()
        );

        return new RebuyPermissionEvaluator(permissionCache);
    }

    @Bean
    public AuthorizationService authorizationService(TokenCache tokenCache)
    {
        return new AuthorizationService(tokenCache, opaqueTokenIntrospector);
    }
}
