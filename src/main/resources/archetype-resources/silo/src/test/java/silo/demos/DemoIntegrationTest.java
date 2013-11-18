#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.demos;


import ${package}.${artifactId}.annotation.IntegrationTest;
import ${package}.${artifactId}.configuration.WebConfig;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static junit.framework.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebConfig.class)
@WebAppConfiguration
@Category(IntegrationTest.class)
public class DemoIntegrationTest
{
    @Test
    public void maven_should_run_this_test()
    {
        assertTrue(true);
    }
}
