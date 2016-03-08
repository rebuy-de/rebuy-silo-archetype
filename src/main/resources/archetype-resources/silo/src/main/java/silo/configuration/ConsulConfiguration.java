#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )
package ${package}.${artifactId}.configuration;

import com.rebuy.consul.ConsulService;
import com.rebuy.consul.listeners.ContextClosedEventListener;
import com.rebuy.consul.listeners.ContextStoppedEventListener;
import com.rebuy.consul.listeners.EmbeddedServletContainerInitializedEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsulConfiguration
{
    @Autowired
    private ConsulService consulService;

    @Bean
    public ContextClosedEventListener contextClosedEventListener()
    {
        return new ContextClosedEventListener(consulService);
    }

    @Bean
    public ContextStoppedEventListener contextStoppedEventListener()
    {
        return new ContextStoppedEventListener(consulService);
    }

    @Bean
    public EmbeddedServletContainerInitializedEventListener embeddedServletContainerInitializedEventListener()
    {
        return new EmbeddedServletContainerInitializedEventListener(consulService);
    }
}
