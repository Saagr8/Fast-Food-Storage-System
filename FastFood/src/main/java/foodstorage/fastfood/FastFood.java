/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package foodstorage.fastfood;


import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author Jeremiah
 */
public class FastFood {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);// Reads the user input from the keyboard
        FoodStorage storage = new FoodStorage();// Creates the storage for food items.
        
        // Shows the title of the application.
        System.out.println("====================================");
        System.out.println(" FAST-FOOD STORAGE SYSTEM");
        System.out.println("====================================");

        //Lets the user choose how to store the food.
        int mode = chooseMode(scanner);
        boolean running = true;
        
        // Keeps showing the menu until the user exits.
        while (running) {
            showMenu(mode);
            int choice = readInt(scanner, "Choose an option"); // Get the option chosen by the user.
            
            // Runs the function based on the user choice.
            switch (choice){
                case 1:
                    addFood(scanner, storage);
                    break;
                
                case 2:
                    removeFood(storage, mode);
                    break;
                    
                case 3:
                    storage.displayAll();
                    break;
                    
                case 4:
                    peekFood(storage, mode);
                    break;
                    
                case 5:
                    searchFood(scanner, storage);
                    break;
                    
                case 6:
                    // Stop the program.
                    System.out.println("Application terminated. Goodbye!");
                    running = false;
                    break;
                
                default:
                    // Shows message incase the option is not valid.
                    System.out.println("Invalid option. Please choose 1 to 6.");
            }
       
        }
        
        // Closes the scanner when the program ends.
        scanner.close();
        
    }
    private static int chooseMode(Scanner scanner){
        System.out.println("Choose storage mode: ");
        System.out.println("1. Same side - Stack / LIFO");
        System.out.println("2. Opposite side - Queue / FIFO");
        
        while (true){
            int mode = readInt(scanner, "Enter 1 or 2: ");
            
            if (mode == 1 || mode == 2) {
                return mode;
            }
            
            System.out.println("Please enter only 1 or 2. ");
        
        }
   }  
   
    private static void showMenu(int mode){
        System.out.println("\n--------------- MENU ---------------");
        
        if (mode == 1) {
            System.out.println("Current mode: Stack /LIFO");
        } else {
            System.out.println("Current mode: Queue / FIFO");
        }
        System.out.println("1. Add food item");
        System.out.println("2. Remove food item");
        System.out.println("3. Display all food items");
        System.out.println("4. Peek next food item");
        System.out.println("5. Search food item by name");
        System.out.println("6. Exit");
        System.out.println("------------------------------");
    }
    
    private static void addFood(Scanner scanner, FoodStorage storage){
         if (storage.isFull()){
             System.out.println("Storage is full. Maximum capacity is 8 trays");
             return;
         }
         
         String name = readFoodName(scanner);
         double weight = readWeight(scanner);
         LocalDate bestBeforeDate = readBestBeforeDate(scanner);
         FoodItem item = new FoodItem(name, weight, bestBeforeDate);
         storage.addFood(item);
    }
    
    private static String readFoodName(Scanner scanner){
        while (true){
            System.out.println("Enter food name (Burger, Pizza, Fries, Sandwich, Hotdog): ");
            String name = scanner.nextLine().trim();
            
            if (name.equalsIgnoreCase("Burger")
                    || name.equalsIgnoreCase("Pizza")
                    || name.equalsIgnoreCase("Sandwich")
                    || name.equalsIgnoreCase("Hotdog")
                    || name.equalsIgnoreCase("Fries")){
                return name;
            }
            
            System.out.println("Invalid food name. Please choose one of the five foods. ");
        }
    }
    
    private static double readWeight(Scanner scanner){
        while (true) {
            System.out.println("Enter weight in grams: ");
            String input = scanner.nextLine().trim();
            
            try{
                double weight = Double.parseDouble(input);
                
                if (weight > 0){
                    return weight;
                }
                System.out.println("Weight must be greater than 0.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number for weight.");
            }
        }
    }
    
    private static LocalDate readBestBeforeDate(Scanner scanner){
        LocalDate today = LocalDate.now();
        LocalDate latestDate = today.plusDays(14);
        
        while (true) {
            System.out.println("Enter best-before date (YYYY-MM-DD, within 14 days): ");
            String input = scanner.nextLine().trim();
            
            try {
                LocalDate date = LocalDate.parse(input);
                
                if ( date.isBefore(today)){
                    System.out.println("Best-before date cannot be in the past.");
                } else if (date.isAfter(latestDate)) {
                    System.out.println("Best-before date cannot be more than 14 days away.");
                } else {
                    return date;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date. Use YYYY-MM-DD. ");
            }
        }
    }
    
    private static void removeFood(FoodStorage storage, int mode) {
        FoodItem removedItem;
        
        if (mode == 1) {
            removedItem = storage.removeFromFront();
        } else {
            removedItem = storage.removeFromOppositeSide();
        }
        
        if (removedItem != null) {
            System.out.println("Removed: " + removedItem.getName());
        }
    }
    
    private static void peekFood(FoodStorage storage, int mode) {
        FoodItem item;
        
        if (mode == 1) {
            item = storage.peekFront();
        } else {
            item = storage.peekOppositeSide();
        }
        
        if (item == null) {
            System.out.println("Storage is empty. No item to peek.");
        } else {
            System.out.println("Next food item: " + item.getName());
            System.out.println(item);
        }
    }
    
    private static void searchFood(Scanner scanner, FoodStorage storage) {
        if (storage.isEmpty()) {
            System.out.println("Storage is empty. Nothing to search.");
            return;
        }
        
        System.out.println("Enter food name to search: ");
        String name = scanner.nextLine().trim();
        FoodItem found = storage.searchByName(name);
        
        if (found == null) {
            System.out.println(name + " was not found in storage.");
        } else {
            System.out.println("Food item found: ");
            System.out.println(found);
        }
    }
    
    private static int readInt(Scanner scanner, String message){
        while (true) {
            System.out.println(message);
            String input = scanner.nextLine().trim();
            
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }
}