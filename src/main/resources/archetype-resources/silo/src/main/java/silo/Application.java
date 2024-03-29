#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )
package ${package}.${artifactId};

import com.rebuy.library.messaging.annotation.EnableMessaging;
import com.rebuy.library.objectmapper.annotation.EnableObjectMapper;
import com.rebuy.library.webcontextlogging.annotation.EnableWebContextLogging;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@SpringBootApplication
@EnableConfigurationProperties
@EnableMessaging
@EnableObjectMapper
@EnableWebContextLogging
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class Application
{
    public static void main(String[] args)
    {
        if (!StandardCharsets.UTF_8.equals(Charset.defaultCharset())) {
            throw new IllegalStateException("Default charset must be UTF-8");
        }

        new SpringApplicationBuilder()
            .bannerMode(Banner.Mode.OFF)
            .sources(Application.class)
            .run(args);
    }
}
