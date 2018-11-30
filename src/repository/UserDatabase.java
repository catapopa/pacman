package repository;

import constants.Constants;
import domain.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDatabase {

    private List<User> users;

    public UserDatabase() {
        this.users = new ArrayList<>();
        //this.getFromDatabase();
    }

    public List<User> getUsers() {
        return users;
    }

    public List<User> getFromDatabase(){
        Connection conn = null;
        Statement stmt = null;

        try {
            //STEP 2: Register JDBC driver
            Class.forName("org.mariadb.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(
                    Constants.DB_URL, Constants.DatabaseUser, Constants.DatabasePassword);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query

            stmt = conn.createStatement();
            String sql = "Select * from score";
            ResultSet rst;
            rst = stmt.executeQuery(sql);
            while (rst.next()) {
                User user = new User(rst.getString("name"), rst.getInt("score"), rst.getString("created_at"));
                this.users.add(user);
                System.out.println(user);
            }

            System.out.println("Created table in given database...");

        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        return this.users;
    }

    public void addToDatabase(User user) {
        String name = user.getName();
        String score = String.valueOf(user.getScore());
        String created_at = user.getCreated_at();
        this.connect(name, score, created_at);
    }

    private void connect(String name, String score, String created_at) {
        Connection conn = null;
        Statement stmt = null;
        PreparedStatement preparedStatement;

        try {
            //STEP 2: Register JDBC driver
            Class.forName("org.mariadb.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(
                    Constants.DB_URL, Constants.DatabaseUser, Constants.DatabasePassword);
            System.out.println("Connected database successfully...");

            //STEP 4: Execute a query
            String line = "INSERT INTO score (name, score, created_at) VALUES (?,?,?); ";
            preparedStatement = conn.prepareStatement(line);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, score);
            preparedStatement.setString(3, created_at);
            preparedStatement.execute();
            System.out.println("Created table in given database...");


        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }
}
