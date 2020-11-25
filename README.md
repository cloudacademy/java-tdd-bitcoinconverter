# Java JDK11 TDD Bitcoin Converter

## Background
The following repo contains source code developed using TDD (Test Driven Development) practices. The sample project implements a Java (JDK 11) library which interacts with the [Bitcoin Price Index](https://www.coindesk.com/coindesk-api) api.

## Notes

This branch (step7) introduces a client console project to test the GitHub Action built jar library artifact

Download the ```bitcoin-converter-svc-lib-1.0.9-SNAPSHOT.jar``` from the Github Releases page.

Use Maven to install ```bitcoin-converter-svc-lib-1.0.9-SNAPSHOT.jar``` library locally:

```
FILE=bitcoin-converter-svc-lib-1.0.9-SNAPSHOT.jar
VERSION=`echo $FILE | egrep -o '\d\.\d\.\d\-\w*'`
mvn install:install-file -Dfile=./libs/$FILE -DgroupId=com.cloudacademy -DartifactId=bitcoin-converter-svc-lib -Dversion=$VERSION -Dpackaging=jar
```

Update the client console project ```pom.xml``` file and add the ```bitcoin-converter-svc-lib``` library dependency

```
<dependency>
    <groupId>com.cloudacademy</groupId>
    <artifactId>bitcoin-converter-svc-lib</artifactId>
    <version>1.0.10-SNAPSHOT</version>
</dependency>
```

Build, test, and package the Java11 client console solution:

```
cd ./client
mvn clean package
```

Execute the Java11 client console solution:

```
java -jar target/bitcoin-converter-client-1.0.0-SNAPSHOT-jar-with-dependencies.jar
```