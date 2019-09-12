#!/bin/sh
mvn clean package && docker build -t ca.nait.dmit/dmit2015-exercises-swu .
docker rm -f dmit2015-exercises-swu || true && docker run -d -p 8080:8080 -p 4848:4848 --name dmit2015-exercises-swu ca.nait.dmit/dmit2015-exercises-swu