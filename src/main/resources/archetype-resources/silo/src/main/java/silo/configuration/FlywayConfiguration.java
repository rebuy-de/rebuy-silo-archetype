#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.configuration;

import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.flywaydb.core.internal.database.postgresql.PostgreSQLConfigurationExtension;
import org.springframework.boot.autoconfigure.flyway.FlywayConfigurationCustomizer;
import org.springframework.context.annotation.Configuration;

@Configuration
class FlywayConfiguration implements FlywayConfigurationCustomizer
{
    @Override
    public void customize(FluentConfiguration configuration)
    {
        /*
         * Disable the transactional lock in Flyway that breaks all non-transactional migrations since v9.1.2 of the plugin
         * See https://github.com/flyway/flyway/issues/3508
         */

        var pgExtension = configuration.getPluginRegister().getPlugin(PostgreSQLConfigurationExtension.class);
        pgExtension.setTransactionalLock(false);
    }
}
