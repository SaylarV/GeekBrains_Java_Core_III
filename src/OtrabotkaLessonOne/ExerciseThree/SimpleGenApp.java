package OtrabotkaLessonOne.ExerciseThree;

public class SimpleGenApp {
    public static void main(String[] args) {
        TwoGen<String, Integer> twoGenObj = new TwoGen<String, Integer>("Hello",50);
        twoGenObj.showTypes();
        int intValue = twoGenObj.getObj2();
        String strValue = twoGenObj.getObj1();
        System.out.println(intValue + " " + strValue);



    }
}
