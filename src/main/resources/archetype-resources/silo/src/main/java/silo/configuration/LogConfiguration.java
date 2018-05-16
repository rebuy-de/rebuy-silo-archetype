#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )
package ${package}.${artifactId}.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Configuration
public class LogConfiguration
{
    @Autowired
    private Environment env;

    @PostConstruct
    private void configureLog4j()
    {
        for (String profile : env.getActiveProfiles()) {
            LoggerContext context = (org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false);
            ClassPathResource resource = new ClassPathResource(profile + "/log4j2.xml");

            try {
                context.setConfigLocation(resource.getURI());

                return;
            } catch (IOException ignored) {
            }
        }

        Configurator.initialize("default", null);
    }
}
