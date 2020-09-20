Feature: Validating GIPHY Search API with a Basic Search

  Scenario: Minimum query parameters search
    Given a set of query params
      |api_key      |q        |
      |dc6zaTOxFJmzC|funny+cat|
    When user sends GET operation to Giphy search API
    Then response status is 200
    And response "data.size()" is "greater than" 0
    And response "meta.status" is 200
    And response "meta.msg" is "OK"
    And response "pagination.count" equals data size

  Scenario: Wrong api_key search
    Given a set of query params
      |api_key      |q        |
      |qatest       |funny+cat|
    When user sends GET operation to Giphy search API
    Then response status is 403
    And response "message" is "Invalid authentication credentials"

  Scenario: No api_key in query params
    Given a set of query params
     |q        |
     |funny+cat|
    When user sends GET operation to Giphy search API
    Then response status is 401
    And response "message" is "No API key found in request"

  Scenario: No results search
    Given a set of query params
      |api_key      |q        |
      |dc6zaTOxFJmzC|qatest   |
    When user sends GET operation to Giphy search API
    Then response status is 200
    And response "data.size()" is "equal to" 0
    And response "meta.status" is 200
    And response "meta.msg" is "OK"
    And response "pagination.count" is 0


