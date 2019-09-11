#!/bin/sh
mvn clean package && docker build -t ca.nait/dmit2015-fall2019term-instructor-demos .
docker rm -f dmit2015-fall2019term-instructor-demos || true && docker run -d -p 8080:8080 -p 4848:4848 --name dmit2015-fall2019term-instructor-demos ca.nait/dmit2015-fall2019term-instructor-demos
