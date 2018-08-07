#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )
package ${package}.${artifactId}.domain.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(catalog = "${rootArtifactId}", name = "example")
public class Example
{
    @Id
    @GeneratedValue(generator = "example_id_seq", strategy = GenerationType.SEQUENCE)
    @GenericGenerator(
        name = "example_id_seq",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
            @Parameter(name = "sequence_name", value = "example_id_seq"),
            @Parameter(name = "schema", value = "${rootArtifactId}")
        }
    )
    private Long id;

}
