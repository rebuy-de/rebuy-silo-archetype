#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.configuration;

import com.rebuy.library.messaging.configuration.ConfirmHandlerConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocalConfirmationHandlerConfiguration extends ConfirmHandlerConfiguration
{
}
