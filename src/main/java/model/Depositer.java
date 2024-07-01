package model;

import java.math.BigDecimal;

/**
 * The type Depositer.
 */
public class Depositer implements Runnable{

    private BankAccount account;

    private BigDecimal amount;

    /**
     * Instantiates a new Depositer.
     *
     * @param account the account
     * @param amount  the amount
     */
    public Depositer(BankAccount account, BigDecimal amount) {
        super();
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void run() {
        try{
            account.deposit(amount);

        }
        catch (Exception e){
            System.out.println("Error in deposit "+e.getMessage());
        }
    }
}
