#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.configuration;

import io.prometheus.client.guava.cache.CacheMetricsCollector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class MetricReportingConfiguration
{
    @Bean
    public CacheMetricsCollector cacheMetricsCollector()
    {
        return new CacheMetricsCollector().register();
    }

    @PostConstruct
    public void init()
    {
        DefaultExports.initialize();
    }
}
