package HomeworkJava3.HomeworkSix.Ex3;

import HomeworkJava3.HomeworkSix.Ex2.Lesson6Ex2;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Lesson6Ex3Test {

    @Before
    public void start(){
        System.out.println("Tests had started!");
    }

    @Test
    public void test1() {
        int[] arrInt = new int[10];
        int[] arrInt2 = new int[5];
        Lesson6Ex3.fillArr(arrInt);
        Lesson6Ex3.fillArr(arrInt2);
        Assert.assertArrayEquals(Lesson6Ex2.createNewArray2(arrInt),Lesson6Ex2.createNewArray2(arrInt2));
    }

    @Test
    public void test2() {
        int[] arrInt = new int[10];
        Lesson6Ex3.fillArr(arrInt);
        Assert.assertNotNull(Lesson6Ex2.createNewArray2(arrInt));
    }

    @After
    public void end(){
        System.out.println("Tests had ended!");
    }
}