#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )

package ${package}.${artifactId}.configuration.settings;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("consul")
public class ConsulSettings
{
    public String name;

    public String agent;

    public int siloPort;

    public void setName(String name)
    {
        this.name = name;
    }

    public void setAgent(String agent)
    {
        this.agent = agent;
    }

    public void setSiloPort(int siloPort) {
        this.siloPort = siloPort;
    }
}
