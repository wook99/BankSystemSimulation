import accounts.CurrentBankAccount;
import accounts.SavingsBankAccount;
import model.Bank;
import model.BankAccount;
import model.Customer;
import model.Depositer;
import model.Receiver;
import operations.AnnualChargesCalculationManager;
import operations.IncomeTaxCalculationManager;
import operations.InterestCalculationManager;
import util.AccountType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static util.AccountType.REGULAR;

public class Application {

    public static void main(String[] args) {
        Bank bank = new Bank();

        Random random = new Random();

        List<Customer> customers = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();
        ArrayList<BankAccount> bankAccounts = new ArrayList<>();
        ArrayList<Thread> depositorThread = new ArrayList<>();
        ArrayList<Thread> receiverThread = new ArrayList<>();


        //Add customers with different customer ids to the customers list
        for (int i = 0; i < 20; i++) {
            customers.add(new Customer("C0"+i,"Tharindi","Hansika","0704339562","address","123456"));

        }

        // Add savingsBankAccounts and currentBankAccounts to the bankAccounts list
        for (int i = 0; i < 20; i++) {
            bankAccounts.add(new SavingsBankAccount("SavingsAccount : 0"+i, customers, i%2 ==0 ? AccountType.VIP: REGULAR));
            bankAccounts.add(new CurrentBankAccount("CurrentAccount : 0"+i, customers, i%2 ==0 ? AccountType.VIP: REGULAR));
        }

        //Add bank accounts to the bank
        for (BankAccount account : bankAccounts){
            bank.addBankAccount(account);
        }


        //Create threadGroups giving the maximum priority to the Bank manager, then to the VIP customer and norm priority to the regular customer
        ThreadGroup bankManager = new ThreadGroup("BankManager");
        bankManager.setMaxPriority(10);
        ThreadGroup customerVIP = new ThreadGroup("VIP Customer");
        customerVIP.setMaxPriority(6);
        ThreadGroup regularCustomer = new ThreadGroup("Regular Customer");
        regularCustomer.setMaxPriority(Thread.NORM_PRIORITY);


        Thread annualChargersCalculatormanagerThread = new Thread(bankManager,new AnnualChargesCalculationManager(bank),"Annual Chargers calculation manager");
        Thread incomeTaxCalculatormanagerThread = new Thread(bankManager,new IncomeTaxCalculationManager(bank),"Income Tax calculation manager");
        Thread interestCalculatormanagerThread = new Thread(bankManager,new InterestCalculationManager(bank),"Interest calculation manager");

        //Create threads for depositor and add to the depositor thread list
        for (int i = 0; i < 20; i++) {
            depositorThread.add(new Thread(bankAccounts.get(i).getAccountType().equals(REGULAR)? regularCustomer : customerVIP ,new Depositer(bankAccounts.get(i),BigDecimal.valueOf(random.nextInt(50000))),"Depositor: "+i));
        }

        //Create threads for receiver and add to the receiver thread list
        for (int i = 0; i < 20; i++) {
            receiverThread.add(new Thread(bankAccounts.get(i).getAccountType().equals(REGULAR)? regularCustomer : customerVIP ,new Receiver(customers.get(i),bankAccounts.get(i),BigDecimal.valueOf(random.nextInt(50000))),"Receiver: "+i));

        }


        for(Thread depositor : depositorThread){
            threads.add(depositor);
        }

        for(Thread receiver : receiverThread){
            threads.add(receiver);
        }

        threads.add(annualChargersCalculatormanagerThread);
        threads.add(incomeTaxCalculatormanagerThread);
        threads.add(interestCalculatormanagerThread);

        //Start all threads
        for (Thread thread: threads) {
            thread.start();
        }

    }
}
