INSERT INTO Token
  (payment_token, decision, reason_Code, request_Token, message, bill_Email, bill_Forename, bill_Surname, bill_Line1, bill_Line2, bill_Postal_Code, bill_City, bill_Country, expiry_Date_Iso, card_Number, card_Type, currency_Iso, customer_Ip_Address, locale_Iso, payment_Method, skip_Decision_Manager, transaction_type, profile_id)
VALUES
  (1, 'ACCEPT', 'ACCEPT', 'Cukes-1', 'Cukes Test', 'test@cukes.com', 'Test'  , 'Cukes'  , '1 Test St', 'Line 2', 'XX1 1XX', 'London', 'UK', 202512, '1234123412341234', 'VISA', 'GBP', '127.0.0.1', 'en', 'card', TRUE, 'create_payment_token','default'),
  (2, 'ACCEPT', 'ACCEPT', 'Cukes-2', 'Cukes Test', 'test@cukes.com', 'AUTH'  , 'DECLINE', '1 Test St', 'Line 2', 'XX1 1XX', 'London', 'UK', 202512, '1234123412341234', 'VISA', 'GBP', '127.0.0.1', 'en', 'card', TRUE, 'create_payment_token','default'),
  (3, 'ACCEPT', 'ACCEPT', 'Cukes-3', 'Cukes Test', 'test@cukes.com', 'AUTH'  , 'REVIEW' , '1 Test St', 'Line 2', 'XX1 1XX', 'London', 'UK', 202512, '1234123412341234', 'VISA', 'GBP', '127.0.0.1', 'en', 'card', TRUE, 'create_payment_token','default'),
  (4, 'ACCEPT', 'ACCEPT', 'Cukes-4', 'Cukes Test', 'test@cukes.com', 'AUTH'  , 'ACCEPT' , '1 Test St', 'Line 2', 'XX1 1XX', 'London', 'UK', 202512, '1234123412341234', 'VISA', 'GBP', '127.0.0.1', 'en', 'card', TRUE, 'create_payment_token','default'),
  (5, 'ACCEPT', 'ACCEPT', 'Cukes-5', 'Cukes Test', 'test@cukes.com', 'CANCEL', 'ACCEPT' , '1 Test St', 'Line 2', 'XX1 1XX', 'London', 'UK', 202512, '1234123412341234', 'VISA', 'GBP', '127.0.0.1', 'en', 'card', TRUE, 'create_payment_token','test_gbp_ecommerce');

INSERT INTO Payer_Auth_Enroll
  (pareq, xid, decision, reason_code, enrolled, payment_token, message, selected_result)
VALUES
  ('1-pareq', '123456-xid', 'ACCEPT', 'ACCEPT', 'YES', 123456, 'message goes here', 'SUCCESS');

INSERT INTO Auth_Txn
  (id, decision, reason_code, avs_code, cvn_code, amount, auth_code, txn_time, merchant_id, token_id)
VALUES
  (5, 'ACCEPT', 'ACCEPT', 'MATCH', 'MATCH', '2.00', 'authcode-5', '2018-01-01T12:00:01.000Z', 'test_gbp_ecommerce', 5);
