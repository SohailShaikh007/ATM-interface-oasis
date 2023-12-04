import java.util.Scanner;

public class ATMInterface {
    private double balance;
    private TransactionHistory transactionHistory;

    public ATMInterface() {
        this.balance = 1000.0;  // Initial balance
        this.transactionHistory = new TransactionHistory();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;

        while (!quit) {
            System.out.println("ATM Interface:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdrawal");
            System.out.println("3. Transfer");
            System.out.println("4. Check Balance");
            System.out.println("5. Transaction History");
            System.out.println("6. Quit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    deposit();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    transfer();
                    break;
                case 4:
                    checkBalance();
                    break;
                case 5:
                    viewTransactionHistory();
                    break;
                case 6:
                    quit = true;
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private void deposit() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the deposit amount: ");
        double amount = scanner.nextDouble();

        balance += amount;
        transactionHistory.addTransaction("Deposit", amount);
        System.out.println("Deposit successful. New balance: $" + balance);
    }

    private void withdraw() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the withdrawal amount: ");
        double amount = scanner.nextDouble();

        if (amount <= balance) {
            balance -= amount;
            transactionHistory.addTransaction("Withdrawal", -amount);
            System.out.println("Withdrawal successful. New balance: $" + balance);
        } else {
            System.out.println("Insufficient funds. Withdrawal failed.");
        }
    }

    private void transfer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the transfer amount: ");
        double amount = scanner.nextDouble();

        if (amount <= balance) {
            balance -= amount;
            transactionHistory.addTransaction("Transfer (Outgoing)", -amount);

            System.out.print("Enter the recipient's account: ");
            String recipient = scanner.next();
            transactionHistory.addTransaction("Transfer (Incoming) - " + recipient, amount);

            System.out.println("Transfer successful. New balance: $" + balance);
        } else {
            System.out.println("Insufficient funds. Transfer failed.");
        }
    }

    private void checkBalance() {
        System.out.println("Current Balance: $" + balance);
    }

    private void viewTransactionHistory() {
        System.out.println("Transaction History:");
        transactionHistory.printHistory();
    }

    public static void main(String[] args) {
        ATMInterface atmInterface = new ATMInterface();
        atmInterface.run();
    }
}

class TransactionHistory {
    private StringBuilder history;

    public TransactionHistory() {
        this.history = new StringBuilder();
    }

    public void addTransaction(String type, double amount) {
        history.append(type).append(": $").append(amount).append("\n");
    }

    public void printHistory() {
        System.out.println(history.toString());
    }
}
