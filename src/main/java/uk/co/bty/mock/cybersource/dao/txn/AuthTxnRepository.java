package uk.co.bty.mock.cybersource.dao.txn;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthTxnRepository extends CrudRepository<AuthTxn, Integer>
{
}
