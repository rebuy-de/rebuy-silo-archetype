#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.configuration;

import ${package}.${artifactId}.configuration.settings.FlywaySettings;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class FlywayConfiguration
{
    @Autowired
    private DataSource dataSource;

    @Autowired
    private FlywaySettings flywaySettings;

    @Bean
    public Flyway flyway()
    {
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.setTable(flywaySettings.getTable());
        flyway.setSchemas(flywaySettings.getSchemas());
        flyway.setBaselineVersionAsString("0");
        flyway.setBaselineOnMigrate(true);
        flyway.setLocations(flywaySettings.getLocations());

        flyway.migrate();

        return flyway;
    }
}
