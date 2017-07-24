#!/bin/bash

set -ex

cd /service/

exec \
  java \
    ${SILO_JAVA_ARGS} \
    -Dfile.encoding=UTF-8 \
    -Dspring.profiles.active=${SILO_PROFILE} \
    -jar /service/silo.jar
