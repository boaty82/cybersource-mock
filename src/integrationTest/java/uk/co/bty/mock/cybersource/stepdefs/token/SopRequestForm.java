package uk.co.bty.mock.cybersource.stepdefs.token;

import java.util.UUID;

import lombok.Data;

@Data
public class SopRequestForm
{
	//<editor-folder description="merchant config">
	private String access_key = "94f9893b8122872d05db1a39529a39e2";
	private String profile_id = "card_tokenisation_sop";
	//</editor-folder>

	private String bill_to_address_city = "London";
	private String bill_to_address_country = "UK";
	private String bill_to_address_line1 = "1 Test Street";
	private String bill_to_address_line2 = "A Locality";
	private String bill_to_address_postal_code = "NN10 0SS";
	private String bill_to_email = "test.dummy@domain.com";
	private String bill_to_forename = "test";
	private String bill_to_surname = "dummy";
	private String card_expiry_date;
	private String card_expiry_month;
	private String card_expiry_year;
	private String card_number;
	private String card_type;
	private String currency = "GBP";
	private String customer_ip_address = "127.0.0.1";
	private String locale = "en";
	/**
	 * save-card-details
	 */
	private String merchant_defined_data90 = "true";
	private String override_custom_cancel_page = "http://localhost:8080/cancel";
	private String override_custom_receipt_page = "http://localhost:8080/receipt";
	private String payment_method = "card";
	private String reference_number = UUID.randomUUID().toString();
	private String signature = "zRE0RnGPl5plniZaAATxSIUzJQzD7g2IfrdNjR8xs0=";
	private String signed_date_time = "2017-11-23T10:52:01Z";
	private String signed_field_names = "access_key,bill_to_address_city,bill_to_address_country,bill_to_address_line1,bill_to_address_line2,bill_to_address_postal_code," +
			"bill_to_email,bill_to_forename,bill_to_surname,currency,customer_ip_address,locale," +
			"override_custom_cancel_page,override_custom_receipt_page,payment_method,profile_id,reference_number," +
			"signed_date_time,signed_field_names,skip_decision_manager,transaction_type,transaction_uuid,unsigned_field_names";
	private String skip_decision_manager = "true";
	private String submit;
	private String transaction_type = "create_payment_token";
	private String transaction_uuid = UUID.randomUUID().toString();
	private String unsigned_field_names = "card_type,card_expiry_date,card_number,submit,merchant_defined_data90,card_expiry_year,card_expiry_month";

}
