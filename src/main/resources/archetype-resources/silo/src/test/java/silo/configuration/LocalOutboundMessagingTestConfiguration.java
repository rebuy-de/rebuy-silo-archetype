#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )
package ${package}.${artifactId}.configuration;

import com.rebuy.library.messaging.bus.AsynchronousBus;
import com.rebuy.library.messaging.bus.SynchronousBus;
import com.rebuy.library.messaging.bus.TransactionAwareBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static org.mockito.Mockito.*;

@Configuration
@Profile("testing")
public class LocalOutboundMessagingTestConfiguration
{
    @Bean
    public TransactionAwareBus transactionAwareBus()
    {
        return mock(TransactionAwareBus.class);
    }

    @Bean
    public TransactionAwareBus prototypedTransactionAwareBus()
    {
        return mock(TransactionAwareBus.class);
    }

    @Bean
    public AsynchronousBus asynchronousBus()
    {
        return mock(AsynchronousBus.class);
    }

    @Bean
    public SynchronousBus synchronousBus()
    {
        return mock(SynchronousBus.class);
    }
}

