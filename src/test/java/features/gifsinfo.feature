Feature: Validating that retrieved GIFs from GIPHY Search API show consistent information

  Scenario: Retrieved gifs links are not broken
    Given a set of query params
      |api_key      |q         |limit |
      |dc6zaTOxFJmzC|funny+cat |1     |
    When user sends GET operation to Giphy search API
    Then response status is 200
    And response "data.size()" is "greater than" 0
    And response "meta.status" is 200
    And response "meta.msg" is "OK"
    And response "pagination.count" equals data size
    And GIFs urls are not broken

  Scenario: Retrieved gifs unshortened links contain GIPHYs unique gifs id
    Given a set of query params
      |api_key      |q         |limit |
      |dc6zaTOxFJmzC|funny+cat |5     |
    When user sends GET operation to Giphy search API
    Then response status is 200
    And response "data.size()" is "greater than" 0
    And response "meta.status" is 200
    And response "meta.msg" is "OK"
    And response "pagination.count" equals data size
    And GIFs unshortened urls contain gifs GIPHY Id

  Scenario: Retrieved gifs user data links contain valid user info
    Given a set of query params
      |api_key      |q         |
      |dc6zaTOxFJmzC|funny+cat |
    When user sends GET operation to Giphy search API
    Then response status is 200
    And response "data.size()" is "greater than" 0
    And response "meta.status" is 200
    And response "meta.msg" is "OK"
    And response "pagination.count" equals data size
    And response username matches user.username
    And response user profile url is not broken

  Scenario: Retrieved gifs imported date are before than system's date
    Given a set of query params
      |api_key      |q         |
      |dc6zaTOxFJmzC|funny+cat |
    When user sends GET operation to Giphy search API
    Then response status is 200
    And response "data.size()" is "greater than" 0
    And response "meta.status" is 200
    And response "meta.msg" is "OK"
    And response "pagination.count" equals data size
    And response GIFs import datetime are before than system's date


