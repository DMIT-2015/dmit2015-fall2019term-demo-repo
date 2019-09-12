@echo off
call mvn clean package
call docker build -t ca.nait.dmit/dmit2015-exercises-swu .
call docker rm -f dmit2015-exercises-swu
call docker run -d -p 8080:8080 -p 4848:4848 --name dmit2015-exercises-swu ca.nait.dmit/dmit2015-exercises-swu