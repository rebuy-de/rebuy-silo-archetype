#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.demos;

import ${package}.${artifactId}.BaseIntegrationTest;
import ${package}.${artifactId}.Application;
import ${package}.${artifactId}.annotation.IntegrationTest;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class DemoIntegrationTest extends BaseIntegrationTest
{
    @Test
    public void maven_should_run_this_test()
    {
        assertTrue(true);
    }
}
