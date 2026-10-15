/*

 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foodstorage.fastfood;
import java.util.ArrayDeque;

/**
 *
 * @author keo
 */

public class FoodStorage {

    private final int MAX_CAPACITY = 8;
    private ArrayDeque<FoodItem> storage;

    public FoodStorage() {
        storage = new ArrayDeque<>();
    }
    //Storage status methods(check if full, empty,or size)
public boolean isFull(){
    return storage.size()== MAX_CAPACITY;
}
public boolean isEmpty(){
    return storage.isEmpty();
}
public int getSize(){
    return storage.size();
}
}


