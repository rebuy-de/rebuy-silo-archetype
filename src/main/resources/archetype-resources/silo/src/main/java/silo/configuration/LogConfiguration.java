#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )
package ${package}.${artifactId}.configuration;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Properties;

@Configuration
@PropertySource("classpath:${spring.profiles.active}/log4j.properties")
public class LogConfiguration {
    @Autowired
    private Environment env;

    private void configureLog4j() throws IOException {
        for (String profile : env.getActiveProfiles()) {
            ClassPathResource resource = new ClassPathResource(profile + "/log4j.properties");
            Properties properties = new Properties();
            properties.load(resource.getInputStream());
            PropertyConfigurator.configure(properties);
        }

        BasicConfigurator.configure();
    }

    @Bean
    public Logger logger(@Value("${logger.name}") String loggerName) throws IOException {
        configureLog4j();
        return LoggerFactory.getLogger(loggerName);
    }
}
