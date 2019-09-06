#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )
package ${package}.${artifactId}.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rebuy.library.security.builder.RemoteTokenServicesBuilder;
import com.rebuy.library.security.cache.RemoteTokenServicesCache;
import com.rebuy.library.security.client.PermissionClient;
import com.rebuy.library.security.client.PermissionClientConfig;
import ${package}.${artifactId}.configuration.settings.PermissionClientSettings;
import ${package}.${artifactId}.configuration.settings.RemoteTokenServicesSettings;
import io.opentracing.Tracer;
import io.prometheus.client.guava.cache.CacheMetricsCollector;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

import java.util.concurrent.TimeUnit;

@Configuration
@Profile("!testing")
public class ClientConfiguration
{
    @Bean
    public ResourceServerTokenServices resourceServerTokenServices(
        RemoteTokenServicesSettings remoteTokenServicesSettings,
        CacheMetricsCollector cacheMetricsCollector,
        Tracer tracer
    )
    {
        ResourceServerTokenServices remoteTokenService = new RemoteTokenServicesBuilder()
            .clientId(remoteTokenServicesSettings.getClientId())
            .clientSecret(remoteTokenServicesSettings.getSecret())
            .host(remoteTokenServicesSettings.getEndpoint())
            .tracer(tracer)
            .build();

        return new RemoteTokenServicesCache(
            remoteTokenService,
            remoteTokenServicesSettings.getCacheDuration(),
            remoteTokenServicesSettings.getCacheTimeUnit(),
            remoteTokenServicesSettings.getCacheSize(),
            cacheMetricsCollector
        );
    }

    @Bean
    public PermissionClient permissionClient(
        PermissionClientSettings permissionClientSettings,
        ObjectMapper objectMapper,
        Tracer tracer
    )
    {
        PermissionClientConfig config = new PermissionClientConfig();
        config.setClientId(permissionClientSettings.getClientId());
        config.setClientSecret(permissionClientSettings.getSecret());
        config.setHost(permissionClientSettings.getHost());
        config.setPort(permissionClientSettings.getPort());

        config.setScheme("http");

        OkHttpClient client = new OkHttpClient.Builder()
            .connectionPool(new ConnectionPool(2, permissionClientSettings.getKeepAliveDurationMs(), TimeUnit.MILLISECONDS))
            .build();

        return new PermissionClient(config, client, objectMapper, tracer);
    }
}
