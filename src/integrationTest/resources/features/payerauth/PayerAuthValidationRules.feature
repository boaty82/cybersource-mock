Feature: Cybersource Payer Auth Validation

  Scenario Outline: Validation

    Given I have a token
      | Card Type   | Token   | Exp Month   | Exp Year   |
      | <Card Type> | <Token> | <Exp Month> | <Exp Year> |
    And the card is enrolled
    When I check the card payer authorisation
    Then the card payer authorisation is "<Result>" and the indicator is "<Commerce Indicator>"

    Examples:
      | Card Type | Token | Exp Month | Exp Year | Result  | Commerce Indicator |
      | VISA      | 1     | 12        | 2025     | SUCCESS | VBV_SUCCESS        |