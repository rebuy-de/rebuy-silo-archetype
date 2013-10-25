#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )
package ${package}.${artifactId}.domain.entity;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;

@Entity
public class Example
{
    @Id
    @GeneratedValue
    @Column(name = "id")
    public Long id;


}
