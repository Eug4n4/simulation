# Simulation

## Description

This project is a turn-based simulation of a world inhabited by herbivores and predators. Herbivores's goal is to find grass. It serves as food for them. Predators, on the other hand, are trying to reach herbivores and kill them.

The main goal of the project is to practice using main OOP principles: abstraction, polymorphism, inheritance and encapsulation.

## How to run it?

### Requirements
> JDK 21+ [Download](https://www.oracle.com/java/technologies/downloads/#java21)


1. Clone this repository
    ```bash
    git clone https://github.com/Eug4n4/simulation.git
    cd simulation
    ```
2. Compile the program
    ```bash
    javac -d bin src\*.java src\action\*.java src\dialog\*.java src\entity\*.java src\validator\*.java src\worldmap\*.java
    ```
3. Run
    ```bash
    java -cp bin Main
    ```

> These steps are applicable when running in terminal. If you are going to launch this program using an IDE, you shouldn't follow them.