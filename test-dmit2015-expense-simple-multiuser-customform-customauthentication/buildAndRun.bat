@echo off
call mvn clean package
call docker build -t expense/test-dmit2015-expense-simple-multiuser-customform-customauthentication .
call docker rm -f test-dmit2015-expense-simple-multiuser-customform-customauthentication
call docker run -d -p 8080:8080 -p 4848:4848 --name test-dmit2015-expense-simple-multiuser-customform-customauthentication expense/test-dmit2015-expense-simple-multiuser-customform-customauthentication