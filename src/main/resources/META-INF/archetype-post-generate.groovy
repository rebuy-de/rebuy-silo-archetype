def baseDir = new File(request.getOutputDirectory() + "/" + request.getArtifactId())

def buildFile = new File(baseDir, "deployment/bin/build")

println "Making $buildFile executable"

buildFile.setExecutable(true, false)
