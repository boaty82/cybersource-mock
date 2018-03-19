Feature: Cybersource Create Token

  Scenario Outline: Apply '<Surname> <Reason>' Decision Based on Forename/Surname

    Given I enter the following card data
      | Card Type | Card Number      | Exp Month | Exp Year | Forename | Surname   |
      | VISA      | 4111111111111111 | 12        | 2025     | TOKEN    | <Surname> |
    When I request a token
    Then the token request decision is "<Surname>" and reason is "<Reason Code>" and message is "Billing name was 'TOKEN <Surname>'"

    Examples:
      | Surname | Reason             | Reason Code |
      | DECLINE | DECLINED           | 203         |
      | REVIEW  | BANK_HAS_QUESTIONS | 201         |