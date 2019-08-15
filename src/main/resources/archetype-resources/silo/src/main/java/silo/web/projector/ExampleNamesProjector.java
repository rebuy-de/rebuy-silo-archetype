#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )
package ${package}.${artifactId}.web.projector;

import com.rebuy.library.projector.SimpleProjector;

import ${package}.${artifactId}.domain.entity.Example;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ExampleNamesProjector extends SimpleProjector<Example, List<String>>
{
    @Override
    public List<String> project(Example input)
    {
        return List.of("Foo", "Bar");
    }
}
