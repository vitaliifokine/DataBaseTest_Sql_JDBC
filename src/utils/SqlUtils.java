package utils;

import io.qameta.allure.Step;
import mySqlDataBaseOperations.Select;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlUtils {
    public static Connection conn;
    public static final String URL = "jdbc:mysql://localhost:3306/mydbtest?useUnicode=true&useSSL=true&use" +
            "JDBCCompliantTimezoneShift=true\" +\n" +
            "            \"&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static final String USERNAME = "vitaliifokine";
    public static final String PASSWORD = "Vf281992";

    public static Connection getConnection() {
        return conn;
    }

    @Step
    public SqlUtils connectionToDb() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String url = URL;
            conn = DriverManager.getConnection(url, USERNAME, PASSWORD);
        } catch (ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
        } catch (IllegalAccessException ex) {
            System.err.println(ex.getMessage());
        } catch (InstantiationException ex) {
            System.err.println(ex.getMessage());
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return this;
    }

    @Step
    public SqlUtils cleanDataBase() {
        try {
            Statement st = SqlUtils.getConnection().createStatement();
            st.executeUpdate("delete from users;");
            new Select().selectNameAgeMail();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return this;
    }

    @Step
    public void close() {
        try {
            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}