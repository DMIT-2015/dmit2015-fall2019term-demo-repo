#!/bin/sh
mvn clean package && docker build -t expense/dmit2015-expense-singleuser-basic-memoryauthentication .
docker rm -f dmit2015-expense-singleuser-basic-memoryauthentication || true && docker run -d -p 8080:8080 -p 4848:4848 --name dmit2015-expense-singleuser-basic-memoryauthentication expense/dmit2015-expense-singleuser-basic-memoryauthentication