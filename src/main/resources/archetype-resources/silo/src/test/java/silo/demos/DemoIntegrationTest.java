#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.demos;

import ${package}.${artifactId}.BaseIntegrationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DemoIntegrationTest extends BaseIntegrationTest
{
    @Test
    public void maven_should_run_this_test()
    {
        Assertions.assertTrue(true);
    }
}
