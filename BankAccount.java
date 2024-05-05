import java.io.Serializable;
import java.util.ArrayList;




class BankAccount implements Serializable {
  
    private double balance;
    private ArrayList<String>  log = new ArrayList<String>();

  
    public BankAccount( double bal, ArrayList<String> l) {
      this.balance = bal;
      this.log = l;
        
    }

    public BankAccount() {
      this.balance = 0.0;
    }

  
    // logging funcs
    public void printLog(){
      for(int i = log.size() - 1; i >= 0; i--){
          System.out.println(log.get(i).toString());
      }
    }

    private void addLog( String a) {
      log.add(a);
      if (log.size() > 5) {
        log.remove(0);
      }
    }
    


    //banking funcs
    public double getBalance() {
        return balance;
    }
  
    public void deposit(double amount) {
      balance += amount;
      addLog("Deposited: $" + amount + " | Balance: $" + balance);
      System.out.println("Money has been deposited.");
      System.out.printf("New Balance: " + "%.2f\n", balance);
    }
  
    public void withdraw(double amount) {
    
      if (balance >= amount) {
          balance -= amount; 
          addLog("Withdrew: $" + amount + " | Balance: $" + balance);
          System.out.println("Money has been withdrawn.");
          System.out.printf("New balance: " + "%.2f\n", balance);
      }
      else {
          addLog("Attemped withdraw: $" + amount + " | Balance: $" + balance);
          System.out.println("Insufficient funds. Current balance: " + balance);
      }
      
    }
}
