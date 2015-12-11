#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.configuration;

import com.rebuy.library.security.cache.PermissionCache;
import com.rebuy.library.security.cache.PermissionCacheLoader;
import com.rebuy.library.security.cache.TokenCache;
import com.rebuy.library.security.cache.TokenCacheLoader;
import com.rebuy.library.security.client.PermissionClient;
import com.rebuy.library.security.service.AuthorizationService;
import com.rebuy.library.security.service.RebuyPermissionEvaluator;
import ${package}.${artifactId}.configuration.settings.PermissionCacheSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@EnableResourceServer
public class SecurityLibraryConfiguration extends ResourceServerConfigurerAdapter
{
    @Autowired
    private RemoteTokenServices remoteTokenServices;

    @Autowired
    private PermissionClient permissionClient;

    @Override
    public void configure(HttpSecurity http) throws Exception
    {
        http.csrf().disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
            .anyRequest()
            .permitAll();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception
    {
        resources.resourceId("${rootArtifactId}");

        resources.tokenServices(remoteTokenServices);
    }

    @Bean
    public TokenCache tokenCache()
    {
        return new TokenCache(new TokenCacheLoader(permissionClient));
    }

    @Bean
    public RebuyPermissionEvaluator rebuyPermissionEvaluator(
        PermissionCacheSettings permissionCacheSettings
    )
    {
        PermissionCacheLoader permissionCacheLoader = new PermissionCacheLoader(permissionClient, tokenCache());
        PermissionCache permissionCache = new PermissionCache(
            permissionCacheLoader, permissionCacheSettings.duration, permissionCacheSettings.timeunit
        );

        return new RebuyPermissionEvaluator(permissionCache);
    }

    @Bean
    public AuthorizationService authorizationService()
    {
        return new AuthorizationService(tokenCache(), remoteTokenServices);
    }
}
