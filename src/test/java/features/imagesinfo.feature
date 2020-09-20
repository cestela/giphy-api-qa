Feature: Validating that retrieved Images show correct image sources

  #Current scenario failing due to API exceeding requests
  #when executing all thests together
  Scenario: Retrieved images links are not broken
    Given a set of query params
      |api_key      |q         |limit |
      |dc6zaTOxFJmzC|funny+cat |2     |
    When user sends GET operation to Giphy search API
    Then response status is 200
    And response "data.size()" is "greater than" 0
    And response "meta.status" is 200
    And response "meta.msg" is "OK"
    And response "pagination.count" equals data size
    And Images urls are not broken

  #Current scenario failing due to API exceeding requests
  #when executing all thests together  Scenario: Retrieved images width, height and sizes show feasible measurements
    Given a set of query params
      |api_key      |q         |limit |
      |dc6zaTOxFJmzC|funny+cat |2     |
    When user sends GET operation to Giphy search API
    Then response status is 200
    And response "data.size()" is "greater than" 0
    And response "meta.status" is 200
    And response "meta.msg" is "OK"
    And response "pagination.count" equals data size
    And Images width, height and sizes are greater than 0
