#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )
package ${package}.${artifactId}.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

@Controller
@RequestMapping(value = "/examples", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExampleController
{
}
