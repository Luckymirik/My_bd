package org.example;

import java.sql.*;
import java.util.Scanner;

public class Cats {
    public static void main(String[] args) {
        Cats cats = new Cats();
        cats.open();
        cats.secondTable();
//        cats.insertAllTypes();
//        cats.insert();
//        cats.deleteType();
//        cats.updateType();
//        cats.select();
//        cats.getType();
        cats.close();

    }

    Connection connection;
    private void open(){
        try{
//            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\user\\IdeaProjects\\MyBd1\\identifier.sqlite");
            System.out.println("Connected");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void secondTable(){
        try {
            String secondTable = "CREATE TABLE cats (" +
                    " id INT PRIMARY KEY UNIQUE, " +
                    "name VARCHAR(20) NOT NULL, " +
                    "type_id INT NOT NULL, age INT NOT NULL, " +
                    "weight DOUBLE, FOREIGN KEY (type_id) " +
                    "REFERENCES types(id)" +
                    ")";
            Statement statement = connection.createStatement();
            statement.executeUpdate(secondTable);
            System.out.println("Таблица создана");
            statement.close();
        }catch (SQLException e){
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
    private void deleteType(){
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Delete type cat: ");
            int id = sc.nextInt();

            String deleteSQL = "DELETE FROM types WHERE id = " + id;
            Statement statement = connection.createStatement();
            statement.executeUpdate(deleteSQL);
            System.out.println("Удаление успешно");
            statement.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());

        }
    }
    private void updateType(){
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Update type cat: ");

            String type = sc.nextLine();
            System.out.println("id: ");
            int id = sc.nextInt();

            String updateSql = "UPDATE types SET type = ?  WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(updateSql);
            statement.setString(1,type);
            statement.setInt(2,id);
            statement.executeUpdate();
            System.out.println("Редактирование успешно");
            statement.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
//    private void getType(){
//        try {
//            Scanner sc = new Scanner(System.in);
//            System.out.println("Введите id кошки: ");
//
//            int id = sc.nextInt();
//          Statement statement = connection.createStatement();
//
//
//            String selectSQL = "SELECT type FROM types WHERE id = (?)";
//            System.out.println(statement.);
//
//
//
//
//            statement.close();
//        }
//        catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//    }
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
