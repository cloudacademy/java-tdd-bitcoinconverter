# Java JDK11 TDD Bitcoin Converter

## Background
The following repo contains source code developed using TDD (Test Driven Development) practices. The sample project implements a Java (JDK 11) library which interacts with the [Bitcoin Price Index](https://www.coindesk.com/coindesk-api) api.

## Notes

This branch (step2) refactors current unit tests and codebase to call the real Bitcoin Price Index API using the Apache HttpClient for HTTP access - the tests in this step will fail, this introduces you to the next step (mocks)

Search Maven Central Repository for both Apache HTTP Components and Google Code GSON libraries:

```
https://search.maven.org/
https://search.maven.org/artifact/org.apache.httpcomponents/httpclient/4.5.13/jar
https://search.maven.org/artifact/com.google.code.gson/gson/2.8.6/jar
```

Add libraries as dependencies to the ```pom.xml``` file:

```
<dependency>
    <groupId>org.apache.httpcomponents</groupId>
    <artifactId>httpclient</artifactId>
    <version>4.5.13</version>
</dependency>
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.8.6</version>
</dependency>    
```

Bitcoin Price Index API used within the ```ConverterSvc.java``` class:

```
https://api.coindesk.com/v1/bpi/currentprice.json
```

Bitcoin Price Index API returns the following response:

```
curl -s https://api.coindesk.com/v1/bpi/currentprice.json | jq .
```

```
{
    "time": {
        "updated": "Oct 15, 2020 22:55:00 UTC",
        "updatedISO": "2020-10-15T22:55:00+00:00",
        "updateduk": "Oct 15, 2020 at 23:55 BST"
    },
    "disclaimer": "This data was produced from the CoinDesk Bitcoin Price Index (USD)",
    "chartName": "Bitcoin",
    "bpi": {
        "USD": {
        "code": "USD",
        "symbol": "&#36;",
        "rate": "11,486.5341",
        "description": "United States Dollar",
        "rate_float": 11486.5341
        },
        "GBP": {
        "code": "GBP",
        "symbol": "&pound;",
        "rate": "8,900.8693",
        "description": "British Pound Sterling",
        "rate_float": 8900.8693
        },
        "EUR": {
        "code": "EUR",
        "symbol": "&euro;",
        "rate": "9,809.3278",
        "description": "Euro",
        "rate_float": 9809.3278
        }
    }
}
```

Build, test, and package Java11 and JUnit5 solution:

```
mvn clean test
```

```
mvn package
```