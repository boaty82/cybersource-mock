package uk.co.bty.mock.cybersource.dao.payerauth;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayerAuthEnrollRepository extends CrudRepository<PayerAuthEnroll, String>
{
	PayerAuthEnroll findByXid(final String xid);
}
