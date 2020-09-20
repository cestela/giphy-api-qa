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

  #Current scenario may fail due to API exceeding requests
  #when executing all tests together
  Scenario: Retrieved images width, height and sizes show feasible measurements
    Given a set of query params
      |api_key      |q         |limit |
      |dc6zaTOxFJmzC|funny+cat |2     |
    When user sends GET operation to "/gifs/search" Giphy search API endpoint
    Then response status is 200
    And Images width, height and sizes are greater than 0

  Scenario: Fixed height images have a height of 200px
    Given a set of query params
      |api_key      |q         |limit |
      |dc6zaTOxFJmzC|funny+cat |2     |
    When user sends GET operation to "/gifs/search" Giphy search API endpoint
    Then response status is 200
    And images "fixed_height" "height" is "equal to" 200
    And images "fixed_height_still" "height" is "equal to" 200
    And images "fixed_height_downsampled" "height" is "equal to" 200

  Scenario: Fixed width images have a width of 200px
    Given a set of query params
      |api_key      |q         |limit |
      |dc6zaTOxFJmzC|funny+cat |2     |
    When user sends GET operation to "/gifs/search" Giphy search API endpoint
    Then response status is 200
    And images "fixed_width" "width" is "equal to" 200
    And images "fixed_width_still" "width" is "equal to" 200
    And images "fixed_width_downsampled" "width" is "equal to" 200

  Scenario: Small fixed height images have a height of 100px
    Given a set of query params
      |api_key      |q         |limit |
      |dc6zaTOxFJmzC|funny+cat |2     |
    When user sends GET operation to "/gifs/search" Giphy search API endpoint
    Then response status is 200
    And images "fixed_height_small" "height" is "equal to" 100
    And images "fixed_height_small_still" "height" is "equal to" 100

  Scenario: Small fixed width images have a width of 100px
    Given a set of query params
      |api_key      |q         |limit |
      |dc6zaTOxFJmzC|funny+cat |2     |
    When user sends GET operation to "/gifs/search" Giphy search API endpoint
    Then response status is 200
    And images "fixed_width_small" "width" is "equal to" 100
    And images "fixed_width_small_still" "width" is "equal to" 100

  Scenario: Downsized images size is less than 2 mb
    Given a set of query params
      |api_key      |q         |limit |
      |dc6zaTOxFJmzC|funny+cat |2     |
    When user sends GET operation to "/gifs/search" Giphy search API endpoint
    Then response status is 200
    And images "downsized" "size" is "less than" 2000000
    And images "downsized_large" "size" is "less than" 2000000
    And images "downsized_medium" "size" is "less than" 2000000
    And images "downsized_small" "mp4_size" is "less than" 2000000

  Scenario: Preview images size is less than 50 kb
    Given a set of query params
      |api_key      |q         |limit |
      |dc6zaTOxFJmzC|funny+cat |2     |
    When user sends GET operation to "/gifs/search" Giphy search API endpoint
    Then response status is 200
    And images "preview" "mp4_size" is "less than" 50000




