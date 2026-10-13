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
}
