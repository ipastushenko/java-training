package ipastushenko.training.repository;

import ipastushenko.training.domain.Account;

/**
 * AccountRepository
 * @author Ivan Pastushenko
 * @version 1.0.0
 * Date: 07.09.2013
 */
public interface AccountRepository {

    Account findById(String acctId);

    void update(Account account);

    void add(Account account);

}
