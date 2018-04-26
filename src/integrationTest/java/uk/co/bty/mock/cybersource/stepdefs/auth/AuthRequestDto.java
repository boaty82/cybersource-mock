package uk.co.bty.mock.cybersource.stepdefs.auth;

import lombok.Data;

@Data
public class AuthRequestDto {
    
    private String currency;
    private String amount;
    
    private String title;
    private String firstName;
    private String lastName;
    private String line1;
    private String line2;
    private String city;
    private String postalCode;
    private String country;
    private String email;
    private String ipAddress;
    private String customerId;
    
}
