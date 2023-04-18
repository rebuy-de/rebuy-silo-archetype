#set($symbol_pound='#')
#set($symbol_dollar='$')
#set($symbol_escape='\' )
package ${package}.${artifactId}.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
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
