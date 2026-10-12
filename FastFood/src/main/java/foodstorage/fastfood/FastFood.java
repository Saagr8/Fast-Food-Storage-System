/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package foodstorage.fastfood;


import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author ipman
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
    }
}
