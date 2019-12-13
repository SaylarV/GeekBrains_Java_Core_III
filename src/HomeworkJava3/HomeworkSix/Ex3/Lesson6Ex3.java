package HomeworkJava3.HomeworkSix.Ex3;

import java.util.ArrayList;
import java.util.Arrays;

/*
Написать метод, который проверяет состав массива из чисел 1 и 4.
Если в нем нет хоть одной четверки или единицы, то метод вернет false;
Написать набор тестов для этого метода (по 3-4 варианта входных данных).
 */
public class Lesson6Ex3 {
    private static int search1 = 1;
    private static int search4 = 4;
    private static int[] arr = new int[10];
    private static ArrayList<?> list;

    public static void fillArr (int[] arr){
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random()*6);
        }
    }

    public static boolean checkArray(int[] arr) {
        int check = 0;
        for (int value : arr) {
            if (value == search1 || value == search4) check++;
        }
        return check > 0;
    }

    public static void main(String[] args) {
        fillArr(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(checkArray(arr));
    }
}
