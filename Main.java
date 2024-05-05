import java.util.Scanner;
import java.io.*;


public class Main {
public static void main(String[] args) {
  Scanner scnr = new Scanner(System.in);
  Boolean mainState = true;
  BankAccount account = null;



  // Java's really fun way of shipping objects around, not a fun read but seems pretty important
  /* oh yeah this loads the previous profile from the base folder where the app is running, if it doesn't exist well it makes 
  an empty BankAccount object bcuz the user has never banked with us before and missing out on out fantastic negative interest rates */ 
 
  try {
    FileInputStream fileIn = new FileInputStream("bankDataVerySafeBasicallyPlainText.ser");
    ObjectInputStream in = new ObjectInputStream(fileIn);
    account = (BankAccount) in.readObject();
    in.close();
    fileIn.close();
    System.out.println("Welcome back to the bank app!");
  } catch (IOException i) {
      // i.printStackTrace();
      System.out.println("Welcome to the bank app!");
      account = new BankAccount();
      
  } catch (ClassNotFoundException c) {
      // c.printStackTrace();
      System.out.println("Welcome to the bank app!");
      account = new BankAccount();

  }

   
    
  
  while(mainState) {

    
    System.out.println("\n\nWhat would you like to do? (type letter)");
    System.out.println("A) Deposit");
    System.out.println("B) Withdraw");
    System.out.println("C) View Past Actions");
    System.out.println("D) Exit\n");

    String option = scnr.next();

    //option one - deposit
    if (option.equalsIgnoreCase("A")) {
      System.out.print("\n\nEnter Amount to Deposit: ");
      double addAmount = scnr.nextDouble();
       //sanatizing input
      if (addAmount < 0) {
        System.out.println("You can't deposit a negative amount.");
        continue;
      }
      account.deposit(addAmount);
    }

    //option 2 - withdraw
    else if (option.equalsIgnoreCase("B")) {
      System.out.print("\n\nEnter Amount to Withdraw: ");
      double withAmount = scnr.nextDouble();
      //sanatizing input
      if (withAmount < 0) {
        System.out.println("You can't Withdraw a negative amount.");
        continue;
      }
      account.withdraw(withAmount);
    }

    //option 3 - view log
    else if (option.equalsIgnoreCase("C")) {
      System.out.print("\n\n");
      account.printLog();
      
    }

    //exit 
    else if (option.equalsIgnoreCase("D")) {
      // exit loop
      mainState = false;

      // runs saving serializer
      try {
          FileOutputStream fileOut = new FileOutputStream("bankDataVerySafeBasicallyPlainText.ser");
          ObjectOutputStream out = new ObjectOutputStream(fileOut);
          out.writeObject(account);
          out.close();
          fileOut.close();
          System.out.println("\n\nSerialized data is saved in bankDataVerySafeBasicallyPlainText.ser");
      } catch (IOException i) {
          i.printStackTrace();
      }

        System.out.println("\n\n\n\n Thank you for using the Bank App!");
        scnr.close();
      } else {
        System.out.println("Invalid choice. Please choose another option.");
      }
    }

  }

}