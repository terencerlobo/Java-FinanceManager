package fileOperations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Expense;

public class FileManager {
	
	private static final String FILE_NAME = "expenses.csv";

    public static void saveExpense(Expense expense) throws IOException {
        String dataLine = expense.toString() + "\n";
        Files.write(Paths.get(FILE_NAME), dataLine.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    public static List<Expense> loadExpenses() throws IOException {
        List<Expense> expenses = new ArrayList<>();
        List<String> lines = Files.readAllLines(Paths.get(FILE_NAME));
        for (String line : lines) {
            String[] data = line.split(", ");
            Expense expense = new Expense(LocalDate.parse(data[0]), data[1], Double.parseDouble(data[2]), data[3]);
            expenses.add(expense);
        }
        return expenses;
    }

}
