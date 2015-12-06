#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )
package ${package}.${artifactId}.configuration.settings;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@ConfigurationProperties("clients.common")
public class CommonClientSettings
{
    public UUID oauthToken;

    public int readTimeout;

    public int connectTimeout;

    public void setReadTimeout(int readTimeout)
    {
        this.readTimeout = readTimeout;
    }

    public void setConnectTimeout(int connectTimeout)
    {
        this.connectTimeout = connectTimeout;
    }

    public void setOauthToken(UUID oauthToken)
    {
        this.oauthToken = oauthToken;
    }
}
