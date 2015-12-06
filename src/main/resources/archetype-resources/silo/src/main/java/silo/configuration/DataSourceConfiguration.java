#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.configuration;

import ${package}.${artifactId}.configuration.settings.DataSourceSettings;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "${package}.${artifactId}.domain.persistence")
@Profile("!testing")
public class DataSourceConfiguration
{
    @Autowired
    private DataSourceSettings dataSourceSettings;

    @Bean
    public DataSource dataSource()
    {
        final HikariDataSource dataSource = new HikariDataSource();

        dataSource.setLeakDetectionThreshold(100000L);
        dataSource.setConnectionTimeout(5000);

        // TODO: set your desired data source class name
        dataSource.setDataSourceClassName();
        dataSource.addDataSourceProperty("url", dataSourceSettings.url);
        dataSource.addDataSourceProperty("user", dataSourceSettings.user);
        dataSource.addDataSourceProperty("password", dataSourceSettings.password);
        dataSource.setMaximumPoolSize(dataSourceSettings.poolSize);

        dataSource.addDataSourceProperty("cachePrepStmts", true);
        dataSource.addDataSourceProperty("prepStmtCacheSize", 300);
        dataSource.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
        dataSource.addDataSourceProperty("useServerPrepStmts", true);

        return dataSource;
    }
}
