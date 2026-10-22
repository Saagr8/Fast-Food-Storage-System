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
    
    // Allows the user to choose Stack or Queue mode.
    private static int chooseMode(Scanner scanner){
        System.out.println("Choose storage mode: ");
        System.out.println("1. Same side - Stack / LIFO");
        System.out.println("2. Opposite side - Queue / FIFO");
        
        while (true){
            int mode = readInt(scanner, "Enter 1 or 2: ");
            
            // Accepts only option 1 or 2.
            if (mode == 1 || mode == 2) {
                return mode;
            }
            
            System.out.println("Please enter only 1 or 2. ");
        
        }
   }  
   
    // Displays the menu and the current storage mode.
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
    
    // Gets the food details and adds the item to storage.
    private static void addFood(Scanner scanner, FoodStorage storage){
        
        // Checks if there is space before adding a new item.
         if (storage.isFull()){
             System.out.println("Storage is full. Maximum capacity is 8 trays");
             return;
         }
         
         // Get the food name, weight and best-before date.
         String name = readFoodName(scanner);
         double weight = readWeight(scanner);
         LocalDate bestBeforeDate = readBestBeforeDate(scanner);
         FoodItem item = new FoodItem(name, weight, bestBeforeDate); // Creates a new food item using the details entered.
         storage.addFood(item); // Adds the new item to storage.
    }
    
    // Checks if the food name is one of the allowed choices.
    private static String readFoodName(Scanner scanner){
        while (true){
            System.out.println("Enter food name (Burger, Pizza, Fries, Sandwich, Hotdog): ");
            String name = scanner.nextLine().trim();
            
            // Accepts the five food names without checking capital letters.
            if (name.equalsIgnoreCase("Burger")
                    || name.equalsIgnoreCase("Pizza")
                    || name.equalsIgnoreCase("Sandwich")
                    || name.equalsIgnoreCase("Hotdog")
                    || name.equalsIgnoreCase("Fries")){
                return name;
            }
            
            // Ask again if the name is not allowed.
            System.out.println("Invalid food name. Please choose one of the five foods. ");
        }
    }
    
    // Checks the weight is a valid number greater than 0.
    private static double readWeight(Scanner scanner){
        while (true) {
            System.out.println("Enter weight in grams: ");
            String input = scanner.nextLine().trim();
            
            try{
                double weight = Double.parseDouble(input);
                
                // Weight must be positive.
                if (weight > 0){
                    return weight;
                }
                System.out.println("Weight must be greater than 0.");
            } catch (NumberFormatException e) {
                // Handles input that is not a valid number.
                System.out.println("Please enter a valid number for weight.");
            }
        }
    }
    
    // Check the best-before date is today or within the next 14 days.
    private static LocalDate readBestBeforeDate(Scanner scanner){
        LocalDate today = LocalDate.now();
        LocalDate latestDate = today.plusDays(14);
        
        while (true) {
            System.out.println("Enter best-before date (YYYY-MM-DD, within 14 days): ");
            String input = scanner.nextLine().trim();
            
            try {
                LocalDate date = LocalDate.parse(input);
                
                if ( date.isBefore(today)){
                    System.out.println("Best-before date cannot be in the past."); // Rejects if dates have passed.
                } else if (date.isAfter(latestDate)) {
                    System.out.println("Best-before date cannot be more than 14 days away."); // Rejects if dates more than 14 days away.
                } else {
                    return date;
                }
            } catch (DateTimeParseException e) {
                // Handles dates entered in the wrong format.
                System.out.println("Invalid date. Use YYYY-MM-DD. ");
            }
        }
    }
    
    // Removes a food item based on the selected mode.
    private static void removeFood(FoodStorage storage, int mode) {
        FoodItem removedItem;
        
        // Stack removes from the front.
        if (mode == 1) {
            removedItem = storage.removeFromFront();
        } else {
            // Queue removes from the opposite side.
            removedItem = storage.removeFromOppositeSide();
        }
        
        // Shows the name if an item was removed.
        if (removedItem != null) {
            System.out.println("Removed: " + removedItem.getName());
        }
    }
    
    // Shows the next food item without removing it.
    private static void peekFood(FoodStorage storage, int mode) {
        FoodItem item;
        
        // Check the front in Stack mode.
        if (mode == 1) {
            item = storage.peekFront();
        } else {
            // Check the opposite side in Queue mode.
            item = storage.peekOppositeSide();
        }
        
        if (item == null) {
            // Show a message when storage is empty.
            System.out.println("Storage is empty. No item to peek.");
        } else {
            System.out.println("Next food item: " + item.getName());
            System.out.println(item);
        }
    }
    
    // Searches for a food item using its name.
    private static void searchFood(Scanner scanner, FoodStorage storage) {
        // No search can be done if storage is empty.
        if (storage.isEmpty()) {
            System.out.println("Storage is empty. Nothing to search.");
            return;
        }
        
        System.out.println("Enter food name to search: ");
        String name = scanner.nextLine().trim();
        FoodItem found = storage.searchByName(name);// Looks for a matching food item in storage.
        
        if (found == null) {
            System.out.println(name + " was not found in storage.");
        } else {
            System.out.println("Food item found: ");
            System.out.println(found);
        }
    }
    
    // Read a whole number and ask again if the input is invalid
    private static int readInt(Scanner scanner, String message){
        while (true) {
            System.out.println(message);
            String input = scanner.nextLine().trim();
            
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                // Handles letters or other invalid menu input.
                System.out.println("Please enter a valid whole number.");
            }
        }
    }
}