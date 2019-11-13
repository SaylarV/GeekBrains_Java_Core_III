package OtrabotkaLessonOne.ExerciseTwo;

public class GenericsDemoApp {
    public static void main(String[] args) {
        TestGeneric<String> genStr = new TestGeneric<>("Hello");
        genStr.showType();
        System.out.println("genStr.getObj(): " + genStr.getObj());
        TestGeneric<Integer> genInt = new TestGeneric<>(140);
        genInt.showType();
        System.out.println("genInt.getObj(): " + genInt.getObj());
        int valueFromGenInt = genInt.getObj();
        String valueFromGenStr = genStr.getObj();
    }


}
