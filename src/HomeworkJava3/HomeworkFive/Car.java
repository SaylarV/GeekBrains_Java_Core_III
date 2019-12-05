package HomeworkJava3.HomeworkFive;

import java.util.Objects;
import java.util.concurrent.BrokenBarrierException;

class Car implements Runnable {
    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;

    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            Thread tr1 = new Thread(() ->{
                try {
                    System.out.println(this.name + " готовится");
                    Thread.sleep(500 + (int)(Math.random() * 800));
                    System.out.println(this.name + " готов");
                    MainClass.cbr.await();
                    MainClass.cdl.countDown();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < race.getStages().size(); i++) {
                    race.getStages().get(i).go(this);
                }
            });
            tr1.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
