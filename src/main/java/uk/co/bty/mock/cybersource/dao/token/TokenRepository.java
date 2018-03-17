package uk.co.bty.mock.cybersource.dao.token;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends CrudRepository<Token, Integer>
{
}
