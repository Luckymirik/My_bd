package org.example;

import java.sql.*;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Cats {
    public static void main(String[] args) {
        Cats cats = new Cats();
        cats.open();
//        cats.secondTable();
        //cats.createMoreCats(5000);
   //     cats.getCatById();
      //  cats.getCatWhere();
        cats.getAllCats();
//        cats.insertAllTypes();
//        cats.insert();
//        cats.deleteType();
//        cats.updateType();
//        cats.select();
//        cats.getType();
        cats.close();

    }
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
            String secondTable = "CREATE TABLE if not exists cats (" +
                    " id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                    "name VARCHAR(20) NOT NULL, " +
                    "type_id INT NOT NULL REFERENCES types(id), age INT NOT NULL, " +
                    "weight DOUBLE )";
            Statement statement = connection.createStatement();
            statement.executeUpdate(secondTable);
            System.out.println("Таблица создана");
            statement.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    private void createCat(String name,String type,int age,Double weight){
        try {
            ResultSet resultSet = getType("type = '" + type + "'");
            int id;
            if (resultSet.isBeforeFirst()){
                id = resultSet.getInt("id");
            }else {
                insert(type);
                id = getType("type = '" + type + "'").getInt("id");
            }
            String insertCat = "INSERT into 'cats' ('name','type_id','age','weight') VALUES ('" + name + "'," + id + "," + age + "," + weight + ")"  ;
            Statement statement = connection.createStatement();
            statement.executeUpdate(insertCat);
            System.out.println("Кошка добавлена");
            statement.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    private ResultSet getType(String type) throws SQLException{
        Statement statement = connection.createStatement();
        String query = "Select id, type FROM types WHERE " + type;
        return statement.executeQuery(query);
    }
    private void getCatById(){
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Введите id кота: ");
            int id = sc.nextInt();
            String query = "SELECT * FROM cats WHERE id = " + id;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                int ident = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                double weight = resultSet.getDouble("weight");
                System.out.println(id + "\t|" + name + "\t|"
                 + age + "\t|" + weight);
            }
            resultSet.close();
            statement.close();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    private void getCatWhere(){
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Введите условие: ");
            String where = sc.nextLine();
            String query = "SELECT * FROM cats WHERE " + where;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                int ident = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                double weight = resultSet.getDouble("weight");
                System.out.println(ident + "\t|" + name + "\t|"
                        + age + "\t|" + weight);
            }
            resultSet.close();
            statement.close();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    private void getAllCats(){
        try {
            String query = "SELECT * FROM cats";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                int ident = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                double weight = resultSet.getDouble("weight");
                System.out.println(ident + "\t|" + name + "\t|"
                        + age + "\t|" + weight);
            }
            resultSet.close();
            statement.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private void createMoreCats(int n){
        String[] names = {"Гарфилд","Том","Гудвин","Рокки",
                "Ленивец","Пушок","Спорти","Бегемот","Пират","Гудини","Зорро",
                "Саймон","Альбус","Базилио","Леопольд","Нарцисс","Атос","Каспер","Валлито",
                "Оксфорд","Бисквит","Соня","Клеопатра","Цунами","Забияка","Матильда","Кнопка",
                "Масяня","Царапка","Серсея","Ворсинка","Амели","Наоми","Маркиза","Изольда","Вальс",
                "Несквик","Златан","Баскет","Изюм","Цукат","Мокко","Месси","Кокос","Адидас","Бейлиз",
                "Тайгер","Зефир","Мохи","Валенсия","Баунти","Свити","Текила","Ириска","Карамель","Виски",
                "Кукуруза","Гренка","Фасолька","Льдинка","Китана","Офелия","Дайкири","Брусника","Аватар",
                "Космос","Призрак","Изумруд","Плинтус","Яндекс","Крисмас","Метеор","Оптимус","Смайлик",
                "Цельсий","Краска","Дейзи","Пенка","Веста","Астра","Эйприл","Среда","Бусинка","Гайка",
                "Елка","Золушка","Мята","Радость","Сиам","Оскар","Феликс","Гарри","Байрон","Чарли","Симба",
                "Тао","Абу","Ватсон","Енисей","Измир","Кайзер","Васаби","Байкал","Багира","Айрис","Диана",
                "Мими","Сакура", "Индия","Тиффани","Скарлетт","Пикси",
                "Лиззи","Алиса","Лило","Ямайка","Пэрис","Мальта","Аляска"};
        try {
            for (int i = 0; i < n; i++) {
                createCat(names[(int) ((names.length - 1) * Math.random())]
                        ,typesToInsert[(int) ((typesToInsert.length - 1) * Math.random())]
                        , (int) (25 * Math.random())
                        ,17 * Math.random());

            }
            System.out.println("Количество добавленных котов: " + n);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }


    private void insertAllTypes() {

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
    private void deleteCatById(){
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Delete cat by id: ");
            int id = sc.nextInt();

            String deleteSQL = "DELETE FROM cats WHERE id = " + id;
            Statement statement = connection.createStatement();
            statement.executeUpdate(deleteSQL);
            System.out.println("Удаление прошло успешно");
            statement.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
    private void deleteCatByName(){
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Delete cat by name: ");
            String name = sc.nextLine();

            String deleteSql = "DELETE FROM cats WHERE name = " + name;
            Statement statement = connection.createStatement();
            statement.executeUpdate(deleteSql);
            System.out.println("Удаление прошло успешно");
            statement.close();
        }catch (SQLException e){
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
    private void updateCat(){
        try {
            Scanner sc = new Scanner(System.in);

            System.out.println("Name : ");
            String name = sc.nextLine();
            System.out.println("Update cat id: ");

            int id = sc.nextInt();


            String updateSQL = "UPDATE cats SET name = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(updateSQL);
            statement.setInt(1,id);
            statement.setString(2,name);
        }catch (SQLException e){
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
    private void insert(String type) {
        try {



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
