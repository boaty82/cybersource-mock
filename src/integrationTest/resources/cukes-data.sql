INSERT INTO Token
  (payment_token, decision, reason_Code, request_Token, message, bill_Email, bill_Forename, bill_Surname, bill_Line1, bill_Line2, bill_Postal_Code, bill_City, bill_Country, expiry_Date_Iso, card_Number, card_Type, currency_Iso, customer_Ip_Address, locale_Iso, payment_Method, skip_Decision_Manager, transaction_type, profile_id)
VALUES
  (123456, 'ACCEPT', 'ACCEPT', 'Cukes-123456', 'Cukes Test', 'test@cukes.com', 'Test', 'Cukes', '1 Test St', 'Line 2', 'XX1 1XX', 'London', 'UK', 202512, '1234123412341234', 'VISA', 'GBP', '127.0.0.1', 'en', 'card', TRUE, 'create_payment_token','default');

INSERT INTO Payer_Auth_Enroll
  (pareq, xid, decision, reason_code, enrolled, payment_token, message, selected_result)
VALUES
  ('123456-pareq', '123456-xid', 'ACCEPT', 'ACCEPT', 'YES', 123456, 'message goes here', 'SUCCESS');
