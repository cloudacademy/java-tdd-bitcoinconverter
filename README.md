# Java JDK11 TDD Bitcoin Converter

## Background
The following repo contains source code developed using TDD (Test Driven Development) practices. The sample project implements a Java (JDK 11) library which interacts with the [Bitcoin Price Index](https://www.coindesk.com/coindesk-api) api.

## Notes

This branch (step7) introduces a [client console](https://github.com/cloudacademy/java-tdd-bitcoinconverter/tree/step7/client) based project which imports and uses the GitHub Action built ```bitcoin-converter-svc-lib-1.0.*-SNAPSHOT.jar``` library artifact

Download the ```bitcoin-converter-svc-lib-1.0.*-SNAPSHOT.jar``` from the Github Releases page and add the file into the [./libs](https://github.com/cloudacademy/java-tdd-bitcoinconverter/tree/step7/client/libs) folder (replace existing one if there).

Use Maven to install the ```bitcoin-converter-svc-lib-1.0.*-SNAPSHOT.jar``` library locally:


```
cd ./client
#install bitcoin lib
FILE=`ls libs/`
VERSION=`echo $FILE | egrep -o "([0-9]{1,}\.)+[0-9]{1,}-\w*"`
mvn install:install-file \
 -Dfile=./libs/$FILE \
 -DgroupId=com.cloudacademy \
 -DartifactId=bitcoin-converter-svc-lib \
 -Dversion=$VERSION \
 -Dpackaging=jar
```

Update the client console project ```pom.xml``` file and add the ```bitcoin-converter-svc-lib``` library dependency. Update the `<version>` element to contain the version just downloaded.

```
<dependency>
    <groupId>com.cloudacademy</groupId>
    <artifactId>bitcoin-converter-svc-lib</artifactId>
    <version>1.0.10-SNAPSHOT</version>
</dependency>
```

Build, test, and package the Java11 client console solution:

```
mvn clean package
```

Execute the Java11 client console solution:

```
java -jar target/bitcoin-converter-client-1.0.0-SNAPSHOT-jar-with-dependencies.jar
```