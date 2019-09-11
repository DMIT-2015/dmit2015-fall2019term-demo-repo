#!/bin/sh
mvn clean package && docker build -t ca.nait/dmit2015-fall2019term-demos-swu .
docker rm -f dmit2015-fall2019term-demos-swu || true && docker run -d -p 8080:8080 -p 4848:4848 --name dmit2015-fall2019term-demos-swu ca.nait/dmit2015-fall2019term-demos-swu