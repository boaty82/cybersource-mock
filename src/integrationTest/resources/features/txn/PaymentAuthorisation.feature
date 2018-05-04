Feature: Cybersource Transaction Cancellation

  Scenario Outline: Transaction Cancellation

    Given I have a authorised a transaction and the ID was "<Auth Id>"
    When I try to cancel the authorisation
    Then the payment cancellation decision was "<Result>"
    And the payment cancellation reason was "<Reason Code>"

    Examples:
      | Auth Id | Result | Reason Code |
      | 5       | ACCEPT | ACCEPT      |