package operations;

import model.Bank;

/**
 * The type Interest calculation manager.
 */
public class InterestCalculationManager implements Runnable{

    private Bank bank;

    /**
     * Instantiates a new Interest calculation manager.
     *
     * @param bank the bank
     */
    public InterestCalculationManager(Bank bank) {
        super();
        this.bank = bank;
    }

    @Override
    public void run() {
        try {
            bank.addMonthlyInterest();
        }
        catch (Exception e){
            System.out.println("Error occurred in adding monthly interest "+e.getMessage());
        }
    }
}
