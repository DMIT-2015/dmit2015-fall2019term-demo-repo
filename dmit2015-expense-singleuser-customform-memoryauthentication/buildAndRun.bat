@echo off
call mvn clean package
call docker build -t expense/dmit2015-expense-singleuser-customform-memoryauthentication .
call docker rm -f dmit2015-expense-singleuser-customform-memoryauthentication
call docker run -d -p 8080:8080 -p 4848:4848 --name dmit2015-expense-singleuser-customform-memoryauthentication expense/dmit2015-expense-singleuser-customform-memoryauthentication