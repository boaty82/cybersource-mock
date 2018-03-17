package uk.co.bty.mock.cybersource.controller.form;

import lombok.Data;

@Data
public class SopForm
{
	//<editor-folder description="merchant config">
	private String access_key;
	private String profile_id;
	//</editor-folder>
	private String bill_to_address_city;
	private String bill_to_address_country;
	private String bill_to_address_line1;
	private String bill_to_address_line2;
	private String bill_to_address_postal_code;
	private String bill_to_email;
	private String bill_to_forename;
	private String bill_to_surname;
	private String card_expiry_date;
	private String card_expiry_month;
	private String card_expiry_year;
	private String card_number;
	private String card_type;
	private String currency;
	private String customer_ip_address;
	private String locale;
	/**
	 * save-card-details
	 */
	private String merchant_defined_data90;
	private String override_custom_cancel_page;
	private String override_custom_receipt_page;
	private String payment_method;
	private String reference_number;
	private String signature;
	private String signed_date_time;
	private String signed_field_names;
	private String skip_decision_manager;
	private String submit;
	private String transaction_type;
	private String transaction_uuid;
	private String unsigned_field_names;
}
