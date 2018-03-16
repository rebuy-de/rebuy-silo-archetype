#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )
package ${package}.${artifactId}.configuration;

import com.rebuy.consul.ConsulService;
import com.rebuy.consul.ConsulServiceBuilder;
import com.rebuy.consul.listeners.ContextClosedEventListener;
import com.rebuy.consul.listeners.ContextStoppedEventListener;
import com.rebuy.consul.listeners.EmbeddedServletContainerInitializedEventListener;
import ${package}.${artifactId}.configuration.settings.ConsulSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!kubernetes")
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

    @Bean
    public ConsulService consulService(ConsulSettings consulSettings)
    {
        ConsulServiceBuilder consulServiceBuilder = new ConsulServiceBuilder();

        return consulServiceBuilder
            .agent(consulSettings.agent)
            .port(consulSettings.siloPort)
            .name(consulSettings.name)
            .tag("silo")
            .tag("vhost")
            .build();
    }
}
