#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )
package ${package}.${artifactId}.domain.entity;

import javax.persistence.*;

@Entity
public class Example
{
    @Id
    @GeneratedValue
    @Column(name = "id")
    public Long id;


}
