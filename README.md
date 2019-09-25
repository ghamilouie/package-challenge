[![Build Status](https://travis-ci.org/ghamilouie/package-challenge.svg?branch=master)](https://travis-ci.org/ghamilouie/package-challenge)
[![codecov](https://codecov.io/gh/ghamilouie/package-challenge/branch/master/graph/badge.svg)](https://codecov.io/gh/ghamilouie/package-challenge)
# Package Challenge

A simple library for creating package based on items weight and cost, 
the package total weight is less than or equal to the package limit and the total cost is as large as possible

## Technologies
- Java 8
- [JUnit](https://junit.org/)

## How to build
```sh
./mvnw clean package
```

## How to run tests
```sh
mvn clean test
# converage file: ./target/site/jacoco/index.html
``` 

##How to use
```
String result = Packer.pack(absoluteFilePath)
```
