package ipastushenko.training.service;

import ipastushenko.training.domain.Account;
import ipastushenko.training.repository.AccountRepository;

/**
 * TransferServiceImpl
 * @author Ivan Pastushenko
 * @version 1.0.0
 * Date: 07.09.2013
 */
public class TransferServiceImpl implements TransferService {

    private final AccountRepository accountRepository;

    public TransferServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void transfer(double amount, String srcAcctId, String destAcctId) {
        Account srcAcct = accountRepository.findById(srcAcctId);
        Account destAcct = accountRepository.findById(destAcctId);

        srcAcct.debit(amount);
        destAcct.credit(amount);

        accountRepository.update(srcAcct);
        accountRepository.update(destAcct);
    }
}
