package com.library;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.*;

public class DatabaseConnectionTest {

    private Connection connection;
    private static Statement statement;
    private static ResultSet rs;

    @BeforeClass
    public void setUp() {
        String databaseURL = "jdbc:mysql://localhost:3306/librarydb";
        String user = "root";
        String password = "domin";
        connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting to Database...");
            connection = DriverManager.getConnection(databaseURL, user, password);
            if (connection != null) {
                System.out.println("Connected to the Database...");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void getUsersFromDatabase() {
        try {
            String query = "select * from User";
            statement = connection.createStatement();
            rs = statement.executeQuery(query);

            while(rs.next()){
                int userID= rs.getInt("userID");
                int active=rs.getInt(2);
                String email= rs.getString("email");
                String firstame=rs.getString("firstname");
                String password=rs.getString(5);
                String surname= rs.getString("surname");
                String username= rs.getString("surname");
                System.out.println(userID+"\t"+active+"\t"+email+"\t"+firstame+"\t"+password+"\t"+surname+"\t"+username);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @AfterClass
    public void tearDown() {
        if (connection != null) {
            try {
                System.out.println("Closing Database Connection...");
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
