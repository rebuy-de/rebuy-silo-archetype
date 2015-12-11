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
    public int duration;

    public char timeunit;

    public void setDuration(int duration)
    {
        this.duration = duration;
    }

    public void setTimeunit(char timeunit)
    {
        this.timeunit = timeunit;
    }
}
