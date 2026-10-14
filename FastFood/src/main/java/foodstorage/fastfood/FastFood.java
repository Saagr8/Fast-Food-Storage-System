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
        Scanner scanner = new Scanner(System.in);
        FoodStorage storage = new FoodStorage();
        
        
        System.out.println("====================================");
        System.out.println(" FAST-FOOD STORAGE SYSTEM");
        System.out.println("====================================");

        int mode = chooseMode(scanner);
        boolean running = true;
        
        while (running) {
            showMenu(mode);
            int choice = readInt(scanner, "Choose an option");
            
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
                    System.out.println("Application terminated. Goodbye!");
                    running = false;
                    break;
                
                default:
                    System.out.println("Invalid option. Please choose 1 to 6.");
            }
       
        }
        
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
}