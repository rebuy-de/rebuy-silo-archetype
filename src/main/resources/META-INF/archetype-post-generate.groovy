import java.nio.file.Files
import java.nio.file.StandardOpenOption

def baseDir = new File(request.getOutputDirectory() + "/" + request.getArtifactId())
def runFile = new File(baseDir, "deployment/docker/silo/run.sh")

println "Making $runFile executable"

runFile.setExecutable(true, false)

// due to a bug in the maven-archtype-plugin .gitignore files are not copied to the archtype-jar, so we create it by hand on post-generate
def gitignoreFile = new File(baseDir, ".gitignore")

def gitIgnoreContent = """.classpath
.project
.settings/

target/
*.log
trace.db/

.factorypath
*.orig

.DS_Store
shared/export/
shared/
java_pid*

*.iml
.idea/
"""

println "Creating default .gitignore"

Files.writeString(gitignoreFile.toPath(), gitIgnoreContent, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)
