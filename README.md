# TMPS-Labs

Laboratory works for Software Design Techniques and Mechanisms (TMPS).

| Lab | Topic | Package | Report |
|---|---|---|---|
| Lab 0 | SOLID principles (quiz app) | `org.example.Lab0` | [reports/lab0.md](reports/lab0.md) |
| Lab 1 | Creational patterns (quiz app) | `org.example.Lab1` | [reports/lab1.md](reports/lab1.md) |
| Lab 2 | Structural patterns (coffee shop) | `org.example.Lab3` | [reports/lab3.md](reports/lab3.md) |
| Lab 3 | Behavioral patterns (library) | `org.example.Lab4` | [reports/lab4.md](reports/lab4.md) |

## Requirements

- Java 17
- Maven 3 (or IntelliJ IDEA, which has Maven built in)

## Build

```
mvn compile
```

## Run

Coffee shop (structural patterns):

```
mvn compile exec:java -Dexec.mainClass=org.example.Lab3.Main
```

or open `src/main/java/org/example/Lab3/Main.java` in IntelliJ and click **Run**.

Library (behavioral patterns):

```
mvn compile exec:java -Dexec.mainClass=org.example.Lab4.Main
```

or open `src/main/java/org/example/Lab4/Main.java` in IntelliJ and click **Run**.

## Tests

All unit tests run with one command:

```
mvn test
```
