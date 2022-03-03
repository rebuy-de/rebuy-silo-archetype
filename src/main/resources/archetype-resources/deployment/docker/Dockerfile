# syntax=rebuy.local/dockerfile
ARG BASE_TAG=8
FROM 074509403805.dkr.ecr.eu-west-1.amazonaws.com/java17-maven:${BASE_TAG} AS builder

COPY . /project

ENV HOME=/tmp

RUN --mount=id=maven-cache,type=cache,target=/m2/repository,sharing=shared /build.sh

FROM 074509403805.dkr.ecr.eu-west-1.amazonaws.com/java17:${BASE_TAG}

RUN mkdir -p /service \
 && useradd \
      --user-group \
      -Md /service \
      -s /bin/bash \
      silo

USER silo

ENTRYPOINT /service/run.sh

COPY --chown=0:0 deployment/docker/silo/run.sh /service/run.sh

COPY --chown=0:0 --from=builder /project/silo/target/${projectName}.jar /service/silo.jar
