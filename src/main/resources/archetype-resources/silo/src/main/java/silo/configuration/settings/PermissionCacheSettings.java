#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )
package ${package}.${artifactId}.configuration.settings;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("permissions.cache")
public class PermissionCacheSettings
{
    private int duration;

    private char timeUnit;

    public int getDuration()
    {
        return duration;
    }

    public void setDuration(int duration)
    {
        this.duration = duration;
    }

    public char getTimeUnit()
    {
        return timeUnit;
    }

    public void setTimeUnit(char timeUnit)
    {
        this.timeUnit = timeUnit;
    }
}
