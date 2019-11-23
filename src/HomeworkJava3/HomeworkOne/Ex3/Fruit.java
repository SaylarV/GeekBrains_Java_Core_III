package HomeworkJava3.HomeworkOne.Ex3;

public abstract class Fruit {
    private double weight;

    public Fruit(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }
}

class Orange extends Fruit{

    public Orange() {
        super(1.2);
    }
}

class Apple extends Fruit{

    public Apple() {
        super(1);
    }
}
