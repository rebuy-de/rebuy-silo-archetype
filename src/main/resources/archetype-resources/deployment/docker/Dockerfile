FROM 074509403805.dkr.ecr.eu-west-1.amazonaws.com/rebuy-base-image-java

RUN mkdir -p /service \
 && useradd \
      --user-group \
      -Md /service \
      -s /bin/bash \
      silo

COPY deployment/docker/silo/run.sh /service/run.sh
RUN chmod +x /service/run.sh

USER silo

ENTRYPOINT /service/run.sh

COPY silo/target/${rootArtifactId}-silo.jar /service/silo.jar
