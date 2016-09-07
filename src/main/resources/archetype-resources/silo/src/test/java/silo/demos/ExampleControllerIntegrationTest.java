#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )
package ${package}.${artifactId}.demos;

import ${package}.${artifactId}.BaseIntegrationTest;
import ${package}.${artifactId}.annotation.IntegrationTest;
import ${package}.${artifactId}.configuration.ClientTestConfiguration;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.Assert.*;

@Category(IntegrationTest.class)
public class ExampleControllerIntegrationTest extends BaseIntegrationTest
{
    @Test
    public void authorization_should_work() throws Exception
    {
        final String clientName = "client_name";
        final String role = "ROLE_SYSTEM";
        final UUID systemToken = ClientTestConfiguration.ACCESS_TOKEN;

        OAuth2Authentication oauth = authBuilder.buildClient(systemToken, clientName, new String[]{role});
        when(remoteTokenServices.loadAuthentication(systemToken.toString())).thenReturn(oauth);

        mockMvc
            .perform(get("/examples").header("Authorization", "Bearer " + systemToken))
            .andExpect(status().isOk())
            .andExpect(header().string("Content-Type", "application/json"))
            .andExpect(jsonPath("id", is(1)));
    }
}
