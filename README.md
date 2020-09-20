# GIPHY Search API Testing 

GIPHY Search API Testing is a Java project to test the search endpoint from GIPHY's API.

## Project Structure

```
.
└── giphy-api-qa
    └── src
        ├── main
        │   └── java
        │       └── org
        │           └── example
        │               └── domain
        └── test
            └── java
                ├── cucumber
                │   └── Options
                ├── features
                ├── resources
                └── steps

```

* **domain package**: Includes all the POJOs that are going to be used to map the service response.

* **cucumber.Options package**: Includes TestRunner class to execute cucumber tests with desired configuration.

* **features package**: Includes all cucumber feature files with all cucumber scenarios.

* **resources package**: Includes Utils and common Assertions classes.

* **steps package**: Includes cucumber Hooks and Step Definitions

## Prerequisites

The Cucumber testing tool is being used to define BDD test cases. So, in order to interact with Gherkin syntax and to execute TestRunner class, a few IDE plugins are needed. Installing the following or similar plugins is required:

-- plugins for IntelliJ IDEA --

* Cucumber for Java (JetBrains)
* Gherkin (JetBrains)

 More info on enabling Cucumber support can be found [here](https://www.jetbrains.com/help/idea/enabling-cucumber-support-in-project.html).

Additionally, different lombok annotations are used to reduce code and improve readability. The following plugin should be installed in order for the IDE to identify these annotations:

* Lombok (Michail Plushnikov)


## Dependencies

This project is built using [Maven](https://maven.apache.org/). So pom.xml file is provided with all the dependencies and builds.

## Usage

In order to execute the test, there are two available options:

1. Executing them straight from CLI using maven command:

```bash
mvn test
```

2. Executing TestRunner class directly from source code. Class can be found in the following path: `src/test/java/cucumber/Options/TestRunner.java`

## Test Cases Definitions

Each feature file includes different test cases in the form of Cucumber Scenarios.

A total of 13 scenarios are provided to cover the critical functional path of the GIFs Search API. 

Cucumber Scenarios are written in Gherkin syntax. This is done  to make it easier for the reader to understand what is going to be tested. In addition, these step-definitions improve code reusability and maintainability.


## Test Reports

Both JSON and HTML reports can be found in `/target` folder generated during test execution.

After the test execution is finished (using both CLI maven command or class execution), HTML file `cucumber-reports.html` can be found inside `/target` folder. In order to view the report, just open the file in any browser.

