# Java JDK11 TDD Bitcoin Converter

## Background
The following repo contains source code developed using TDD (Test Driven Development) practices. The sample project implements a Java (JDK 11) library which interacts with the [Bitcoin Price Index](https://www.coindesk.com/coindesk-api) api.

## Notes

This branch (step5) adds in a [GitHub Action](https://github.com/cloudacademy/java-tdd-bitcoinconverter/tree/step5/.github/workflows) workflow to perform automatic build and tests on push events - produces a jar artifact

```
name: Java CI

on: [push]

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
      - uses: actions/checkout@v2

      - name: JDK 1.11 Install
        uses: actions/setup-java@v1
        with:
          java-version: 11

      - name: Maven Build
        run: |
          mvn -B clean package
          mkdir staging
          cp target/*.jar staging
      - name: Artifact Upload
        uses: actions/upload-artifact@v2
        with:
          name: Package
          path: staging
```

Build, test, and package Java11 and JUnit5 solution:

```
mvn clean test
```

```
mvn package
```