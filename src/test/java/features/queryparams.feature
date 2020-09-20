Feature: Validating GIPHY Search API with different query params search

  Scenario: Limited rating for retrieved GIFs
    Given a set of query params
      |api_key      |q    |rating |
      |dc6zaTOxFJmzC|fire |pg     |
    When user sends GET operation to Giphy search API
    Then response status is 200
    And response "data.size()" is "greater than" 0
    And response "meta.status" is 200
    And response "meta.msg" is "OK"
    And response gifs ratings are not
      |pg-13|r|
    And response "pagination.count" equals data size

  Scenario: Limited quantity for retrieved GIFs
    Given a set of query params
      |api_key      |q        | limit |
      |dc6zaTOxFJmzC|funny+cat| 25    |
    When user sends GET operation to Giphy search API
    Then response status is 200
    And response "data.size()" is "equal to" 25
    And response "meta.status" is 200
    And response "meta.msg" is "OK"
    And response "pagination.count" equals data size

  @CustomOffset
  Scenario: Custom offset for retrieved GIFs
    Given a set of query params
      |api_key      |q         |offset |
      |dc6zaTOxFJmzC|funny+cat |4     |
    When user sends GET operation to Giphy search API
    Then response status is 200
    And response "data.size()" is "greater than" 0
    And response "meta.status" is 200
    And response "meta.msg" is "OK"
    And response "data[0].id" is the 5th element of the list without offset
    And response "pagination.count" equals data size


