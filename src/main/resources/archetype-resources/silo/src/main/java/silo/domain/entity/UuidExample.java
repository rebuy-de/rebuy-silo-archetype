#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )
package ${package}.${artifactId}.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.UUID;

@Entity
@Table(schema = "${rootArtifactId}", name = "example_uuid")
public class UuidExample
{
    @Id
    private UUID uuid;

    @Version
    public Long version;
}
