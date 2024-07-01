package operations;

import model.Bank;

/**
 * The type Annual charges calculation manager.
 */
public class AnnualChargesCalculationManager implements Runnable{

    private Bank bank;

    /**
     * Instantiates a new Annual charges calculation manager.
     *
     * @param bank the bank
     */
    public AnnualChargesCalculationManager(Bank bank) {
        this.bank = bank;
    }

    @Override
    public void run() {
        try {
            bank.deductAnnualCharges();
        }
        catch (Exception e){
            System.out.println("Error occurred in reducing annual charges "+e.getMessage());
        }
    }
}
