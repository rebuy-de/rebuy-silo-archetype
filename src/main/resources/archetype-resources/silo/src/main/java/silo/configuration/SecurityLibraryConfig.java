#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.configuration;

import com.rebuy.library.security.configuration.DefaultSecurityLibraryConfig;
import com.rebuy.library.security.validators.PrincipalValidator;
import com.rebuy.library.security.validators.SystemAuthenticationValidator;
import com.rebuy.library.security.validators.UserAuthenticationValidator;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityLibraryConfig extends DefaultSecurityLibraryConfig
{
    @Bean
    public PrincipalValidator principalValidator()
    {
        return new PrincipalValidator();
    }

    @Bean
    public SystemAuthenticationValidator systemValidator()
    {
        return new SystemAuthenticationValidator(principalValidator());
    }

    @Bean
    public FilterRegistrationBean disableAutomaticRegistrationFilter(){
        FilterRegistrationBean filterRegBean = new FilterRegistrationBean();
        filterRegBean.setFilter(headerAuthorizationFilter());
        filterRegBean.setEnabled(false);

        return filterRegBean;
    }

    @Bean
    public UserAuthenticationValidator userValidator()
    {
        return new UserAuthenticationValidator(principalValidator());
    }
}
