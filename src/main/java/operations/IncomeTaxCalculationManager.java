package operations;

import model.Bank;

/**
 * The type Income tax calculation manager.
 */
public class IncomeTaxCalculationManager implements Runnable{

    private Bank bank;

    /**
     * Instantiates a new Income tax calculation manager.
     *
     * @param bank the bank
     */
    public IncomeTaxCalculationManager(Bank bank) {
        super();
        this.bank = bank;
    }

    @Override
    public void run() {
        try {
            bank.deductIncomeTax();
        }
        catch (Exception e){
            System.out.println("Error occurred in reducing Income Tax "+e.getMessage());
        }
    }
}
