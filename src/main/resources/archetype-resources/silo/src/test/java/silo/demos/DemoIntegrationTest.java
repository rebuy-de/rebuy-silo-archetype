#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId}.demos;


import ${package}.${artifactId}.Application;
import ${package}.${artifactId}.annotation.IntegrationTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@Category(IntegrationTest.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest(
    value = {"spring.profiles.active=vagrant", "spring.jpa.hibernate.ddl-auto=none"}
)
public class DemoIntegrationTest
{
    @Test
    public void maven_should_run_this_test()
    {
        assertTrue(true);
    }
}
