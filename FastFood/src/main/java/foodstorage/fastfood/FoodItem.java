/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package foodstorage.fastfood;

/**
 *
 * @author user
 */
import java.time.LocalDate;
import java.time.LocalDateTime;
public class FoodItem {
    
    private String name;
    private double weight;
    private LocalDate bestBeforeDate;
    private LocalDateTime placedTime;
    
    public FoodItem(String name, double weight, LocalDate bestBeforeDate) {
    this.name = name;
    this.weight = weight;
    this.bestBeforeDate = bestBeforeDate;
    this.placedTime = LocalDateTime.now();
}
    public String getName() {
    return name;
}

public double getWeight() {
    return weight;
}

public LocalDate getBestBeforeDate() {
    return bestBeforeDate;
}

public LocalDateTime getPlacedTime() {
    return placedTime;
}
@Override
public String toString() {
    return "Name: " + name
            + ", Weight: " + weight + "g"
            + ", Best Before: " + bestBeforeDate
            + ", Placed Time: " + placedTime;
}
}
