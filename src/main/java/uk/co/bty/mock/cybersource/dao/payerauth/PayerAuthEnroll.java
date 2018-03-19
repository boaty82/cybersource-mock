package uk.co.bty.mock.cybersource.dao.payerauth;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import org.hibernate.annotations.GenericGenerator;

import uk.co.bty.mock.cybersource.constants.PayerAuthValidateResult;
import uk.co.bty.mock.cybersource.constants.ReasonCode;
import uk.co.bty.mock.cybersource.constants.TransactionDecision;
import uk.co.bty.mock.cybersource.constants.VeresEnrolled;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayerAuthEnroll
{

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String pareq;

	private String xid;

	@Enumerated(EnumType.STRING)
	private TransactionDecision decision;

	@Enumerated(EnumType.STRING)
	private ReasonCode reasonCode;

	@Enumerated(EnumType.STRING)
	private VeresEnrolled enrolled;

	private int paymentToken;

	private String message;

	@Enumerated(EnumType.STRING)
	private PayerAuthValidateResult selectedResult;

	@PrePersist
	public void setDefaultValues()
	{
		if (this.xid == null)
		{
			this.xid = UUID.randomUUID().toString();
		}
	}
}
