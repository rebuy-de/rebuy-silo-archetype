#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.${artifactId};

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rebuy.library.messaging.annotation.MockMessaging;
import com.rebuy.library.security.test.AuthProvider;
import com.rebuy.library.testutil.IntegrationTest;
import org.junit.experimental.categories.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.actuate.observability.AutoConfigureObservability;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@Category(IntegrationTest.class)
@SpringBootTest(
    classes = Application.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@MockMessaging
@AutoConfigureObservability
@ActiveProfiles("testing")
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

    @BeforeEach
    public void setUp() throws Exception
    {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).addFilter(filterChainProxy).build();
    }
}
