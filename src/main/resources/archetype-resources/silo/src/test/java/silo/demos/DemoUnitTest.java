#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )
package ${package}.${artifactId}.demos;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Assertions;

@ExtendWith(MockitoExtension.class)
public class DemoUnitTest
{
    @Test
    public void maven_should_run_this_test()
    {
        Assertions.assertTrue(true);
    }
}
