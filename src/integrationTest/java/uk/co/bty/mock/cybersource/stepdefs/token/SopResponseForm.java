package uk.co.bty.mock.cybersource.stepdefs.token;

import lombok.Getter;
import lombok.Value;

@Getter
@Value
public class SopResponseForm
{
	private String decision;
	private String message;
	private String payment_token;
	private String reason_code;
	private String req_access_key;
	private String req_bill_to_address_city;
	private String req_bill_to_address_country;
	private String req_bill_to_address_line1;
	private String req_bill_to_address_line2;
	private String req_bill_to_address_postal_code;
	private String req_bill_to_email;
	private String req_bill_to_forename;
	private String req_bill_to_surname;
	private String req_card_expiry_date;
	private String req_card_number;
	private String req_card_type;
	private String req_currency;
	private String req_customer_ip_address;
	private String req_locale;
	/**
	 * save-card-details
	 */
	private String req_merchant_defined_data90;
	private String req_override_custom_cancel_page;
	private String req_override_custom_receipt_page;
	private String req_payment_method;
	private String req_profile_id;
	private String req_reference_number;
	private String req_skip_decision_manager;
	private String req_transaction_type;
	private String req_transaction_uuid;
	private String request_token;
	private String signature;
	private String signed_date_time;
	private String signed_field_names;
	private String transaction_id;
	private String invalid_fields;

}

