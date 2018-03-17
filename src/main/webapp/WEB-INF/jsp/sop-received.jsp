<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:choose>
    <c:when test="${not empty rawForm}">${rawForm}</c:when>
    <c:otherwise>
        <html>
        <body>
            <form:form id="postBack" modelAttribute="responseForm" action="${redirectTo}" method="post">
                <form:input type='hidden' id='decision' path='decision'/>
                <form:input type='hidden' id='message' path='message'/>
                <form:input type='hidden' id='payment_token' path='payment_token'/>
                <form:input type='hidden' id='reason_code' path='reason_code'/>
                <form:input type='hidden' id='req_access_key' path='req_access_key'/>
                <form:input type='hidden' id='req_bill_to_address_city' path='req_bill_to_address_city'/>
                <form:input type='hidden' id='req_bill_to_address_country' path='req_bill_to_address_country'/>
                <form:input type='hidden' id='req_bill_to_address_line1' path='req_bill_to_address_line1'/>
                <form:input type='hidden' id='req_bill_to_address_line2' path='req_bill_to_address_line2'/>
                <form:input type='hidden' id='req_bill_to_address_postal_code' path='req_bill_to_address_postal_code'/>
                <form:input type='hidden' id='req_bill_to_email' path='req_bill_to_email'/>
                <form:input type='hidden' id='req_bill_to_forename' path='req_bill_to_forename'/>
                <form:input type='hidden' id='req_bill_to_surname' path='req_bill_to_surname'/>
                <form:input type='hidden' id='req_card_expiry_date' path='req_card_expiry_date'/>
                <form:input type='hidden' id='req_card_number' path='req_card_number'/>
                <form:input type='hidden' id='req_card_type' path='req_card_type'/>
                <form:input type='hidden' id='req_currency' path='req_currency'/>
                <form:input type='hidden' id='req_customer_ip_address' path='req_customer_ip_address'/>
                <form:input type='hidden' id='req_locale' path='req_locale'/>
                <form:input type='hidden' id='req_merchant_defined_data90' path='req_merchant_defined_data90'/>
                <form:input type='hidden' id='req_override_custom_cancel_page' path='req_override_custom_cancel_page'/>
                <form:input type='hidden' id='req_override_custom_receipt_page' path='req_override_custom_receipt_page'/>
                <form:input type='hidden' id='req_payment_method' path='req_payment_method'/>
                <form:input type='hidden' id='req_profile_id' path='req_profile_id'/>
                <form:input type='hidden' id='req_reference_number' path='req_reference_number'/>
                <form:input type='hidden' id='req_skip_decision_manager' path='req_skip_decision_manager'/>
                <form:input type='hidden' id='req_transaction_type' path='req_transaction_type'/>
                <form:input type='hidden' id='request_token' path='request_token'/>
                <form:input type='hidden' id='signature' path='signature'/>
                <form:input type='hidden' id='signed_date_time' path='signed_date_time'/>
                <form:input type='hidden' id='signed_field_names' path='signed_field_names'/>
                <form:input type='hidden' id='transaction_id' path='transaction_id'/>
            </form:form>
            <script type="text/javascript">
                window.onload = function(){
                    document.forms['postBack'].submit();
                }
            </script>
        </body>
        </html>
    </c:otherwise>
</c:choose>
