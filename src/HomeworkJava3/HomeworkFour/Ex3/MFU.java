package HomeworkJava3.HomeworkFour.Ex3;
/*  3. Написать класс МФУ, на котором возможно одновременно выполнять печать
    и сканирование документов, но нельзя одновременно печатать или сканировать
    два документа. При печати в консоль выводится сообщения
    «Отпечатано 1, 2, 3,... страницы», при сканировании – аналогично «Отсканировано...».
    Вывод в консоль с периодом в 50 мс. */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MFU{

    private static int N;

    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Какое количество страниц необходимо напечатать (отксанировать)?");
        N = Integer.parseInt(reader.readLine());
        canScan();
        canPrint();
    }

    private static synchronized void canPrint() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            System.out.print("Отпечатано ");
            for (int i = 1; i <= N; i++) {
                if (i == N) System.out.println(i + " стр.");
                else System.out.print(i + ", ");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t1.join();
    }

    private static synchronized void canScan() throws InterruptedException {
        Thread t2 = new Thread(() -> {
            System.out.print("Отсканировано ");
            for (int i = 1; i <= N; i++) {
                if (i == N) System.out.println(i + " стр.");
                else System.out.print(i + ", ");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t2.start();
        t2.join();
    }
}
