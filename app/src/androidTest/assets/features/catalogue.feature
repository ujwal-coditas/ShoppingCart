Feature: Catalogue Page

  @catalogue
  Scenario Outline: Verify Catalogue Page
    Given User click on Catalogue tab
    When User click on <section> section
    And User apply filter by selecting max price range
    And user select category <category> and brand <brand>
    Then Click on result button

    Examples:
      | section    | category      | brand              |
      | electronic | Watches       | Hunny Bunny        |
      | jewelery   | accessories   | brand Rosebuds     |
      | men's      | Mens fashion  | Adventure Apparel  |
      | women's    | Girls fashion | Bewitched Boutique |






