package HomeworkJava3.HomeworkFour.Ex2;
    /*  2. Написать небольшой метод, в котором 3 потока построчно пишут данные в файл
        (по 10 записей с периодом в 20 мс). */

import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {
    private static FileWriter fr;

    public static void main(String[] args) throws IOException, InterruptedException {
        fr = new FileWriter("Lesson4.txt", false);
        for (int i = 0; i < 10; i++) {
            methodWrite();
        }
        fr.close();
    }

    private static synchronized void methodWrite() throws InterruptedException {
        Thread t1 = new Thread(() ->{
            try {
                fr.write("первый");
                fr.append('\n');
                Thread.sleep(20);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t1.join();

        Thread t2 = new Thread(() ->{
            try {
                fr.write("второй");
                fr.append('\n');
                Thread.sleep(20);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        t2.start();
        t2.join();

        Thread t3 = new Thread(() ->{
            try {
                fr.write("третий");
                fr.append('\n');
                Thread.sleep(20);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        t3.start();
        t3.join();
    }
}
