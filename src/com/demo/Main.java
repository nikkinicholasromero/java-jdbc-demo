package com.demo;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.*;

public class Main {
    /*
        JDBC URL Format = jdbc:${vendor}:${databaseSpecific}
        Connection
            public Statement createStatement();
            public Statement createStatement(int type, int concurrency);

            ResultSet.TYPE_FORWARD_ONLY         - default
            ResultSet.TYPE_SCROLL_SENSITIVE     - forward/backward - data is static
            ResultSet.TYPE_SCROLL_INSENSITIVE   - forward/backward - data is dynamic

            ResultSet.CONCUR_READ_ONLY          - default
            ResultSet.CONCUR_UPDATABLE          - allows you to use resultSet to update the database

        Statement
            public boolean execute(String statement) throws SQLException;
            public ResultSet executeQuery(String query) throws SQLException;
            public int executeUpdate(String command) throws SQLException;

        ResultSet
            public ResultSet getResultSet();
            public int getUpdateCount();

            public boolean next();
            public boolean previous();
            public boolean first();
            public boolean last();
            public boolean beforeFirst();
            public boolean afterLast();
            public boolean absolute(int rowNum);
            public boolean relative(int rowNum);

            public boolean getBoolean();
            public int getInt();
            public long getLong();
            public double getDouble();
            public java.sql.Date getDate();
            public java.sql.Time getTime();
            public java.sql.TimeStemp getTimeStamp();
            public String getString();
            public Object getObject();

        SQLException
            public String getMessage();
            public String getSQLState();
            public String getErrorCode();
     */

    private static final String URL = "jdbc:mysql://localhost/demo";
    private static final String USER = "admin";
    private static final String PASS = "pass123";

    public static void main(String[] args) {
        try (Connection connection = getConnectionFromDataSource();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM EMPLOYEES")) {
            while (resultSet.next()) {
                String row =
                        resultSet.getInt("ID") + " - " +
                        resultSet.getString("FIRST_NAME") + " - " +
                        resultSet.getString("MIDDLE_NAME") + " - " +
                        resultSet.getString("LAST_NAME") + " - " +
                        resultSet.getDouble("SALARY") + " - " +
                        resultSet.getDate("SOME_DATE").toLocalDate() + " - " +
                        resultSet.getTime("SOME_TIME").toLocalTime() + " - " +
                        resultSet.getTimestamp("SOME_DATETIME").toLocalDateTime() + " - " +
                        resultSet.getBoolean("ACTIVE");
                System.out.println(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnectionFromDriverManager() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    private static Connection getConnectionFromDataSource() throws SQLException {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl(URL);
        dataSource.setUser(USER);
        dataSource.setPassword(PASS);
        return dataSource.getConnection();
    }
}
