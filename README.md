# Java JDK11 TDD Bitcoin Converter

## Background
The following repo contains source code developed using TDD (Test Driven Development) practices. The sample project implements a Java (JDK 11) library which interacts with the [Bitcoin Price Index](https://www.coindesk.com/coindesk-api) api.

## Notes

This branch (step3) introduces Mockito and refactors current unit tests to mock the Apache HttpClient HTTP calls to the Bitcoin Price Index APIefactors current unit tests and codebase to call the real Bitcoin Price Index API using the Apache HttpClient for HTTP access - the tests in this step will fail, this introduces you to the next step (mocks)

Search Maven Central Repository for both Apache HTTP Components and Google Code GSON libraries:

```
https://search.maven.org/
https://search.maven.org/artifact/org.mockito/mockito-core/3.5.13/jar
```

Add Mockito library as **test** scoped dependency to the ```pom.xml``` file:

```
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-core</artifactId>
    <version>3.5.13</version>
    <scope>test</scope>
</dependency>  
```

Build, test, and package Java11 and JUnit5 solution:

```
mvn clean test
```

```
mvn package
```