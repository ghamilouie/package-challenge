[![Build Status](https://travis-ci.org/ghamilouie/package-challenge.svg?branch=master)](https://travis-ci.org/ghamilouie/package-challenge)
[![codecov](https://codecov.io/gh/ghamilouie/package-challenge/branch/master/graph/badge.svg)](https://codecov.io/gh/ghamilouie/package-challenge)
# Package Challenge

A simple library for creating a package based on items weight and cost, 
the package total weight is less than or equal to the package limit and the total cost is as large as possible.

In this library the [0/1 Knapsack Problem](https://en.wikipedia.org/wiki/Knapsack_problem) 
with a Dynamic Programming approach is used, as in Dynamic Programming weight of items must be integer,
all weights is multiplied by 100.


## Technologies
- Java 8
- [JUnit](https://junit.org/)`

## How to build
```sh
./mvnw clean package
```

## How to run tests
```sh
mvn clean test
# converage file: ./target/site/jacoco/index.html
``` 

## How to use

Create a text file and use its absolute path as argument, 
input file should consist of one or more lines, each line with pattern:

capacity : (index, weight, cost) (index, weight, cost) ... (index, weight, cost)

```
String result = Packer.pack(filePath)
```

#### Input Sample

```
81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)

8 : (1,15.3,€34)

75 : (1,85.31,€29) (2,14.55,€74) (3,3.98,€16) (4,26.24,€55) (5,63.69,€52) (6,76.25,€75) (7,60.02,€74) (8,93.18,€35) (9,89.95,€78)

56 : (1,90.72,€13) (2,33.80,€40) (3,43.15,€10) (4,37.97,€16) (5,46.81,€36) (6,48.77,€79) (7,81.80,€45) (8,19.36,€79) (9,6.76,€64)
```

#### Output sample

```
4

\-

2,7

8,9
```