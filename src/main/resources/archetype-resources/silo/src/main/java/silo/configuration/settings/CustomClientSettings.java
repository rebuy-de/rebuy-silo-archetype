#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )
package ${package}.${artifactId}.configuration.settings;

public abstract class CustomClientSettings
{
    public String host;

    public void setHost(String host)
    {
        this.host = host;
    }
}
