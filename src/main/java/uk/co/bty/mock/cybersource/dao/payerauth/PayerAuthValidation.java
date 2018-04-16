package uk.co.bty.mock.cybersource.dao.payerauth;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.bty.mock.cybersource.constants.Eci;
import uk.co.bty.mock.cybersource.constants.ParesStatus;
import uk.co.bty.mock.cybersource.constants.PayerAuthValidateCommerceIndicator;
import uk.co.bty.mock.cybersource.constants.PayerAuthValidateResult;
import uk.co.bty.mock.cybersource.constants.ReasonCode;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayerAuthValidation
{
	@Id
	@GeneratedValue
	private String id;

	private String pareq;

	private String xid;

	@Enumerated(EnumType.STRING)
	private ReasonCode reasonCode;

	@Enumerated(EnumType.STRING)
	private ParesStatus paresStatus;

	@Enumerated(EnumType.STRING)
	private PayerAuthValidateCommerceIndicator commerceIndicator;

	@Enumerated(EnumType.STRING)
	private PayerAuthValidateResult payerAuthValidateResult;

	@Enumerated(EnumType.STRING)
	private Eci eci;

	private int paymentToken;

	private String message;

	private String cavv;

	@PrePersist
	public void setDefaultValues()
	{
		if (this.cavv == null)
		{
			this.cavv = UUID.randomUUID().toString();
		}
	}
}
