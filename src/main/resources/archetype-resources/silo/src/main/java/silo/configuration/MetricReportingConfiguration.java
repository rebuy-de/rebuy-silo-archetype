#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.configuration;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.guava.cache.CacheMetricsCollector;
import io.prometheus.client.hotspot.DefaultExports;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
public class MetricReportingConfiguration
{
    @Bean
    public CacheMetricsCollector cacheMetricsCollector(CollectorRegistry collectorRegistry)
    {
        return new CacheMetricsCollector().register(collectorRegistry);
    }

    @PostConstruct
    public void init()
    {
        DefaultExports.initialize();
    }
}
