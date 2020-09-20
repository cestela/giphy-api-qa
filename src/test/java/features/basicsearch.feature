Feature: Validating GIPHY Search API with a Basic Search

  Scenario: Minimum query parameters search GIFs
    Given a set of query params
      |api_key      |q        |
      |dc6zaTOxFJmzC|funny+cat|
    When user sends GET operation to "/gifs/search" Giphy search API endpoint
    Then response status is 200
    And response "data.size()" is "greater than" 0
    And response "data[0].is_sticker" is 0
    And response "meta.status" is 200
    And response "meta.msg" is "OK"
    And response "meta.response_id" is not empty
    And response "pagination.count" equals data size
    # API returns 50 as default limit but API documentation says the limit is 25
    And response "pagination.count" is "less than" 26
    And response "pagination.total_count" is "greater than" 0

  Scenario: Minimum query parameters search Stickers
    Given a set of query params
      |api_key      |q        |
      |dc6zaTOxFJmzC|funny+cat|
    When user sends GET operation to "/stickers/search" Giphy search API endpoint
    Then response status is 200
    And response "data.size()" is "greater than" 0
    And response "data[0].is_sticker" is 1
    And response "meta.status" is 200
    And response "meta.msg" is "OK"
    And response "meta.response_id" is not empty
    And response "pagination.count" equals data size
    # API returns 50 as default limit but API documentation says the limit is 25
    And response "pagination.count" is "less than" 26
    And response "pagination.total_count" is "greater than" 0

  Scenario: Wrong api_key search
    Given a set of query params
      |api_key      |q        |
      |qatest       |funny+cat|
    When user sends GET operation to "/gifs/search" Giphy search API endpoint
    Then response status is 403
    And response "message" is "Invalid authentication credentials"

  Scenario: No api_key in query params
    Given a set of query params
     |q        |
     |funny+cat|
    When user sends GET operation to "/gifs/search" Giphy search API endpoint
    Then response status is 401
    And response "message" is "No API key found in request"

  Scenario: No results search
    Given a set of query params
      |api_key      |q        |
      |dc6zaTOxFJmzC|qatest   |
    When user sends GET operation to "/gifs/search" Giphy search API endpoint
    Then response status is 200
    And response "data.size()" is "equal to" 0
    And response "meta.status" is 200
    And response "meta.msg" is "OK"
    And response "meta.response_id" is not empty
    And response "pagination.count" is 0



