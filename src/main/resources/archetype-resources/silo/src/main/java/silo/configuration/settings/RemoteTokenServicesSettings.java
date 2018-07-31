#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )
package ${package}.${artifactId}.configuration.settings;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("remote-token-services")
public class RemoteTokenServicesSettings
{
    private String clientId;

    private String secret;

    private String endpoint;

    private int cacheDuration;

    private char cacheTimeUnit;

    private int cacheSize;

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

    public String getEndpoint()
    {
        return endpoint;
    }

    public void setEndpoint(String endpoint)
    {
        this.endpoint = endpoint;
    }

    public int getCacheDuration()
    {
        return cacheDuration;
    }

    public void setCacheDuration(int cacheDuration)
    {
        this.cacheDuration = cacheDuration;
    }

    public char getCacheTimeUnit()
    {
        return cacheTimeUnit;
    }

    public void setCacheTimeUnit(char cacheTimeUnit)
    {
        this.cacheTimeUnit = cacheTimeUnit;
    }

    public int getCacheSize()
    {
        return cacheSize;
    }

    public void setCacheSize(int cacheSize)
    {
        this.cacheSize = cacheSize;
    }
}
