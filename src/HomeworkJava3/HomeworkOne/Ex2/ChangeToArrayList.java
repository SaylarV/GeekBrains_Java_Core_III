package HomeworkJava3.HomeworkOne.Ex2;

//Написать метод, который преобразует массив в ArrayList;

public class ChangeToArrayList {
    public static void main(String[] args) {
        Array<Integer> arrOne = new Array<>(1,2,3,4,5);
        Array<String>  arrTwo = new Array<>("a","b","c","d");

        arrOne.change();
        arrTwo.change();
    }
}
