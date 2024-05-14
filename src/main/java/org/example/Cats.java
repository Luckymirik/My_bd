package org.example;

import java.sql.*;
import java.util.Scanner;

public class Cats {
    public static void main(String[] args) {
        Cats cats = new Cats();
        cats.open();
        cats.insert();
        cats.select();
        cats.close();

    }
    Connection connection;
    private void open(){
        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\user\\IdeaProjects\\MyBd1\\identifier.sqlite");
            System.out.println("Connected");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void insert() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter type cat: ");
            String type = sc.nextLine();

            // Проверка наличия записи
            String checkQuery = "SELECT * FROM types WHERE type = '" + type + "'";
            Statement checkStatement = connection.createStatement();
            ResultSet resultSet = checkStatement.executeQuery(checkQuery);
            if (resultSet.next()) {
                System.out.println("Запись уже существует");
                return; // Прерываем метод, чтобы не добавлять дубликат
            }

            // Вставка записи
            String insertQuery = "INSERT INTO types (type) " +
                    "VALUES('" + type + "')";
            Statement insertStatement = connection.createStatement();
            insertStatement.executeUpdate(insertQuery);

            System.out.println("Rows added");
            insertStatement.close();
            checkStatement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void select(){
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT id, type" + " FROM types " + "ORDER BY type";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String type = resultSet.getString("type");
                System.out.println(id + "\t|" + type);
            }
            resultSet.close();
            statement.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void close(){
        try {
            connection.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
