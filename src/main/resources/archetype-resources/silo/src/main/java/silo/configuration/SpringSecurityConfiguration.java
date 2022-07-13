#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.configuration;

import com.rebuy.library.security.service.RebuyTokenAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter
{
    @Autowired
    private OpaqueTokenIntrospector opaqueTokenIntrospector;

    @Override
    public void configure(HttpSecurity http) throws Exception
    {
        final var providerManager = new ProviderManager(
            new RebuyTokenAuthenticationProvider(opaqueTokenIntrospector)
        );
        providerManager.setEraseCredentialsAfterAuthentication(false);

        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests()
            .anyRequest()
            .permitAll()
            .and()
            .oauth2ResourceServer()
            .opaqueToken().authenticationManager(providerManager);
    }
}
