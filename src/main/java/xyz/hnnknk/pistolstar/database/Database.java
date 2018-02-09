package xyz.hnnknk.pistolstar.database;

import com.sun.rowset.CachedRowSetImpl;

import java.sql.*;


public class Database {

    private static final String JDBC_DRIVER = "org.hsqldb.jdbcDriver";

    private static Connection conn = null;

    private static void dbConnect() throws SQLException, ClassNotFoundException {

        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        }

        try {
            conn = DriverManager.getConnection("jdbc:hsqldb:file:C:/hsqldb/", "SA", "");
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private static void dbDisconnect() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }

    public static void checkDbExistense() throws SQLException, ClassNotFoundException{

        String createStmt =
                "CREATE TABLE NOTE\n" +
                        "(\n" +
                        "NOTE_NAME VARCHAR(30),\n" +
                        "NOTE_DATE VARCHAR(30),\n" +
                        "NOTE_BODY VARCHAR(100)\n" +
                        ");\n";
        Statement stmt = null;

        try {
            dbConnect();
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, "NOTE", null);
            if (!tables.next()) {
                conn.createStatement().executeQuery(createStmt);
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            dbDisconnect();
        }
    }


    public static ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {

        Statement stmt = null;
        ResultSet resultSet = null;
        CachedRowSetImpl crs;
        try {

            dbConnect();

            stmt = conn.createStatement();


            resultSet = stmt.executeQuery(queryStmt);

            crs = new CachedRowSetImpl();
            crs.populate(resultSet);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeQuery operation : " + e);
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            dbDisconnect();
        }
        return crs;
    }


    public static void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        try {
            dbConnect();
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlStmt);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            dbDisconnect();
        }
    }
}