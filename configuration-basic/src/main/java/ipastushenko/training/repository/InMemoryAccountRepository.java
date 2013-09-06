package ipastushenko.training.repository;

import ipastushenko.training.domain.Account;

import java.util.HashMap;
import java.util.Map;

/**
 * InMemoryAccountRepository
 * @author Ivan Pastushenko
 * @version 1.0.0
 * Date: 07.09.2013
 */
public class InMemoryAccountRepository implements AccountRepository {
    private final Map<String, Account> accountsById = new HashMap<>();

    @Override
    public Account findById(String acctId) {
        return Account.copy(accountsById.get(acctId));
    }

    @Override
    public void update(Account account) {
        if (!accountsById.containsKey(account.getId()))
            throw new IllegalArgumentException("account [" + account.getId() + "] not found");
        accountsById.put(account.getId(), Account.copy(account));
    }

    @Override
    public void add(Account account) {
        if (accountsById.containsKey(account.getId()))
            throw new IllegalArgumentException("account [" + account.getId() + "] already exists");
        accountsById.put(account.getId(), Account.copy(account));
    }

}
