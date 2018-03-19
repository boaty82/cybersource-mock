Feature: Cybersource Create Token

  Scenario: Invalid Billing Information

    Given I enter the following card data
      | Card Type | Card Number      | Exp Month | Exp Year |
      | VISA      | 4111111111111111 | 12        | 2025     |
    And billing info is empty
    When I request a token
    Then the token request decision is "ERROR" and reason is "102" and message is "Request parameters are invalid or missing" and lists the following as invalid fields
      | bill_to_email               |
      | bill_to_forename            |
      | bill_to_surname             |
      | bill_to_address_line1       |
      | bill_to_address_city        |
      | bill_to_address_country     |
      | bill_to_address_postal_code |