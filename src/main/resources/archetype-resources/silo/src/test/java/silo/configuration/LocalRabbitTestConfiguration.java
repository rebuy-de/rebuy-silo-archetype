#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )
package ${package}.${artifactId}.configuration;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Configuration
@Profile({"testing"})
public class LocalRabbitTestConfiguration
{
    @Bean
    public AmqpTemplate amqpTemplate()
    {
        RabbitTemplate template = mock(RabbitTemplate.class);
        when(template.getConnectionFactory()).thenReturn(connectionFactory());
        return template;
    }

    @Bean
    public AmqpAdmin amqpAdmin()
    {
        return mock(RabbitAdmin.class);
    }

    @Bean
    public Queue queue()
    {
        return mock(Queue.class);
    }

    @Bean
    public ConnectionFactory connectionFactory()
    {
        ConnectionFactory factory = mock(ConnectionFactory.class);
        when(factory.createConnection()).thenReturn(mock(Connection.class));
        return factory;
    }

    @Bean
    public Exchange exchange()
    {
        final Exchange mock = mock(Exchange.class);
        when(mock.getName()).thenReturn("rebuy");
        return mock;
    }
}
