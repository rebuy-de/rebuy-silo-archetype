#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )
package ${package}.${artifactId}.configuration;

import com.rebuy.library.messaging.bus.TransactionAwareBus;
import com.rebuy.library.messaging.configuration.MessagingOutboundConfiguration;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Configuration
public class LocalMessagingOutboundConfiguration extends MessagingOutboundConfiguration
{
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public TransactionAwareBus prototypedTransactionAwareBus()
    {
        return new TransactionAwareBus();
    }
}
