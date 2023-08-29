Feature: Login feature
  Background:
    Given User is on Get Started page
    When User click on GET STARTED BUTTON
    Then User should redirected to login page
    Given User is on login page

    Scenario:Verify skip login feature
      When User clicks on skip button
      Then User should redirected to Home page
      When User click on back button
      Then User should redirected again to login page


  Scenario: Verify login feature with mobile number
    When user enter mobile number
    And User click on SEND VERIFICATION CODE button
    Then User should redirected to validation page
