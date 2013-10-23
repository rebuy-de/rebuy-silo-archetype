#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )
package ${package}.${artifactId}.configuration;

import com.rebuy.library.messaging.configuration.SynchronousMessagingConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocalSynchronousMessagingConfiguration extends SynchronousMessagingConfiguration
{
}
