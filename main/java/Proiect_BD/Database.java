package Proiect_BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static String URL = "jdbc:oracle:thin:@//bd-dc.cs.tuiasi.ro:1539/orcl";
    private static Connection connection = null;
    private static String user = "bd002";
    private static String password = "bd002";

    private Database() {
    }



    public static Connection getConnection() {
        if (connection == null) {
            createConnection();
        }
        return connection;
    }

    private static void createConnection() {
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(URL,user,password);
        } catch (SQLException e) {
            System.err.println(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection = null;
    }

    public static void rollback() {
        try {
            if (connection != null) {
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}