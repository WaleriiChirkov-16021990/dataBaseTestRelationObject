package ru.chirkov;

import java.sql.*;

public class JBDCTask {
    private static final String URL = "jdbc:mysql://localhost:3306";
    private static final String PASSWORD = "admin";
    private static final String USER = "root";

    public static void toConnection() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);) {
            Statement statement = connection.createStatement();
            statement.execute("DROP SCHEMA IF EXISTS HomeWork");
            statement.execute("CREATE SCHEMA HomeWork");
            statement.execute("CREATE TABLE IF NOT EXISTS HomeWork.books (" +
                    "`id` BIGINT unsigned AUTO_INCREMENT NOT NULL," +
                    "`name` varchar(255) NOT NULL," +
                    "`author` varchar(255) NOT NULL" +
                    ", PRIMARY KEY(`id`));");
            PreparedStatement pr = connection.prepareStatement("INSERT INTO HomeWork.books (`name`,`author`) VALUES ( ?,?)");
            pr.setString(1, "Герой нашего времени");
            pr.setString(2, "Лермонтов М.Ю.");
            pr.execute();
            pr.setString(1, "Мастер и Маргарита");
            pr.setString(2, "Булгаков М.А.");
            pr.execute();
            pr.setString(1, "Горе от ума");
            pr.setString(2, "Грибоедов А.С.");
            pr.execute();
            pr.setString(1, "Идиот");
            pr.setString(2, "Достоевский Ф.");
            pr.execute();
            pr.setString(1, "Мцыри");
            pr.setString(2, "Лермонтов М.Ю.");
            pr.execute();
            pr.setString(1, "Демон");
            pr.setString(2, "Лермонтов М.Ю.");
            pr.execute();
            pr.setString(1, "Преступление и наказание");
            pr.setString(2, "Достоевский Ф.");
            pr.execute();
            pr.setString(1, "Атеизм");
            pr.setString(2, "Достоевский Ф.");
            pr.execute();
            pr.setString(1, "Евгений Онегин");
            pr.setString(2, "Пушкин А.С.");
            pr.execute();
            pr.setString(1, "Моцарт и Сальери");
            pr.setString(2, "Пушкин А.С.");
            pr.execute();


            ResultSet resultSet = pr.executeQuery("SELECT * FROM HomeWork.books WHERE author='Пушкин А.С.'");
            while (resultSet.next()) {
                System.out.println("Id: " + resultSet.getString(1) +
                        " Имя: " + resultSet.getString(2) +
                        " Автор: " + resultSet.getString(3));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
