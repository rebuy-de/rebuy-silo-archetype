#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )
package ${package}.${artifactId}.domain.service;

import com.rebuy.consul.ConsulService;
import ${package}.${artifactId}.configuration.settings.ConsulSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;

@Service
@Profile("!testing")
public class ConsulRegistrationService implements ApplicationListener<EmbeddedServletContainerInitializedEvent>
{
    @Autowired
    private ConsulSettings consulSettings;

    private ConsulService consulService;

    @Override
    public void onApplicationEvent(EmbeddedServletContainerInitializedEvent event)
    {
        consulService = ConsulService.builder()
            .agent(consulSettings.agent)
            .name(consulSettings.name)
            .port(event.getEmbeddedServletContainer().getPort())
            .tag("silo")
            .tag("vhost")
            .build();
        consulService.register();
    }

    @PreDestroy
    private void unregister()
    {
        if (consulService != null) {
            consulService.unregister();
        }
    }
}
