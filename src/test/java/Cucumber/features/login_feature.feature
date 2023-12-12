Feature: Login Functionality

  Scenario: try to do it
    Given login with the "Id" and "password"
    When Click on login button
    Then The "message" will be displayed

  Scenario: try to do it again
    Given login with the "Id" and "password"
    When Click on login button
    Then The "message" will be displayed
    And verify failure

  Scenario Outline: Login Functionality Testing
    Given login with the "<Id>" and "<password>"
    When Click on login button
    Then The "<message>" will be displayed


    Examples:
      | Id              | password     | message                            |
      | standard_user   | secret_sauce | success                            |
      | locked_out_user | login_web    | Username and password do not match |
      | locked_out_user | secret_sauce | this user has been locked out      |


