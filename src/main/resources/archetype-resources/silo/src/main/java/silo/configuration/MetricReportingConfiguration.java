#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.configuration;

import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.metrics.prometheus.PrometheusMetricsTrackerFactory;
import io.prometheus.client.hotspot.DefaultExports;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Configuration
public class MetricReportingConfiguration
{
    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void init()
    {
        DefaultExports.initialize();

        if (dataSource instanceof HikariDataSource) {
            ((HikariDataSource) dataSource).setMetricsTrackerFactory(new PrometheusMetricsTrackerFactory());
        }
    }
}