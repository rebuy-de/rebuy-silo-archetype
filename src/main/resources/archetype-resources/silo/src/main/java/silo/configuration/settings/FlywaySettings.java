#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )
package ${package}.${artifactId}.configuration.settings;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ConfigurationProperties("flyway")
public class FlywaySettings
{
    public List<String> locations = new ArrayList<>();

    public String baseLineOnMigrate;

    public void setLocations(List<String> locations)
    {
        this.locations = locations;
    }

    public String[] getLocations()
    {
        return locations.toArray(new String[locations.size()]);
    }

    public void setBaseLineOnMigrate(String baseLineOnMigrate)
    {
        this.baseLineOnMigrate = baseLineOnMigrate;
    }

    public Boolean getBaseLineOnMigrate()
    {
        return Boolean.valueOf(baseLineOnMigrate);
    }
}
