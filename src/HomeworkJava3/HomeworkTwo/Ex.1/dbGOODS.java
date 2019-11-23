package HomeworkJava3.HomeworkTwo;
/* Альтернатива (если нет времени):
   1. Подключиться к БД
   2. Создать таблицу товаров (good_id, good_name, good_price) запрсом из Java пиложения.
   3. При запуске приложения очистить таблицу и заполнить 10000 товаров
   4. Написать консольное приложение, которое позволяет узнать цену товара по его имени,
      либо вывести сообщение «Такого товара нет», если товар не обнаружен в базе.
   5. Добавить возможность изменения цены товара. Указываем имя товара и новую цену.
   6. Вывести товары в заданном ценовом диапазоне.
   Вам понадобятся: SELECT, DELETE, WHERE, UPDATE, LIKE, BETWEEN */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class dbGOODS {
    private static Connection conn;
    private static Statement stmt;

    public static void main(String[] args) throws SQLException, IOException {
        try {
            connection();
        } catch (ClassNotFoundException e) {
            System.out.println("Драйвер не найден");;
        } catch (SQLException e) {
            System.out.println("stmt не сработал");
        }

        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Goods (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                        "Name TEXT NOT NULL, Price DOUBLE NOT NULL)");
        stmt.executeUpdate("DELETE FROM Goods");
        conn.setAutoCommit(false);
            for (int i = 1; i <= 10000; i++) {
                stmt.addBatch("INSERT INTO Goods (Name, Price) VALUES ('g" + i + "', " + Math.ceil(i*Math.random()*100)/100 + ")");
            }
        stmt.executeBatch();
        conn.setAutoCommit(true);
        System.out.println("Введите название товара, цену которого хотите узнать");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String nameOfGood = reader.readLine();
        int count = 0;
        ResultSet rs = stmt.executeQuery("SELECT * FROM Goods WHERE Name = " + "'" + nameOfGood + "'");
        while (rs.next()) {
            System.out.println(rs.getInt(1) + " " + rs.getString("Name")
                    + " " + rs.getDouble("Price"));
            count++;
        }
        if (count == 0) System.out.println("Такого товара нет!");

        System.out.println("Хотите ли вы изменить цену какого-либо товара? (yes/no)");
        String questYN = reader.readLine();
        while ((questYN.equals("yes"))){
            System.out.println("Введите наименование товара, цену которого вы хотите изменить:");
            String nameOfGood2 = reader.readLine();
            System.out.println("Введите новую цену");
            Double newPrice = Double.parseDouble(reader.readLine());
            stmt.executeUpdate("UPDATE Goods set Price = " + newPrice + " WHERE Name = " + "'" + nameOfGood2 + "'");
            System.out.println("Желаете узнать цену ещё одного товара? (yes/no)");
            questYN = reader.readLine();
        }

        System.out.println("Товары в каком ценовом диапазоне желаете вывести на экран? (укажите через Enter min и max)");
        int min = Integer.parseInt(reader.readLine());
        int max = Integer.parseInt(reader.readLine());
        ResultSet rs2 = stmt.executeQuery("SELECT * FROM Goods WHERE Price >= " + min + " AND Price <= " + max);
        while (rs2.next()) {
            System.out.println(rs2.getInt(1) + " " + rs2.getString("Name")
                    + " " + rs2.getDouble("Price"));
        }

        disconnect();
    }

    public static void connection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:mainDB.db");
        stmt = conn.createStatement();
    }

    public static void disconnect() throws SQLException {
        conn.close();
    }
}
