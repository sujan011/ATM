import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ATMSimulator {
    private static Map<String, Double> accountBalances = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nATM Simulator Menu:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

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
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void deposit() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your account number: ");
        String accountNumber = scanner.nextLine();

        if (!accountBalances.containsKey(accountNumber)) {
            System.out.println("Account not found. Please check your account number.");
            return;
        }

        System.out.print("Enter the deposit amount: $");
        double amount = scanner.nextDouble();

        double currentBalance = accountBalances.get(accountNumber);
        double newBalance = currentBalance + amount;
        accountBalances.put(accountNumber, newBalance);

        System.out.println("Deposit successful. New balance: $" + newBalance);
    }

    private static void withdraw() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your account number: ");
        String accountNumber = scanner.nextLine();

        if (!accountBalances.containsKey(accountNumber)) {
            System.out.println("Account not found. Please check your account number.");
            return;
        }

        System.out.print("Enter the withdrawal amount: $");
        double amount = scanner.nextDouble();

        double currentBalance = accountBalances.get(accountNumber);

        if (amount > currentBalance) {
            System.out.println("Insufficient funds. Withdrawal failed.");
        } else {
            double newBalance = currentBalance - amount;
            accountBalances.put(accountNumber, newBalance);
            System.out.println("Withdrawal successful. New balance: $" + newBalance);
        }
    }

    private static void transfer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your account number: ");
        String sourceAccount = scanner.nextLine();

        System.out.print("Enter the recipient's account number: ");
        String destinationAccount = scanner.nextLine();

        if (!accountBalances.containsKey(sourceAccount) || !accountBalances.containsKey(destinationAccount)) {
            System.out.println("Invalid account number(s). Please check your input.");
            return;
        }

        System.out.print("Enter the transfer amount: $");
        double amount = scanner.nextDouble();

        double sourceBalance = accountBalances.get(sourceAccount);

        if (amount > sourceBalance) {
            System.out.println("Insufficient funds. Transfer failed.");
        } else {
            double destinationBalance = accountBalances.get(destinationAccount);

            // Perform the transfer
            accountBalances.put(sourceAccount, sourceBalance - amount);
            accountBalances.put(destinationAccount, destinationBalance + amount);

            System.out.println("Transfer successful. New balance for " + sourceAccount + ": $" +
                    accountBalances.get(sourceAccount));
            System.out.println("New balance for " + destinationAccount + ": $" +
                    accountBalances.get(destinationAccount));
        }
    }

    private static void checkBalance() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your account number: ");
        String accountNumber = scanner.nextLine();

        if (!accountBalances.containsKey(accountNumber)) {
            System.out.println("Account not found. Please check your account number.");
            return;
        }

        System.out.println("Current balance for account " + accountNumber + ": $" +
                accountBalances.get(accountNumber));
    }
}