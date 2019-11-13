package HomeworkJava3.HomeworkOne.Ex1;

import java.io.IOException;

//Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа);

public class ChangeElements {
    public static void main(String[] args) throws IOException {
        Arrays<Integer> arrOne = new Arrays<>(1,2,3,4,5);
        Arrays<String>  arrTwo = new Arrays<>("a","b","c","d");

        arrOne.change();
        arrTwo.change();
    }
}

