Feature: Cybersource Transaction Authorisation

  Scenario Outline: Transaction Authorisation

    Given I have a token
      | Card Type   | Token   | Exp Month   | Exp Year   |
      | <Card Type> | <Token> | <Exp Month> | <Exp Year> |
    When I try to authorise payment
      | Currency    | GBP                   |
      | Amount      | 15.00                 |
      | Title       | Mr                    |
      | First Name  | Test                  |
      | Last Name   | Dummy                 |
      | Line 1      | 1 Test St             |
      | Line 2      | Test Estate           |
      | City        | Test Town             |
      | Postal Code | XX1 1XX               |
      | Country     | GB                    |
      | Email       | test.dummy@domain.com |
      | Ip Address  | 127.0.0.1             |
      | Customer Id | 112233445566          |
    Then the payment authorisation decision was "<Result>"
    And the payment authorisation reason was "<Reason Code>"

    Examples:
      | Card Type | Token | Exp Month | Exp Year | Result  | Reason Code    |
      | VISA      | 2     | 12        | 2025     | DECLINE | ACCOUNT_FROZEN |
      | VISA      | 3     | 12        | 2025     | REVIEW  | CVN_DECLINED   |
      | VISA      | 4     | 12        | 2025     | ACCEPT  | ACCEPT         |