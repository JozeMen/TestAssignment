import java.sql.*;

public class CamelTester {
    private static final String url = "jdbc:postgresql://localhost/simple_db";
    private static final String user = "postgres";
    private static final String password = "1";

    public static void createDataBase() {
        try (Connection conn = DriverManager
                .getConnection("jdbc:postgresql://localhost/",
                        user, password);
             Statement stmt = conn.createStatement();
        ) {
            String sql = "CREATE DATABASE SIMPLE_DB";
            stmt.executeUpdate(sql);
            stmt.close();

            System.out.println("Database created successfully...");
        } catch (SQLException e) {
            if (e.getSQLState().equals("42P04")) { // SQL state for database already exists
                System.out.println("Database already exists.");
            } else {
                e.printStackTrace();
            }
        }
    }

    public static void createTable() {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();) {
            statement.execute("CREATE TABLE COMPANY " +
                    "(ID INT PRIMARY KEY     NOT NULL," +
                    " NAME           TEXT    NOT NULL, " +
                    " SALARY         REAL)");
            System.out.println("Table created successfully...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertInTable() {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();) {
            statement.execute("INSERT INTO COMPANY (ID,NAME,SALARY) VALUES "
                    + "(1, 'Alex', 20000.00 ),"
                    + "(2, 'Masha', 42000.00 ),"
                    + "(3, 'Sim', 62000.00 ),"
                    + "(4, 'Lola', 19000.00 ),"
                    + "(5, 'Max', 50000.00 )");
            System.out.println("Data inserted successfully...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        createDataBase();
        createTable();
        insertInTable();

        MyEndpoint mye = new MyEndpoint("jdbc:postgresql://localhost/simple_db?user=postgres&password=1");
        mye.doWork();
    }
}
