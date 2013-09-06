package ipastushenko.training.domain;

import static java.lang.String.format;
/**
 * Account
 * @author Ivan Pastushenko
 * @version 1.0.0
 * Date: 07.09.2013
 */
public class Account {

    private final String id;
    private double balance;

    public Account(String id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public void debit(double amount) {
        balance -= amount;
    }

    public void credit(double amount) {
        balance += amount;
    }

    public static Account copy(Account src) {
        return new Account(src.getId(), src.getBalance());
    }

    @Override
    public String toString() {
        return format("Account: id=%s, balance=%.2f", getId(), getBalance());
    }
}
