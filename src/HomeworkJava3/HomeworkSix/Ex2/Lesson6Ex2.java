package HomeworkJava3.HomeworkSix.Ex2;
/*
Написать метод, которому в качестве аргумента передается не пустой одномерный целочисленный массив.
Метод должен вернуть новый массив, который получен путем вытаскивания из исходного массива элементов,
идущих после последней четверки. Входной массив должен содержать хотя бы одну четверку, иначе в методе
необходимо выбросить RuntimeException. Написать набор тестов для этого метода (по 3-4 варианта входных данных).
Вх: [ 1 2 4 4 2 3 4 1 7 ] -> вых: [ 1 7 ].
 */

import java.util.ArrayList;

public class Lesson6Ex2 {
    private static int search = 4;
    private static int[] arr = new int[10];

    public static void fillArr (int[] arr){
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random()*6);
        }
    }

    public static Object[] createNewArray2(int[] arr) {
        System.out.println("Исходный массив:");
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] == search) && list.isEmpty()){
                for (int j = i+1; j < arr.length; j++) {
                    list.add(arr[j]);
                }
            }
            System.out.print(arr[i] + " ");
        }
        System.out.println("\n" + "Новый массив:");
        if (list.isEmpty()) throw new RuntimeException("В ИСХОДНОМ МАССИВЕ НЕТ ЦИФРЫ " + search);
        else {
            for (Integer integer : list) {
                System.out.print(integer + " ");
            }
            return list.toArray();
        }
    }

    public static void main(String[] args) {
        fillArr(arr);
        createNewArray2(arr);
    }
}
