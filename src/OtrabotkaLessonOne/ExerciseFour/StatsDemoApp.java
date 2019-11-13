package OtrabotkaLessonOne.ExerciseFour;

public class StatsDemoApp {
    public static void main(String[] args) {
        Stats<Integer> arrayOne = new Stats<>(1,2,3,4,5);
        System.out.println("Среднее значение arrayOne: " + arrayOne.avg());
        Stats<Double> arrayTwo = new Stats<>(2.0,2.0,2.0,2.0,2.0);
        System.out.println("Среднее значение arrayOne: " + arrayTwo.avg());
        if (arrayOne.sameAvg(arrayTwo)){
            System.out.println("Средние равны");
        } else System.out.println("Средние не равны");
    }
}
