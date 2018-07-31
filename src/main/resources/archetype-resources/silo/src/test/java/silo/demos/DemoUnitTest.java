#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )
package ${package}.${artifactId}.demos;

import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.Test;

import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class DemoUnitTest
{
    @Test
    public void maven_should_run_this_test()
    {
        assertTrue(true);
    }
}
