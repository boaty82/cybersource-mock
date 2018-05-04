package uk.co.bty.mock.cybersource.dao.txn;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uk.co.bty.mock.cybersource.constants.ReasonCode;
import uk.co.bty.mock.cybersource.constants.TransactionDecision;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CancelAuthTxn 
{
    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private TransactionDecision decision;

    @Enumerated(EnumType.STRING)
    private ReasonCode reasonCode;

    private String amount;

    private String txnTime;

    private String originalAuthId;
}
