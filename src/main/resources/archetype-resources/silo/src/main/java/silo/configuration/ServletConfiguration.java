#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.configuration;


import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.ServerConnector;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class ServletConfiguration implements EmbeddedServletContainerCustomizer
{
    @Override
    public void customize(ConfigurableEmbeddedServletContainer container)
    {
        JettyEmbeddedServletContainerFactory c = (JettyEmbeddedServletContainerFactory) container;
        c.addServerCustomizers(
            server -> Stream.of(server.getConnectors())
                .filter(connector -> connector instanceof ServerConnector)
                .map(connector -> (ServerConnector) connector)
                .forEach(
                    connector -> {
                        HttpConnectionFactory factoy = connector
                            .getConnectionFactory(HttpConnectionFactory.class);
                        factoy.getHttpConfiguration().setRequestHeaderSize(32768);
                    }
                )
        );
    }
}
