Feature: Cybersource Create Token

  Scenario Outline: <Card Type> Tokenise

    Given I enter the following card data
      | Card Type   | Card Number   | Exp Month   | Exp Year   |
      | <Card Type> | <Card Number> | <Exp Month> | <Exp Year> |
    When I request a token
    Then I receive back the given data
    And the card number is masked as "<Masked Card Number>"
    And I receive a token id

    Examples:
      | Card Type | Card Number      | Masked Card Number | Exp Month | Exp Year |
      | VISA      | 4111111111111111 | ************1111   | 12        | 2025     |