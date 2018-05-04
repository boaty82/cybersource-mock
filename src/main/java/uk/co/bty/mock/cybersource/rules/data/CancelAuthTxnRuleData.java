package uk.co.bty.mock.cybersource.rules.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uk.co.bty.mock.cybersource.data.CancelAuthTxnRequestData;
import uk.co.bty.mock.cybersource.data.CancelAuthTxnResponseData;
import uk.co.bty.mock.cybersource.rules.RuleType;

@Data
@EqualsAndHashCode(callSuper=true)
public class CancelAuthTxnRuleData extends RuleData<CancelAuthTxnRequestData, CancelAuthTxnResponseData> 
{
    private String responseId;
    
    public CancelAuthTxnRuleData() 
    {
        super(RuleType.CANCEL_AUTH_TXN);
    }
}
