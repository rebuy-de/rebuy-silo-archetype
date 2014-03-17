#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )
package ${package}.${artifactId}.configuration;

import com.rebuy.library.messaging.configuration.MessagingInboundConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"local", "vagrant", "sandbox", "production"})
public class LocalInboundMessagingConfiguration extends MessagingInboundConfiguration
{
    @Override
    public String queuePrefix()
    {
        return "your-silo";
    }
}
