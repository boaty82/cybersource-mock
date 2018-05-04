package uk.co.bty.mock.cybersource.dao.txn;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import uk.co.bty.mock.cybersource.constants.AvsCode;
import uk.co.bty.mock.cybersource.constants.CvnCode;
import uk.co.bty.mock.cybersource.constants.ReasonCode;
import uk.co.bty.mock.cybersource.constants.TransactionDecision;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthTxn
{
	@Id
	private String id;

	@Enumerated(EnumType.STRING)
	private TransactionDecision decision;

	@Enumerated(EnumType.STRING)
	private ReasonCode reasonCode;

	@Enumerated(EnumType.STRING)
	private AvsCode avsCode;

	@Enumerated(EnumType.STRING)
	private CvnCode cvnCode;

	private String amount;

	private String authCode;

	private String txnTime;

	private String merchantId;
	
	private int tokenId;
}
