#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )
package ${package}.${artifactId}.configuration;

import com.rebuy.library.messaging.configuration.MessageDispatcherConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!testing")
public class LocalMessageDispatcherConfiguration extends MessageDispatcherConfiguration
{
}
