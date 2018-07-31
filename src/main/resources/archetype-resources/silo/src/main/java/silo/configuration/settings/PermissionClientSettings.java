#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )
package ${package}.${artifactId}.configuration.settings;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("clients.permission-client")
public class PermissionClientSettings
{
    private String clientId;

    private String secret;

    private String host;

    private int port;

    private long keepAliveDurationMs;

    public String getClientId()
    {
        return clientId;
    }

    public void setClientId(String clientId)
    {
        this.clientId = clientId;
    }

    public String getSecret()
    {
        return secret;
    }

    public void setSecret(String secret)
    {
        this.secret = secret;
    }

    public String getHost()
    {
        return host;
    }

    public void setHost(String host)
    {
        this.host = host;
    }

    public int getPort()
    {
        return port;
    }

    public void setPort(int port)
    {
        this.port = port;
    }

    public long getKeepAliveDurationMs()
    {
        return keepAliveDurationMs;
    }

    public void setKeepAliveDurationMs(long keepAliveDurationMs)
    {
        this.keepAliveDurationMs = keepAliveDurationMs;
    }
}
