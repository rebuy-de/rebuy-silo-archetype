#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.time.Clock;

@Configuration
public class DefaultConfiguration
{
    @Bean
    public ExpressionParser expressionParser()
    {
        return new SpelExpressionParser();
    }

    @Bean
    public Clock clock()
    {
        return Clock.systemDefaultZone();
    }
}
