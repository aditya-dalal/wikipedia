
Pre-requisites:
1. java 1.8
2. maven


Run unit tests:
- mvn clean test -P unitTests


Check code coverage:
- open target/site/jacoco/index.html in browser


Run integration tests:
- mvn clean test -P integrationTests


Build executable jar:
- mvn clean package


Run application for sample input:
- java -jar target/wikipedia-1.0-SNAPSHOT.jar
