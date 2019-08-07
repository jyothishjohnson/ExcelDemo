package com.l7.training.poi.jdbc;

import java.sql.*;

public class JDBCExample {
    private static final String DB_URL = "jdbc:mysql://localhost/test";
    private static final String userName = "root";
    private static final String password = "";


    public static void main(String[] args) {
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(DB_URL, userName, password);
            resultSet = getEmployeeRSByStmt(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static ResultSet getEmployeeRSByStmt(Connection conn) throws SQLException {
        ResultSet rs = null;
        Statement stmt = null;
//STEP 4: Create a statement object to execute the query
        System.out.println("Creating statement...");
        stmt = conn.createStatement();
        String sql;
        sql = "SELECT empId, empName FROM EMPLOYEE";

//STEP 5: Executing a query using statement
        rs = stmt.executeQuery(sql);

//STEP 6: Extract data from result set
        while (rs.next()) {
// Retrieve by column name
            int id = rs.getInt("empId");
            String name = rs.getString("empName");

// Display values
            System.out.print("Id: " + id);
            System.out.print(", Name: " + name + "\n");
        }
        return rs;

    }
}
