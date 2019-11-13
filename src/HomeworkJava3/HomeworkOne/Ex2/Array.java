package HomeworkJava3.HomeworkOne.Ex2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Array<T> {
    private T[] arr;

    public Array(T...arr) {
        this.arr = arr;
    }

    public void change(){
        List<T> arrNew = new ArrayList<T>(Arrays.asList(arr));
        for (T t : arrNew) {
            System.out.print(t + " ");
        }
        System.out.println();
    }
}
