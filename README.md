# Java JDK11 TDD Bitcoin Converter

## Background
The following repo contains source code developed using TDD (Test Driven Development) practices. The sample project implements a Java (JDK 11) library which interacts with the [Bitcoin Price Index](https://www.coindesk.com/coindesk-api) api.

## Notes

This branch (step6) refactors the single GitHub Action workflow into seperate Dev and Prod GitHub Action workflows - the Dev workflow generates a unit test code coverage report and forwards it automatically into https://coveralls.io/ for viewing and analysis - the Prod workflow is used to produce releases

**Dev**

```
name: Java CI Dev

on:
  push:
    branches: main

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
      - name: Code Coverage
        run: |
          mvn -B jacoco:prepare-agent clean test jacoco:report coveralls:report -Dcoveralls.secret=${{ secrets.COVERALLS }}
      - name: Artifact Upload
        uses: actions/upload-artifact@v2
        with:
          name: Package
          path: staging
```

**Prod**

```
name: Java CI Prod

on:
  push:
    tags:
      - '*'

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
          mvn versions:set -DremoveSnapshot
          mvn -B clean package test
          mkdir release
          cp target/*.jar release
      - name: Artifact Upload
        uses: actions/upload-artifact@v2
        with:
          name: Package
          path: release

      - name: Make Release
        uses: softprops/action-gh-release@v0.1.5
        with:
          files:
            target/*.jar
        env:
          GITHUB_TOKEN: ${{ secrets.GITHUB_TOKEN }}
```

Build, test, and package Java11 and JUnit5 solution:

```
mvn clean test
```

```
mvn package
```