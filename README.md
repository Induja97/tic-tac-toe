# **Tic Tac Toe**

![](https://raw.githubusercontent.com/stephane-genicot/katas/master/images/Kata_TicTacToe.png)

## **Game Rules**

The rules are described below :

- X always goes first.
- Players cannot play on a played position.
- Players alternate placing X’s and O’s on the board until either:
- One player has three in a row, horizontally, vertically or diagonally
- All nine squares are filled.
- If a player is able to draw three X’s or three O’s in a row, that player wins.
- If all nine squares are filled and neither player has three in a row, the game is a draw.

## **Purpose**

Develop a simple tic-tac-toe game written in Java and Spring Boot using Test Driven Development (TDD) process.

## **Prerequisites**

- Java 8 & Spring Boot 2.7.5
- Gradle 7.5.1

## **How to build the application**

1. Clone this repository `https://github.com/sandiparthiharish/tic-tac-toe.git`
2. You can build the project and run the tests by running `gradlew clean build`

## **How to run the application**

- By default, the application will start on port 8080. If you want the application to run on different port 8082, you can pass additional parameter --server.port=8082 while starting the application
- Once successfully built, you can run the service by using one of below commands:

`java -jar build\libs\bowling-game-1.0.0-SNAPSHOT.jar`

                        or 

`java -jar build\libs\bowling-game-1.0.0-SNAPSHOT.jar --server.port=8082`