#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.configuration;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.hotspot.BufferPoolsExports;
import io.prometheus.client.hotspot.ClassLoadingExports;
import io.prometheus.client.hotspot.GarbageCollectorExports;
import io.prometheus.client.hotspot.MemoryPoolsExports;
import io.prometheus.client.hotspot.MemoryAllocationExports;
import io.prometheus.client.hotspot.StandardExports;
import io.prometheus.client.hotspot.ThreadExports;
import io.prometheus.client.hotspot.VersionInfoExports;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class MetricReportingConfiguration
{
    private final CollectorRegistry collectorRegistry;

    @Autowired
    public MetricReportingConfiguration(CollectorRegistry collectorRegistry)
    {
        this.collectorRegistry = collectorRegistry;
    }

    @Bean
    public CacheMetricsCollector cacheMetricsCollector()
    {
        return new CacheMetricsCollector().register(collectorRegistry);
    }

    @PostConstruct
    public void init()
    {
        new StandardExports().register(collectorRegistry);
        new MemoryPoolsExports().register(collectorRegistry);
        new MemoryAllocationExports().register(collectorRegistry);
        new BufferPoolsExports().register(collectorRegistry);
        new GarbageCollectorExports().register(collectorRegistry);
        new ThreadExports().register(collectorRegistry);
        new ClassLoadingExports().register(collectorRegistry);
        new VersionInfoExports().register(collectorRegistry);
    }
}
