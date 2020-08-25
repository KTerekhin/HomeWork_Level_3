package server;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class DataBaseAuthService implements AuthService {
    private Statement stmt;
    private Connection connection;

    public void connectToDataBase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Драйвер не найден");
        }

        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection("jdbc:mysql://localhost/users_for_chat?serverTimezone=Europe/Moscow&useSSL=false", "root", "futyncvbn7");
            stmt = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка регистрации драйвера");
        }
    }

    @Override
    public String getNickByLoginAndPass(String login, String password) {
        String sql = String.format("SELECT nickname FROM users_for_chat.users WHERE (`login` = '%s' AND `password` = '%s')", login, password);
        try {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Statement Error");
        }
        return null;
    }

    @Override
    public boolean changeNickname(String login, String password, String newNick) {
        String sql = String.format("UPDATE `users_for_chat`.`users` SET `nickname` = '%s' WHERE (`login` = '%s' AND `password` = '%s')", newNick, login, password);
        try {
            return stmt.executeUpdate(sql) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
            return false;
    }

    @Override
    public void start() {
        System.out.println("Сервис аутентификации запущен");
        connectToDataBase();
    }

    @Override
    public void stop() {
        System.out.println("Сервис аутентификации остановлен");
    }

    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
