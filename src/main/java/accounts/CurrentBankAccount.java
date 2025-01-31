package accounts;

import model.BankAccount;
import model.Customer;
import util.AccountType;

import java.math.BigDecimal;
import java.util.List;

/**
 * The type Current bank account.
 */
public class CurrentBankAccount extends BankAccount {

    private final BigDecimal overDraftLimit = BigDecimal.valueOf(100);
    private final BigDecimal currentAccountOverdraftFee = BigDecimal.valueOf(35); // Fixed overdraft fee


    /**
     * Instantiates a new Current bank account.
     *
     * @param accountNumber the account number
     * @param customers     the customers
     * @param accountType   the account type
     */
    public CurrentBankAccount(String accountNumber, List<Customer> customers, AccountType accountType) {
        super(accountNumber,customers, accountType);

    }


    public synchronized  BigDecimal withdraw(BigDecimal amount){
        BigDecimal maxWithdrawalAmount = balance.add(overDraftLimit);
        if(amount.compareTo(BigDecimal.ZERO) > 0){
            if(amount.compareTo(maxWithdrawalAmount) <= 0){
                this.balance = this.balance.subtract(amount);
                System.out.println(this.bankAccountNumber +" Withdrawal successful : amount  :"+ amount + " | balance : "+ this.balance +" | Thread Name :"+ Thread.currentThread().getName());
            }
            else {
                System.out.println((this.bankAccountNumber + " Insufficient funds : amount : " + amount + "| balance : " + this.balance + " | Thread Name :"+ Thread.currentThread().getName()));
                throw new IllegalArgumentException(this.bankAccountNumber + "Insufficient funds : amount : " + amount + "| balance : " + this.balance);
            }
        }
        else {
            System.out.println((this.bankAccountNumber + "Amount you wish to withdraw cannot be 0 or below"));
            throw new IllegalArgumentException(this.bankAccountNumber + "Amount you wish to withdraw cannot be 0 or below");
        }
        if(balance.compareTo(BigDecimal.ZERO) < 0){
            this.balance = this.balance.subtract(currentAccountOverdraftFee);
            System.out.println(this.bankAccountNumber +  "Overdraft fee is applied for the account | the balance : "+this.balance + " | Thread Name :"+ Thread.currentThread().getName());
        }
        return amount;
    }

}
