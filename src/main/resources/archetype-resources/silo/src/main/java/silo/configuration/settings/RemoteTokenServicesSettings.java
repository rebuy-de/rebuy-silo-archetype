#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )
package ${package}.${artifactId}.configuration.settings;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("remoteTokenServices")
public class RemoteTokenServicesSettings
{
    public String clientId;

    public String secret;

    public String endpoint;

    public void setClientId(String clientId)
    {
        this.clientId = clientId;
    }

    public void setSecret(String secret)
    {
        this.secret = secret;
    }

    public void setEndpoint(String endpoint)
    {
        this.endpoint = endpoint;
    }
}
