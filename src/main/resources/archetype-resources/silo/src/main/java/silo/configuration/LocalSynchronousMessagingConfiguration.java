#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )
package ${package}.${artifactId}.configuration;

import com.rebuy.library.messaging.configuration.SynchronousMessagingConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!testing")
public class LocalSynchronousMessagingConfiguration extends SynchronousMessagingConfiguration
{
}
