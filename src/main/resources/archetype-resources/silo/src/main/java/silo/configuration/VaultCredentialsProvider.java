#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.vault.annotation.VaultPropertySource;

@Profile("kubernetes")
@Configuration
@VaultPropertySource(
    value = "terraform/${spring.application.name}",
    renewal = VaultPropertySource.Renewal.OFF
)
@RequiredArgsConstructor
public class VaultCredentialsProvider
{
    private final Environment env;
}
