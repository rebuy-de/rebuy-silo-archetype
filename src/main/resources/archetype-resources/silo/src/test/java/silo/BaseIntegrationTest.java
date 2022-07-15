#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId};

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rebuy.library.messaging.annotation.MockMessaging;
import com.rebuy.library.security.test.AuthProvider;
import ${package}.${artifactId}.annotation.IntegrationTest;
import org.junit.Before;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.actuate.metrics.AutoConfigureMetrics;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@Category(IntegrationTest.class)
@SpringBootTest(
    classes = Application.class,
    properties = {"spring.profiles.active=testing"},
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@MockMessaging
@AutoConfigureMetrics
public abstract class BaseIntegrationTest
{
    @Autowired
    protected WebApplicationContext wac;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected FilterChainProxy filterChainProxy;

    protected MockMvc mockMvc;

    @Autowired
    protected AuthProvider authProvider;

    @Before
    public void setUp() throws Exception
    {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).addFilter(filterChainProxy).build();
    }
}
