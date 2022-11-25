#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )
package ${package}.${artifactId}.demos;

import ${package}.${artifactId}.BaseIntegrationTest;
import ${package}.${artifactId}.configuration.ClientTestConfiguration;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ExampleControllerIntegrationTest extends BaseIntegrationTest
{
    @Test
    public void authorization_should_work() throws Exception
    {
        mockMvc
            .perform(get("/examples").with(authProvider.asSystem()))
            .andExpect(status().isOk())
            .andExpect(header().string("Content-Type", "application/json"))
            .andExpect(jsonPath("id", is(1)));
    }
}
