package HomeworkJava3.HomeworkSeven;

/*
Создать класс, который может выполнять «тесты». В качестве тестов выступают классы с наборами методов
с аннотациями @Test. Для этого у него должен быть статический метод start(), которому в качестве параметра
передается или объект типа Class, или имя класса. Из «класса-теста» вначале должен быть запущен метод
с аннотацией @BeforeSuite, если такой имеется. Далее запущены методы с аннотациями @Test, а по завершении
всех тестов – метод с аннотацией @AfterSuite. К каждому тесту необходимо добавить приоритеты (int числа от 1 до 10),
в соответствии с которыми будет выбираться порядок их выполнения. Если приоритет одинаковый, то порядок
не имеет значения. Методы с аннотациями @BeforeSuite и @AfterSuite должны присутствовать в единственном экземпляре,
иначе необходимо бросить RuntimeException при запуске «тестирования».
 */

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HW7 {

    public static void main(String[] args) {
        TestingClass.start(TestingClass.class);
    }
}

class TestingClass {

    public static void start(Class cls) {
        performTests(cls);
    }

    private static void performTests(Class cls) throws RuntimeException {
        TestingClass testingObj = null;
        try {
            testingObj = (TestingClass)cls.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        Method beforeMethod = null;
        Method afterMethod = null;

        Method[] methods = cls.getMethods();
        List<MethodWithPriority> testingMethods = new ArrayList<>();

        for (Method method : methods)
            if (method.getAnnotation(BeforeSuite.class) != null) {
                if (beforeMethod != null)
                    throw new RuntimeException("Метод @BeforeSuite должен быть один");
                beforeMethod = method;
            } else if (method.getAnnotation(AfterSuite.class) != null) {
                if (afterMethod != null)
                    throw new RuntimeException("Метод @AfterSuite должен быть один");
                afterMethod = method;
            } else if (method.getAnnotation(Test.class) != null){
                Test annotationTst = method.getAnnotation(Test.class);
                testingMethods.add(new MethodWithPriority(method, annotationTst.value()));
            }

        testingMethods.sort(
                Comparator.comparing(MethodWithPriority::getPriority));

        try {
            if (beforeMethod != null)
                beforeMethod.invoke(testingObj);

            for (MethodWithPriority methodWP : testingMethods)
                methodWP.method.invoke(testingObj);

            if (afterMethod != null)
                afterMethod.invoke(testingObj);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test(value = 5)
    public void Test5() {
        System.out.println("Тест №5");
    }

    @Test(value = 3)
    public void Test2(){
        System.out.println("Тест №2");
    }

    @Test(value = 1)
    public void Test1(){
        System.out.println("Тест №1");
    }
    @BeforeSuite
    public void BeforeAll(){
        System.out.println("Метод до начала всех тестов");
    }
    @AfterSuite
    public void AfterAll() {
        System.out.println("Метод после окончания всех тестов");
    }
}

class MethodWithPriority {
    public Method method;
    public Integer priority;

    public MethodWithPriority(Method method, int priority) {
        this.method = method;
        this.priority = priority;
    }

    public Integer getPriority() {
        return priority;
    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface BeforeSuite {}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface AfterSuite {}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Test {
    int value();
}
