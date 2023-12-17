package app;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import fileOperations.FileManager;
import model.Expense;

public class PersonalFinanceManager {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Add Expense  2. View Expenses  3. Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addExpense();
                    break;
                case 2:
                    viewExpenses();
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }

    private static void addExpense() {
        System.out.println("Enter details for new expense:");
        System.out.print("Date (YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(scanner.next());
        System.out.print("Category: ");
        String category = scanner.next();
        System.out.print("Amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();  // Consume newline
        System.out.print("Description: ");
        String description = scanner.nextLine();

        Expense expense = new Expense(date, category, amount, description);
        try {
            FileManager.saveExpense(expense);
            System.out.println("Expense saved.");
        } catch (IOException e) {
            System.out.println("Error saving expense: " + e.getMessage());
        }
    }

    private static void viewExpenses() {
        try {
            List<Expense> expenses = FileManager.loadExpenses();
            for (Expense expense : expenses) {
                System.out.println(expense);
            }
        } catch (IOException e) {
            System.out.println("Error loading expenses: " + e.getMessage());
        }
    }

}
