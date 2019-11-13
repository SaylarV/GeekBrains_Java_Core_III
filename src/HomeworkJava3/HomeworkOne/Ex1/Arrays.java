package HomeworkJava3.HomeworkOne.Ex1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Arrays<T> {
    private T[] arr;


    public Arrays(T...arr) {
        this.arr = arr;
    }

    public void change() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите два индекса от 0 до " + (arr.length-1));
        int index1 = Integer.parseInt(reader.readLine());
        int index2 = Integer.parseInt(reader.readLine());
        Object[] arrNew = new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (i == index1){
                arrNew[i] = arr[index2];
            } else if (i == index2){
                arrNew[i] = arr[index1];
            } else arrNew[i] = arr[i];
            System.out.println("Индекс " + i + " было: " + arr[i] + "; стало: " + arrNew[i]);
        }
        System.out.println("**********************************");
    }


}
