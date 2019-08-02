#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )
package ${package}.${artifactId}.web.controller;

import ${package}.web.dto.response.ExampleResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController
{
    @PreAuthorize("hasAnyRole('ROLE_SYSTEM')")
    @RequestMapping(value = "/examples", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ExampleResponseDto> example()
    {
        ExampleResponseDto example = new ExampleResponseDto();

        example.id = 1L;

        return new ResponseEntity<>(example, HttpStatus.OK);
    }
}
