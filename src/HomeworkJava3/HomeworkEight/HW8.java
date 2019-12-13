package HomeworkJava3.HomeworkEight;

/*
Требуется написать метод, принимающий на вход размеры двумерного массива и выводящий
в консоль\файл массив в виде инкременированной цепочки чисел, идущих по спирали.
Примеры:
2х3
1 2
6 3
5 4

3х1
1 2 3
4х4
01 02 03 04
12 13 14 05
11 16 15 06
10 09 08 07
 */

public class HW8 {
    public static void main(String[] args) {
        newArray(3,3);
    }

    public static void newArray (int m, int n){
        int[][] array = new int[m][n];
        int s = 1;
        // запоняем массив по периметру
        for (int y = 0; y < n; y++) {
            array[0][y] = s;
            s++;
        }
        for (int x = 1; x < m; x++) {
            array[x][n - 1] = s;
            s++;
        }
        for (int y = n - 2; y >= 0; y--) {
            array[m - 1][y] = s;
            s++;
        }
        for (int x = m - 2; x > 0; x--) {
            array[x][0] = s;
            s++;
        }
        // после заполнения периметра начинаем заполнять по координатам
        int c = 1;
        int d = 1;

        while (s < m * n) {
            //Движемся вправо.
            while (array[c][d + 1] == 0) {
                array[c][d] = s;
                s++;
                d++;
            }
            //Движемся вниз.
            while (array[c + 1][d] == 0) {
                array[c][d] = s;
                s++;
                c++;
            }
            //Движемся влево.
            while (array[c][d - 1] == 0) {
                array[c][d] = s;
                s++;
                d--;
            }
            //Движемся вверх.
            while (array[c - 1][d] == 0) {
                array[c][d] = s;
                s++;
                c--;
            }
        }

        // Заполняем центр
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                if (array[x][y] == 0) {
                    array[x][y] = s;
                }
            }
        }

        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                if (array[x][y] < 10) {
                    //Два пробела, чтобы в консоли столбцы были ровные.
                    System.out.print(array[x][y] + "  ");
                } else {
                    System.out.print(array[x][y] + " ");
                }
            }
            System.out.println("");
        }
    }
}
