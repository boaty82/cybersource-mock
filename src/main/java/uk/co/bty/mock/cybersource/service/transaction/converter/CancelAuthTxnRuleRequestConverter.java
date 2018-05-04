package uk.co.bty.mock.cybersource.service.transaction.converter;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.core.convert.converter.Converter;

import uk.co.bty.mock.cybersource.dao.token.Token;
import uk.co.bty.mock.cybersource.dao.token.TokenRepository;
import uk.co.bty.mock.cybersource.dao.txn.AuthTxn;
import uk.co.bty.mock.cybersource.dao.txn.AuthTxnRepository;
import uk.co.bty.mock.cybersource.data.CancelAuthTxnRequestData;
import uk.co.bty.mock.cybersource.rules.data.CancelAuthTxnRuleData;
import uk.co.bty.mock.cybersource.schema.transaction.CCAuthReversalService;
import uk.co.bty.mock.cybersource.schema.transaction.RequestMessage;

public class CancelAuthTxnRuleRequestConverter implements Converter<RequestMessage, CancelAuthTxnRuleData>
{
    private TokenRepository tokenRepository;
    private AuthTxnRepository authTxnRepository;

    @Override
    public CancelAuthTxnRuleData convert(final RequestMessage source)
    {
        final AuthTxn authTxn = Optional.ofNullable(source.getCcAuthReversalService())
                .map(CCAuthReversalService::getAuthRequestID)
                .map(authTxnRepository::findOne)
                .orElseThrow(() -> new IllegalArgumentException("Unable to locate saved auth transaction"));
        
        final Token token = Optional.of(authTxn)
                .map(AuthTxn::getTokenId)
                .map(tokenRepository::findOne)
                .orElseThrow(() -> new IllegalArgumentException("Unable to locate saved token request"));
        
        final CancelAuthTxnRuleData target = new CancelAuthTxnRuleData();
        target.setProfileId(source.getMerchantID());
        target.setRequest(CancelAuthTxnRequestData.builder()
                .token(token)
                .request(source)
                .build());
        return target;
    }

    @Required
    public void setTokenRepository(final TokenRepository tokenRepository)
    {
        this.tokenRepository = tokenRepository;
    }

    @Required
    public void setAuthTxnRepository(final AuthTxnRepository authTxnRepository)
    {
        this.authTxnRepository = authTxnRepository;
    }

}
