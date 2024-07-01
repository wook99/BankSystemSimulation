package model;

import accounts.SavingsBankAccount;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Bank.
 */
public class Bank {

    private List<BankAccount> bankAccounts = new ArrayList<>();


    /**
     * Add bank account.
     *
     * @param bankAccount the bank account
     */
    public void addBankAccount(BankAccount bankAccount){

        for(BankAccount account: bankAccounts){
            if(bankAccount.getBankAccountNumber().equals(account.getBankAccountNumber())){
                System.out.println(account.bankAccountNumber+" Cannot create an account as the account Number Already exists");
                throw new IllegalArgumentException(account.bankAccountNumber+" Cannot create an account as the account Number Already exists ");
            }
        }

        bankAccounts.add(bankAccount);
    }

    /**
     * Remove account boolean.
     *
     * @param accountNumber the account number
     * @return the boolean
     */
    public boolean removeAccount(String accountNumber){
        for(BankAccount account: bankAccounts){
            if(account.getBankAccountNumber().equals(accountNumber)){
                this.bankAccounts.remove(account);
                System.out.println(account.bankAccountNumber+" Account is removed");
                return true;
            }
        }
        return false;
    }

    /**
     * Get account bank account.
     *
     * @param accountNumber the account number
     * @return the bank account
     */
    public BankAccount getAccount(String accountNumber){
        for(BankAccount account: bankAccounts){
            if(account.getBankAccountNumber().equals(accountNumber)){
                return account;
            }
        }
        return null;
    }

    /**
     * Add monthly interest.
     */
    public void addMonthlyInterest(){
        for(BankAccount account : bankAccounts){
            if(account.getBalance().compareTo(BigDecimal.ZERO) > 0){
                if(account instanceof SavingsBankAccount){
                    SavingsBankAccount savingsBankAccount = ((SavingsBankAccount)account);
                    BigDecimal interest = calculateMonthlyInterest(savingsBankAccount);
                    savingsBankAccount.addMonthlyInterest(interest);
                }

            }
        }
    }

    private BigDecimal calculateMonthlyInterest(SavingsBankAccount account){
        return account.getBalance()
                .multiply(account.getInterestRate())
                .divide(BigDecimal.valueOf(100), 3, RoundingMode.HALF_UP)
                .divide(BigDecimal.valueOf(12), 3, RoundingMode.HALF_UP); 

    }

    /**
     * Deduct income tax.
     */
    public void deductIncomeTax(){
        for(BankAccount account : bankAccounts){
            if(account.getBalance().compareTo(BigDecimal.ZERO) > 0){
                if(account instanceof SavingsBankAccount) {
                    SavingsBankAccount savingsBankAccount = ((SavingsBankAccount)account);
                    BigDecimal interest = calculateMonthlyInterest(savingsBankAccount);
                    BigDecimal tax = interest.multiply(savingsBankAccount.getTaxRate());
                    savingsBankAccount.deductIncomeTax(tax);
                }
            }
        }
    }

    /**
     * Deduct annual charges.
     */
    public void deductAnnualCharges(){
        for(BankAccount account : bankAccounts){
            if(account.getBalance().compareTo(BigDecimal.ZERO) > 0){
                if(account instanceof SavingsBankAccount){
                    SavingsBankAccount savingsBankAccount = ((SavingsBankAccount)account);
                    savingsBankAccount.deductAnnualCharge(savingsBankAccount.getAnnualChargeAmount());
                }

            }
        }
    }

}
