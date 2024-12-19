package se.eric.DatabassH;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtil {

    private static Properties properties = new Properties();

    static {                                                    //gets properties from application properties file
        try (InputStream input = JDBCUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new IOException("Unable to find application.properties");
            }
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("Failed to load database properties");
        }
    }

    public static Connection getConnection() throws SQLException {      //creates a connection whit databas

        Driver hsqlDriver = new org.hsqldb.jdbc.JDBCDriver();

        DriverManager.registerDriver(hsqlDriver);

        String dbUrl = properties.getProperty("db.url");

        String userID = properties.getProperty("db.user");

        String password = properties.getProperty("db.password");

        Connection conn = DriverManager.getConnection(dbUrl, userID, password);

        conn.setAutoCommit(false);

        return conn;
    }

    public static void closeConnection(Connection conn) {       //closes database connection
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeStatement(Statement stmt) {         //closes database statement
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeResultSet(ResultSet rs) {           //closes database result set
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void commit(Connection conn) {                    //commits changes to the databas
        try {
            if (conn != null) {
                conn.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void rollback(Connection conn) {                  //rollbacks the databas if something gos wrong
        try {
            if (conn != null)
                conn.rollback();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getDatabaseProductName(Connection conn) {      //un used
        try {
            if (conn != null) {
                DatabaseMetaData metadata = conn.getMetaData();
                return metadata.getDatabaseProductName();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
}

