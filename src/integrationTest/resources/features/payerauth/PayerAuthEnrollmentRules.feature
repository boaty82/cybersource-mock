@Wip
Feature: Cybersource Payer Auth Enrollment

  Scenario Outline: <Card Type> '<Enrolled>' Enrollment

    Given I enter the following card data
      | Card Type   | Card Number   | Exp Month   | Exp Year   |
      | <Card Type> | <Card Number> | <Exp Month> | <Exp Year> |
    And I request a token
    When I check if the card is enrolled
    Then the enrollment status is "<Enrolled>"

    Examples:
      | Card Type | Card Number      | Exp Month | Exp Year | Enrolled |
      | VISA      | 4111111111111111 | 12        | 2025     | UNKNOWN  |
      | VISA      | 4000000000000001 | 12        | 2025     | NO       |
      | VISA      | 4000000000000002 | 12        | 2025     | YES      |