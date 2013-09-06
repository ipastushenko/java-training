package ipastushenko.training.service;

/**
 * TransferService
 * @author Ivan Pastushenko
 * @version 1.0.0
 * Date: 07.09.2013
 */
public interface TransferService {
    void transfer(double amount, String srcAcctId, String destAcctId);
}
