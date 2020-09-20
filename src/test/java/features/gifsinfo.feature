Feature: Validating that retrieved GIFs from GIPHY Search API show consistent information

  @ignore
  Scenario: Retrieved gifs links are not broken
    Given a set of query params
      |api_key      |q         |limit |
      |dc6zaTOxFJmzC|funny+cat |1     |
    When user sends GET operation to "/gifs/search" Giphy search API endpoint
    Then response status is 200
    And GIFs urls are not broken

  @ignore
  Scenario: Retrieved gifs unshortened links contain GIPHYs unique gifs id
    Given a set of query params
      |api_key      |q         |limit |
      |dc6zaTOxFJmzC|funny+cat |5     |
    When user sends GET operation to "/gifs/search" Giphy search API endpoint
    Then response status is 200
    And GIFs unshortened urls contain gifs GIPHY Id

  @ignore
  Scenario: Retrieved gifs user data links contain valid user info
    Given a set of query params
      |api_key      |q         |
      |dc6zaTOxFJmzC|funny+cat |
    When user sends GET operation to "/gifs/search" Giphy search API endpoint
    Then response status is 200
    And response username matches user.username
    And response user profile url is not broken

  @ignore
  Scenario: Retrieved gifs imported date are before than system's date
    Given a set of query params
      |api_key      |q         |
      |dc6zaTOxFJmzC|funny+cat |
    When user sends GET operation to "/gifs/search" Giphy search API endpoint
    Then response status is 200
    And response GIFs import datetime are before than system's date


