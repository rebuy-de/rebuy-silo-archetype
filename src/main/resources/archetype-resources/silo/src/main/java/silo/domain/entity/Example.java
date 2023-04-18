#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )
package ${package}.${artifactId}.domain.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.SequenceGenerator;

@Entity
@Table(schema = "${rootArtifactId}", name = "example")
public class Example
{
    @Id
    @GeneratedValue(generator = "example_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
        name = "example_id_seq",
        sequenceName = "example_id_seq",
        schema = "${rootArtifactId}",
        allocationSize = 1
    )
    private Long id;

}
