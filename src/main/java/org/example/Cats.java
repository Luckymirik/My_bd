package org.example;

import java.sql.*;
import java.util.Scanner;

public class Cats {
    public static void main(String[] args) {
        Cats cats = new Cats();
        cats.open();
        cats.insertAllTypes();
//        cats.insert();
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
    private void insertAllTypes() {
        String[] typesToInsert = {
                "Абиссинская кошка",
                "Австралийский мист",
                "Американская жесткошерстная",
                "Американская короткошерстная",
                "Американский бобтейл",
                "Американский кёрл",
                "Балинезийская кошка",
                "Бенгальская кошка",
                "Бирманская кошка",
                "Бомбейская кошка",
                "Бразильская короткошерстная",
                "Британская длинношерстная",
                "Британская короткошерстная",
                "Бурманская кошка",
                "Бурмилла кошка",
                "Гавана",
                "Гималайская кошка",
                "Девон-рекс",
                "Донский сфинкс",
                "Европейская короткошерстная",
                "Египетская мау",
                "Канадский сфинкс",
                "Кимрик",
                "Корат",
                "Корниш рекс",
                "Курильский бобтейл",
                "Лаперм",
                "Манчкин",
                "Мейн-кун",
                "Мекогонский бобтейл",
                "Мэнкс кошка",
                "Наполеон",
                "Немецкий рекс",
                "Нибелунг",
                "Норвежская лесная кошка",
                "Ориентальная кошка",
                "Оцикет",
                "Персидская кошка",
                "Петерболд",
                "Пиксибоб",
                "Рагамаффин",
                "Русская голубая кошка",
                "Рэгдолл",
                "Саванна",
                "Селкирк-рекс",
                "Сиамская кошка",
                "Сибирская кошка",
                "Сингапурская кошка",
                "Скоттиш-фолд",
                "Сноу-шу",
                "Сомалийская кошка",
                "Тайская кошка",
                "Тойгер",
                "Тонкинская кошка",
                "Турецкая ангорская кошка",
                "Турецкий ван",
                "Украинский левкой",
                "Чаузи",
                "Шартрез",
                "Экзотическая короткошерстная",
                "Японский бобтейл"
        };
        String insertSQL = "INSERT INTO types (type) VALUES (?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            for (String type : typesToInsert) {
                preparedStatement.setString(1, type);
                preparedStatement.executeUpdate();
            }
            System.out.println("Все типы успешно добавлены");
        } catch (SQLException e) {
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
