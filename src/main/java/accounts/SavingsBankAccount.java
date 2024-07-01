package accounts;

import model.BankAccount;
import model.Customer;
import util.AccountType;

import java.math.BigDecimal;
import java.util.List;

/**
 * The type Savings bank account.
 */
public class SavingsBankAccount  extends BankAccount {

    private final BigDecimal interestRate = BigDecimal.valueOf(5);

    private final BigDecimal annualChargeAmount = BigDecimal.valueOf(100);

    private final BigDecimal taxRate = BigDecimal.valueOf(0.36); // 36% tax rate


    /**
     * Instantiates a new Savings bank account.
     *
     * @param accountNumber the account number
     * @param customers     the customers
     * @param accountType   the account type
     */
    public SavingsBankAccount(String accountNumber, List<Customer> customers, AccountType accountType) {
        super(accountNumber,customers, accountType);
    }


    /**
     * Gets interest rate.
     *
     * @return the interest rate
     */
    public BigDecimal getInterestRate() {
        return interestRate;
    }

    /**
     * Gets annual charge amount.
     *
     * @return the annual charge amount
     */
    public BigDecimal getAnnualChargeAmount() {
        return annualChargeAmount;
    }

    /**
     * Gets tax rate.
     *
     * @return the tax rate
     */
    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public synchronized BigDecimal withdraw(BigDecimal amount){
        BigDecimal amountAvailable = balance;
        if(amount.compareTo(BigDecimal.ZERO) > 0){
            if(amount.compareTo(amountAvailable) <= 0){
                this.balance = this.balance.subtract(amount);
                System.out.println(this.bankAccountNumber +" Withdrawal successful : amount  :"+ amount + " | balance : "+ this.balance + " | Thread Name :"+ Thread.currentThread().getName());
            }
            else {
                System.out.println((this.bankAccountNumber + " Insufficient funds : amount : " + amount + "| balance : " + this.balance + " | Thread Name :"+ Thread.currentThread().getName()));
                throw new IllegalArgumentException(this.bankAccountNumber + "Insufficient funds : amount : " + amount + "| balance : " + this.balance);
            }
        }
        else {
            System.out.println((this.bankAccountNumber + "Amount you wish to withdraw cannot be 0 or below"+ " | Thread Name :"+ Thread.currentThread().getName()));
            throw new IllegalArgumentException(this.bankAccountNumber + "Amount you wish to withdraw cannot be 0 or below");
        }
        return amount;
    }

    /**
     * Deduct annual charge.
     *
     * @param amount the amount
     */
    public synchronized void deductAnnualCharge(BigDecimal amount){
        if(amount.compareTo(BigDecimal.ZERO) > 0){
            this.balance = this.balance.subtract(amount);
            System.out.println(this.bankAccountNumber +" Annual charges deducted successfully : amount  :"+ amount + " | balance : "+ this.balance + " | Thread Name :"+ Thread.currentThread().getName());
        }
        else
            throw new IllegalArgumentException(this.bankAccountNumber + "Amount you wish to deduct annual charges cannot be 0 or below");
    }

    /**
     * Add monthly interest.
     *
     * @param amount the amount
     */
    public synchronized void addMonthlyInterest(BigDecimal amount){
        if(amount.compareTo(BigDecimal.ZERO) > 0){
            this.balance = this.balance.add(amount);
            System.out.println(this.bankAccountNumber +" Monthly interest is added successfully : amount  :"+ amount + " | balance : "+ this.balance + " | Thread Name :"+ Thread.currentThread().getName());
        }
        else
            throw new IllegalArgumentException(this.bankAccountNumber + " Amount you wish to add interest cannot be 0 or below");
    }

    /**
     * Deduct income tax.
     *
     * @param amount the amount
     */

    //even the balance goes 0 need to execute this
    public synchronized void deductIncomeTax(BigDecimal amount){
        if(amount.compareTo(BigDecimal.ZERO) > 0){
            this.balance = this.balance.subtract(amount);
            System.out.println(this.bankAccountNumber +" Income Tax deducted successfully : amount  :"+ amount + " | balance : "+ this.balance + " | Thread Name :"+ Thread.currentThread().getName());
        }
        else
            throw new IllegalArgumentException(this.bankAccountNumber +" Amount you wish to deduct tax cannot be 0 or below");
    }


}
