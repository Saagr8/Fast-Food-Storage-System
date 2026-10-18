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

//Method to add a food item. Checks if storage is full before adding
public void addFood(FoodItem item){
    if (isFull()){
        System.out.println("Storage is full. Maximum capacity is 8 trays.");
        return;
    }
    storage.addFirst(item);
    System.out.println(item.getName() + "added successfully.");
}
//Add removal methods (front and opposite)
public FoodItem removeFromFront(){
    if (isEmpty()){
        System.out.println("Storage is empty. Nothing to remove.");
        return null;
    }
    return storage.removeFirst();
    
}

public FoodItem removeFromOppositeSide(){
    if (isEmpty()){
        System.out.println("Storage is empty. Nothing to remove.");
        return null;
    }
    return storage.removeLast();
}
//Add peek methods and search functionality.
public FoodItem peekFront(){
    if  (isEmpty()){
        return null;
    }
    return storage.peekFirst();
}
public FoodItem peekOppositeSide(){
    if (isEmpty()){
        return null;
    }
    return storage.peekLast();
}
public FoodItem searchByName(String name){
    for (FoodItem item : storage){
        if (item.getName().equalsIgnoreCase(name)){
          return item;  
        }
    }
    return null;
}
// Display all items in storage.
public void displayAll(){
    if (isEmpty()){
        System.out.println("Storage is empty.");
        return;
    }
    System.out.println("Current trays in storage:");
    for (FoodItem item : storage){
        System.out.println("-" + item.getName());
    }
    System.out.println("Used:" + getSize() + "/" + MAX_CAPACITY);
}

}


