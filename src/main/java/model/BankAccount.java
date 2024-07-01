package model;

import util.AccountType;

import java.math.BigDecimal;
import java.util.List;

/**
 * The type Bank account.
 */
public abstract class BankAccount {

    /**
     * The Bank account number.
     */
    protected String bankAccountNumber;


    //for joint accounts
    private List<Customer> customers;

    //accountType
    private AccountType accountType;


    /**
     * The Balance.
     */
    protected BigDecimal balance;


    /**
     * Instantiates a new Bank account.
     *
     * @param bankAccountNumber the bank account number
     * @param customers         the customers
     * @param accountType       the account type
     */
    public BankAccount(String bankAccountNumber,  List<Customer> customers, AccountType accountType) {
        this.bankAccountNumber = bankAccountNumber;
        this.customers = customers;
        this.accountType = accountType;
        this.balance = BigDecimal.ZERO;
    }


    /**
     * Gets bank account number.
     *
     * @return the bank account number
     */
    public synchronized String getBankAccountNumber() {
        return bankAccountNumber;
    }

    /**
     * Get balance big decimal.
     *
     * @return the big decimal
     */
    public synchronized BigDecimal getBalance(){
        return balance;
    }


    /**
     * Gets customers.
     *
     * @return the customers
     */
    public List<Customer> getCustomers() {
        return customers;
    }

    /**
     * Gets account type.
     *
     * @return the account type
     */
    public AccountType getAccountType() {
        return accountType;
    }

    /**
     * Sets customers.
     *
     * @param customer the customer
     */
    public void setCustomers(Customer customer) {
        this.customers.add(customer);
    }

    /**
     * Sets account type.
     *
     * @param accountType the account type
     */
    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }


    /**
     * Remove customer boolean.
     *
     * @param customerId the customer id
     * @return the boolean
     */
    public boolean removeCustomer(String customerId){
        for(Customer customer: customers){
            if(customer.getCustomerId().equals(customerId)){
                this.customers.remove(customer);
                return false;
            }
        }
        return true;
    }

    /**
     * Is owner boolean.
     *
     * @param customer the customer
     * @return the boolean
     */
    public boolean isOwner(Customer customer) {
        return customers.contains(customer);
    }

    /**
     * Withdraw big decimal.
     *
     * @param amount the amount
     * @return the big decimal
     */
    public abstract BigDecimal withdraw(BigDecimal amount);

    /**
     * Deposit.
     *
     * @param amount the amount
     */
    public synchronized void deposit(BigDecimal amount){
        if(amount.compareTo(BigDecimal.ZERO) > 0){
            this.balance = this.balance.add(amount);
            System.out.println(this.bankAccountNumber +" Deposit successfully : amount  :"+ amount + " | balance : "+ this.balance + " | Thread Name :"+ Thread.currentThread().getName());

        }
        else
            throw new IllegalArgumentException(this.bankAccountNumber +" Amount you wish to deposit cannot be 0 or below");
    }





}
