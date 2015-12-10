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
    private List<String> locations = new ArrayList<>();

    private List<String> schemas = new ArrayList<>();

    private String table;

    public void setLocations(List<String> locations)
    {
        this.locations = locations;
    }

    public void setSchemas(List<String> schemas)
    {
        this.schemas = schemas;
    }

    public void setTable(String table)
    {
        this.table = table;
    }

    public String[] getLocations()
    {
        return locations.toArray(new String[locations.size()]);
    }

    public String[] getSchemas()
    {
        return schemas.toArray(new String[locations.size()]);
    }

    public String getTable()
    {
        return table;
    }
}
