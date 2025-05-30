import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Conexion class provides the method to establish a connection
 * to the MySQL database used by the application. It encapsulates the database
 * connection details and allows the classes to obtain a connection for performing
 * database operations.
 * */
public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/taskphrena";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    /**
     * Establishes a connection to the MySQL database using the specified URL, user, and password.
     * This method utilizes the DriverManager to obtain a Connection object
     * which can be used to interact with the database.
     *
     * @return A Connection object that represents the connection to the database.
     * @throws SQLException If a database access error occurs.
     */
    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}