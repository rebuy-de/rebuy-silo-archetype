#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.vault.authentication.ClientAuthentication;
import org.springframework.vault.authentication.KubernetesAuthentication;
import org.springframework.vault.authentication.KubernetesAuthenticationOptions;
import org.springframework.vault.authentication.KubernetesServiceAccountTokenFile;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.config.AbstractVaultConfiguration;

import java.net.URI;

import static java.util.Objects.requireNonNull;

@Profile("kubernetes")
@Configuration
public class VaultProductionConfiguration extends AbstractVaultConfiguration
{
    @Override
    public VaultEndpoint vaultEndpoint()
    {
        return VaultEndpoint.from(URI.create("http://vault-mtls.default.svc.cluster.local"));
    }

    @Override
    public ClientAuthentication clientAuthentication()
    {
        final var applicationName = requireNonNull(getEnvironment().getProperty("spring.application.name"));
        final var tokenFile = new KubernetesServiceAccountTokenFile();

        final var builder = KubernetesAuthenticationOptions.builder()
            .role(applicationName)
            .jwtSupplier(tokenFile);

        return new KubernetesAuthentication(builder.build(), restOperations());
    }
}
