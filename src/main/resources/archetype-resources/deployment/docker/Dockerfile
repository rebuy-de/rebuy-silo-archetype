# syntax=docker/dockerfile:experimental
FROM 074509403805.dkr.ecr.eu-west-1.amazonaws.com/java11-maven AS builder

COPY . /project

ENV HOME=/tmp

RUN --mount=id=maven-cache,type=cache,target=/m2/repository,sharing=shared /build.sh

FROM 074509403805.dkr.ecr.eu-west-1.amazonaws.com/java11-maven:${BASE_TAG}

RUN mkdir -p /service \
 && useradd \
      --user-group \
      -Md /service \
      -s /bin/bash \
      silo

USER silo

ENTRYPOINT /service/run.sh

COPY --chown=0:0 deployment/docker/silo/run.sh /service/run.sh

COPY --chown=0:0 --from=builder /project/silo/target/${rootArtifactId}-silo.jar /service/silo.jar
