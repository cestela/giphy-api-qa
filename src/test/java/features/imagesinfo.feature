Feature: Validating that retrieved Images show correct image sources

  #Current scenario may fail due to API exceeding requests
  #when executing all tests together
  Scenario: Retrieved images links are not broken
    Given a set of query params
      |api_key      |q         |limit |
      |dc6zaTOxFJmzC|funny+cat |2     |
    When user sends GET operation to "/gifs/search" Giphy search API endpoint
    Then response status is 200
    And Images urls are not broken

    @ignore
  #Current scenario may fail due to API exceeding requests
  #when executing all tests together
  Scenario: Retrieved images width, height and sizes show feasible measurements
    Given a set of query params
      |api_key      |q         |limit |
      |dc6zaTOxFJmzC|funny+cat |2     |
    When user sends GET operation to "/gifs/search" Giphy search API endpoint
    Then response status is 200
    And Images width, height and sizes are greater than 0
