#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )
package ${package}.${artifactId}.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rebuy.library.security.builder.OpaqueTokenIntrospectorBuilder;
import com.rebuy.library.security.cache.OpaqueTokenIntrospectorCache;
import com.rebuy.library.security.client.PermissionClient;
import com.rebuy.library.security.client.PermissionClientConfig;
import ${package}.${artifactId}.configuration.settings.OpaqueTokenIntrospectorSettings;
import ${package}.${artifactId}.configuration.settings.PermissionClientSettings;
import io.prometheus.client.guava.cache.CacheMetricsCollector;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;

import java.util.concurrent.TimeUnit;

@Configuration
@Profile("!testing")
public class ClientConfiguration
{
    @Bean
    public OpaqueTokenIntrospector opaqueTokenIntrospector(
        OpaqueTokenIntrospectorSettings settings,
        CacheMetricsCollector cacheMetricsCollector
    )
    {
        OpaqueTokenIntrospector opaqueTokenIntrospector = new OpaqueTokenIntrospectorBuilder()
            .clientId(settings.getClientId())
            .clientSecret(settings.getSecret())
            .host(settings.getEndpoint())
            .build();

        return new OpaqueTokenIntrospectorCache(
            opaqueTokenIntrospector,
            settings.getCacheDuration(),
            settings.getCacheTimeUnit(),
            settings.getCacheSize(),
            cacheMetricsCollector
        );
    }

    @Bean
    public PermissionClient permissionClient(
        PermissionClientSettings permissionClientSettings,
        ObjectMapper objectMapper
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

        return new PermissionClient(config, client, objectMapper);
    }
}
