def baseDir = new File(request.getOutputDirectory() + "/" + request.getArtifactId())
def runFile = new File(baseDir, "deployment/docker/silo/run.sh")

println "Making $runFile executable"

runFile.setExecutable(true, false)
