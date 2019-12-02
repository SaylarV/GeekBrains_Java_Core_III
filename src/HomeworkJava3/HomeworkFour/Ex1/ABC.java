package HomeworkJava3.HomeworkFour.Ex1;
    /*  1. Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5 раз
        (порядок – ABСABСABС). Используйте wait/notify/notifyAll. */

public class ABC {
    private final Object letter = new Object();
    private volatile char currentletter = 'A';

    public static void main(String[] args) {
        ABC abc = new ABC();
        new Thread(abc::printA).start();
        new Thread(abc::printB).start();
        new Thread(abc::printC).start();
    }

    private void printA(){
        synchronized (letter){
            for (int i = 0; i < 5; i++) {
                while (currentletter !='A'){
                    try {
                        letter.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print('A');
                currentletter = 'B';
                letter.notifyAll();
            }
        }
    }

    private void printB(){
        synchronized (letter){
            for (int i = 0; i < 5; i++) {
                while (currentletter !='B'){
                    try {
                        letter.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print('B');
                currentletter = 'C';
                letter.notifyAll();
            }
        }
    }

    private void printC(){
        synchronized (letter){
            for (int i = 0; i < 5; i++) {
                while (currentletter != 'C'){
                    try {
                        letter.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print('C');
                currentletter = 'A';
                letter.notifyAll();
            }
        }
    }
}
