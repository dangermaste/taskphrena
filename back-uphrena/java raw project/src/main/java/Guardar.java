import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Guardar class provides methods for saving and loading tasks from the database.
 * This class interacts with the database to persist tasks and retrieve them based on the logged-in user's ID.
 */
public class Guardar {

    /**
     * Saves all tasks to the database for a specific user.
     * Iterates over each task and calls the "guardarBBDD" method of the "Tarea" class
     * to persist each task in the database.
     *
     * @param tareas The list of tasks to be saved.
     * @param usuarioLogin The currently logged-in user, whose tasks will be saved.
     */
    public static void guardarFinal(List<Tarea> tareas, Usuario usuarioLogin) {
        try (Connection conn = Conexion.obtenerConexion()) {

            // Save tasks to the database
            for (Tarea tarea : tareas) {
                tarea.guardarBBDD(conn, usuarioLogin);
            }

            System.out.println("║- " + Colores.BOLD + "Guardando...");

        } catch (SQLException e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }

    /**
     * Loads tasks from the database for a user and adds them to the provided list.
     * This method queries the database for tasks associated with the given user and populates the
     * provided list with "Tarea" objects created from the retrieved data.
     *
     * @param tareas The list where tasks will be loaded and added.
     * @param usuarioLogin The currently logged-in user, whose tasks will be loaded.
     */
    public static void cargarTareasDesdeBBDD(List<Tarea> tareas, Usuario usuarioLogin) {
        String sql = "SELECT nombreTarea, fechaCreacion, fechaLimite, tipoTarea, descripcion, completado, id FROM tareas WHERE usuario_id = ?";
        try (Connection conn = Conexion.obtenerConexion()) {
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, usuarioLogin.getUsuarioID());
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        String nombre = rs.getString("nombreTarea");
                        LocalDateTime fechaCreacion = rs.getTimestamp("fechaCreacion").toLocalDateTime();
                        LocalDateTime deadline = rs.getTimestamp("fechaLimite").toLocalDateTime();
                        TipoTarea tipo = TipoTarea.valueOf(rs.getString("tipoTarea"));
                        String descripcion = rs.getString("descripcion");
                        boolean completado = rs.getBoolean("completado");
                        int tareaId = rs.getInt("id");

                        tareas.add(new Tarea(nombre, fechaCreacion, deadline, tipo, descripcion, completado, tareaId));

                    }
                }
            } catch (SQLException e) {
                System.out.println("Error al cargar tareas: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.err.println("ERROR: " + e.getMessage());
        }
    }
}