package HomeworkJava3.HomeworkOne.Ex3;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {
    private List<T> fruitList;
    private int count;
    private double totalWeight;

    public Box() {
        this.fruitList = new ArrayList<>();
    }

    public void add(T fruit) {
        fruitList.add(fruit);
        count = count + 1;
        totalWeight = count * fruit.getWeight();
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public boolean compare(Box fruit){
        if (this.totalWeight == fruit.totalWeight) {
            System.out.println("Все коробок совпадает.");
            return true;
        } else {
            System.out.println("Вес коробок не совпадает.");
            return false;
        }
    }

    public void changeBox(Box<T> fruit){
        fruit.fruitList.addAll(this.fruitList);
        fruit.count = this.count;
        fruit.totalWeight = this.totalWeight;
        this.fruitList.clear();
        this.count = 0;
        this.totalWeight = 0;
    }

    public void show() {
        System.out.println("Количество: " + count + "ед. Общий вес: " + totalWeight + "ед.");
    }


}