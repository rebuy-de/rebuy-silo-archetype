#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )

package ${package}.${artifactId}.configuration.settings;

import com.rebuy.library.messaging.configuration.settings.MessagingSettings;
import org.springframework.stereotype.Component;

@Component
public class LocalMessagingSettings extends MessagingSettings
{

}
