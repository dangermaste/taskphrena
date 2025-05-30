import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
/**
 * Usuario class represents a user in the application. It handles user registration,
 * login, and the management of user data (such as name, email, and password).
 * This class interacts with the database to verify user credentials and register new users.
 *
 * The class includes methods for:
 * - Registering a new user by checking if the email already exists in the database.
 * - Logging in a user by checking the provided credentials against the stored information.
 * - Returning a `Usuario` object with the user’s data upon successful login.
 *
 */
public class Usuario {
    private String nombre;
    private String email;
    private String contrasena;
    private static int usuarioID;

    /**
     * Constructs a `Usuario` object with the given details.
     *
     * @param nombre the name of the user.
     * @param email the email address of the user.
     * @param contrasena the password of the user.
     * @param usuarioID the unique ID of the user.
     */
    public Usuario(String nombre, String email, String contrasena, int usuarioID) {
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
        this.usuarioID = usuarioID;
    }

    /**
     * Registers a new user by asking for their name, email, and password,
     * and inserting the details into the database if the email is not already taken.
     *
     * @param scanner the scanner object used for input.
     * @return true if the registration was successful / false otherwise.
     */
    public static boolean registrar(Scanner scanner) {
        System.out.println(Colores.BLUE + "╔═════════════════════════════════════════════──────────────────────────────── ─── ── ─   ─ ");
        System.out.println("║                                 " + Colores.RESET + "INTRODUZCA SU NOMBRE");
        System.out.println(Colores.BLUE + "╚═══════════════════════════════════════════════───────────────────────────────── ─── ── ─ ");
        System.out.print("║-\\ ");
        System.out.print(Colores.RESET);
        String nombre = scanner.nextLine();

        System.out.println(Colores.BLUE + "╔═════════════════════════════════════════════──────────────────────────────── ─── ── ─   ─ ");
        System.out.println("║                            " + Colores.RESET + "INTRODUZCA SU CORREO ELECTRÓNICO");
        System.out.println(Colores.BLUE + "╚═══════════════════════════════════════════════───────────────────────────────── ─── ── ─ ");
        System.out.print("║-\\ ");
        System.out.print(Colores.RESET);
        String email = scanner.nextLine();

        System.out.println(Colores.BLUE + "╔═════════════════════════════════════════════──────────────────────────────── ─── ── ─   ─ ");
        System.out.println("║                                " + Colores.RESET + "INTRODUZCA SU CONTRASEÑA");
        System.out.println(Colores.BLUE + "╚═══════════════════════════════════════════════───────────────────────────────── ─── ── ─ ");
        System.out.print("║-\\ ");
        System.out.print(Colores.RESET);
        String contrasena = scanner.nextLine();

        try (Connection conn = Conexion.obtenerConexion()) {
            String verificarSQL = "SELECT * FROM usuarios WHERE email = ?";
            PreparedStatement verificarStmt = conn.prepareStatement(verificarSQL);
            verificarStmt.setString(1, email);
            ResultSet rs = verificarStmt.executeQuery();

            if (rs.next()) {
                return false;
            }

            String sql = "INSERT INTO usuarios (nombre, email, contrasena) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombre);
            stmt.setString(2, email);
            stmt.setString(3, contrasena);
            stmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println("|| Error al registrar usuario: " + e.getMessage());
        }
        return false;
    }

    /**
     * Logs in a user by asking for their email and password and checking the credentials
     * against the records in the database.
     *
     * @param scanner the scanner object used for input.
     * @return an "Usuario" object if login is successful, or null if the credentials are incorrect.
     */
    public static Usuario iniciarSesion(Scanner scanner) {
        System.out.println(Colores.PURPLE + "╔═════════════════════════════════════════════──────────────────────────────── ─── ── ─   ─ ");
        System.out.println("║                            " + Colores.RESET + "INTRODUZCA SU CORREO ELECTRÓNICO");
        System.out.println(Colores.PURPLE + "╚═══════════════════════════════════════════════───────────────────────────────── ─── ── ─ ");
        System.out.print("║-\\ ");
        System.out.print(Colores.RESET);
        String email = scanner.nextLine();

        System.out.println(Colores.PURPLE + "╔═════════════════════════════════════════════──────────────────────────────── ─── ── ─   ─ ");
        System.out.println("║                                " + Colores.RESET + "INTRODUZCA SU CONTRASEÑA");
        System.out.println(Colores.PURPLE + "╚═══════════════════════════════════════════════───────────────────────────────── ─── ── ─ ");
        System.out.print("║-\\ ");
        System.out.print(Colores.RESET);
        String contrasena = scanner.nextLine();

        try (Connection conn = Conexion.obtenerConexion()) {
            String sql = "SELECT * FROM usuarios WHERE email = ? AND contrasena = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, contrasena);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nombre = rs.getString("nombre");
                int uID = rs.getInt("id");
                return new Usuario(nombre, email, contrasena, uID);
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.out.println("|| Error en SQL al iniciar sesión: " + e.getMessage());
            return null;
        }
    }

    // Getters and Setters

    /**
     * Returns the name of the user.
     *
     * @return the user's name.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Returns the unique ID of the user.
     *
     * @return the user's unique ID.
     */
    public static int getUsuarioID() {
        return usuarioID;
    }

    /**
     *  Returns a string representation of the "Usuario" object.
     *
     * @return a string representing the user.
     */
    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", contrasena='" + contrasena + '\'' +
                '}';
    }
}
