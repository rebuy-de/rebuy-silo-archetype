#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )
package ${package}.${artifactId}.configuration.settings;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("clients.permissionClient")
public class PermissionClientSettings
{
    public String clientId;

    public String secret;

    public String host;

    public int port;

    public long keepAliveDurationMs;

    public void setClientId(String clientId)
    {
        this.clientId = clientId;
    }

    public void setSecret(String secret)
    {
        this.secret = secret;
    }

    public void setHost(String host)
    {
        this.host = host;
    }

    public void setPort(int port)
    {
        this.port = port;
    }

    public void setKeepAliveDurationMs(long keepAliveDurationMs)
    {
        this.keepAliveDurationMs = keepAliveDurationMs;
    }
}
