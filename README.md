## How to run
1. `git clone git@github.com:nurdians/learn-rest-assured.git`
2. `mvn clean install`
3. `mvn test`

## How to run specific suite
`mvn test -Dsurefire.suiteXmlFiles=src/test/java/testSuite/TestSuite.xml`

## How to run specific groups
`mvn test -Dgroups="test1,test2"`