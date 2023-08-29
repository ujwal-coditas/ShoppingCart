Feature: Sign in with google
  Background:
    Given User is on Get Started page
    When User click on GET STARTED BUTTON
    Then User should redirected to login page

  Scenario: To verify Sign in with google feature
    When User clicks on SIGN In WITH GOOGLE button
    Then Choose an account window should pop up
    When User select google account by clicking on it
    Then User should redirected to Home page



