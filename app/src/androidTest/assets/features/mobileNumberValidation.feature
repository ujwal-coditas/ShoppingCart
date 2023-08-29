Feature: Mobile number validation
  Background:
    Given User is on Get Started page
    When User click on GET STARTED BUTTON
    Then User should redirected to login page
    When user enter mobile number
    And User click on SEND VERIFICATION CODE button

  Scenario: To verify Mobile number validation
    Given User is on validation page
    When User enters otp
    And Click on validate button
    And Click on change phone number button
    Then  User should redirected again to login page
