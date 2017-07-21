#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )
package ${package}.${artifactId}.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(catalog = "${rootArtifactId}", name = "example")
public class Example
{
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

}
