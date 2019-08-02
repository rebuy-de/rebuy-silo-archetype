#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )
package ${package}.${artifactId}.dto.response;

import com.rebuy.library.projector.Projectable;

import java.util.List;

public class ExampleResponseDto
{
    public Long id;

    @Projectable(name = "names")
    public List<String> names;
}
