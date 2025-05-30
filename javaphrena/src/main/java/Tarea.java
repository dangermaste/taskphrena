import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

/**
 * Tarea class represents a task in the system with attributes such as name, start date, deadline,
 * task type, description, completion status, and task ID. Provides methods to manage
 * tasks, including adding, editing, displaying, and deleting tasks.
 */
public class Tarea {
    private String nombre;
    private LocalDateTime inicio;
    private LocalDateTime deadline;
    private TipoTarea tipo;
    private String descripcion;
    private boolean completado;
    private int tareaID;

    /**
     * Constructor for creating a task with specified attributes.
     *
     * @param nombre the name of the task
     * @param inicio the start date and time of the task
     * @param deadline the deadline of the task
     * @param tipo the type of the task (e.g., low priority)
     * @param descripcion the description of the task
     * @param completado the completion status of the task
     * @param tareaId the unique ID for the task
     */
    public Tarea(String nombre, LocalDateTime inicio, LocalDateTime deadline, TipoTarea tipo, String descripcion, boolean completado, int tareaId) {
        this.nombre = nombre;
        this.inicio = inicio;
        this.deadline = deadline;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.completado = completado;
        this.tareaID = tareaId;
    }

    /**
     * Saves the task to the database.
     *
     * @param conn the connection to the database
     * @param usuarioLogin the logged-in user
     * @throws SQLException if there is an error executing the SQL query
     */
    public void guardarBBDD(Connection conn, Usuario usuarioLogin) throws SQLException {
        String sql = "INSERT INTO tareas (nombreTarea, fechaCreacion, fechaLimite, tipoTarea, descripcion, completado, usuario_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setTimestamp(2, java.sql.Timestamp.valueOf(inicio));
            stmt.setTimestamp(3, java.sql.Timestamp.valueOf(deadline));
            stmt.setString(4, tipo.toString());
            stmt.setString(5, descripcion);
            stmt.setBoolean(6, completado);
            stmt.setInt(7, usuarioLogin.getUsuarioID());
            stmt.executeUpdate();
        }
    }

    /**
     * Adds a new task by getting details from user input.
     *
     * @param tareas the list of tasks to which the new task will be added
     * @param scanner the scanner for user input
     * @param usuarioLogin the logged-in user
     * @return true if the task is successfully added / false otherwise
     */
    public static boolean agregarTarea(List<Tarea> tareas, Scanner scanner, Usuario usuarioLogin) {
        String space = scanner.nextLine();
        tareas.clear();
        Guardar.cargarTareasDesdeBBDD(tareas, usuarioLogin);
        System.out.print(Colores.GREEN);
        System.out.println("╔═════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                                                         ║");
        System.out.println("║                              " + Colores.RESET + Colores.BOLD + "- A G R E G A R   T A R E A -                              " + Colores.GREEN + "║");
        System.out.println("║                                                                                         ║");
        System.out.println("╚═════════════════════════════════════════════════════════════════════════════════════════╝");

        String nombre = "";
        while (nombre.isBlank()) {
        System.out.println("╔═════════════════════════════════════════════──────────────────────────────── ─── ── ─   ─ ");
        System.out.println("║                            " + Colores.RESET + "   INTRODUZCA EL " + Colores.BOLD + "NOMBRE " + Colores.RESET + "DE LA TAREA");
        System.out.println(Colores.GREEN + "╚═══════════════════════════════════════════════───────────────────────────────── ─── ── ─ ");
        System.out.print("║-\\ ");
        nombre = scanner.nextLine();
        if (nombre.isBlank()) {
            System.out.println("║- " + Colores.RED + "FORMATO INCORRECTO! NO SE PUEDE DEJAR EL NOMBRE VACÍO!" + Colores.GREEN);
            break;
        }
        }

        LocalDate deadline = null;
        while (deadline == null) {
            System.out.println("╔═════════════════════════════════════════════──────────────────────────────── ─── ── ─   ─ ");
            System.out.println("║                            " + Colores.RESET + "INTRODUZCA LA " + Colores.BOLD + "FECHA LÍMITE");
            System.out.println(Colores.GREEN + "╚═══════════════════════════════════════════════───────────────────────────────── ─── ── ─ ");
            System.out.print("║-\\ ");
            String fechaStr = scanner.nextLine();
            deadline = parseFecha(fechaStr);
        }

        System.out.println("╔═════════════════════════════════════════════──────────────────────────────── ─── ── ─   ─ ");
        System.out.println("║                            " + Colores.RESET + " INTRODUZCA LA " + Colores.BOLD + "DESCRIPCIÓN" + Colores.RESET + " DE LA TAREA");
        System.out.println(Colores.GREEN + "╚═══════════════════════════════════════════════───────────────────────────────── ─── ── ─ ");
        System.out.print("║-\\ ");
        String descripcion = scanner.nextLine();
        boolean completado = false;
        tareas.clear();
        tareas.add(new Tarea(nombre, LocalDate.now().atStartOfDay(), deadline.atStartOfDay(), TipoTarea.BAJA, descripcion, completado, Usuario.getUsuarioID()));
        Guardar.guardarFinal(tareas, usuarioLogin);
        System.out.println();
        return true;

    }

    /**
     * Parses a string representing a date into a LocalDate object.
     *
     * @param fechaStr the string representing the date in the format YYYY-MM-DD
     * @return a LocalDate object representing the parsed date / null if the input is invalid
     */
    public static LocalDate parseFecha(String fechaStr) {
        try {
            return LocalDate.parse(fechaStr);
        } catch (Exception e) {
            System.out.println("║- " + Colores.RED + "FORMATO INCORRECTO! (YYYY-MM-DD)" + Colores.GREEN);
            return null;
        }
    }

    /**
     * Displays a list of tasks.
     *
     * @param tareas the list of tasks to be displayed
     * @param scanner the scanner for user input
     * @param usuarioLogin the logged-in user
     */
    public static void mostrarTareas(List<Tarea> tareas, Scanner scanner, Usuario usuarioLogin) {
        tareas.clear();
        Guardar.cargarTareasDesdeBBDD(tareas, usuarioLogin);
        String space = scanner.nextLine();
        System.out.print(Colores.YELLOW);
        System.out.println("╔═════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                                                         ║");
        System.out.println("║                            - L I S T A   D E   T A R E A S -                            ║");
        System.out.println("║                                                                                         ║");
        System.out.println("╚═════════════════════════════════════════════════════════════════════════════════════════╝");
        if (tareas.isEmpty()) {
            System.out.println("║- " + Colores.RED + Colores.BOLD + "NO HAY TAREAS REGISTRADAS!" + Colores.RESET);
        } else {
            for (Tarea tarea : tareas) {
                System.out.println(mostrarDetalles(tarea));
            }
        }
        space = scanner.nextLine();
    }

    /**
     * Displays the details of a task.
     *
     * @param tarea the task whose details are to be displayed
     * @return a string representing the formatted task details
     */
    public static String mostrarDetalles(Tarea tarea) {
        StringBuilder sb = new StringBuilder();
        sb.append("╔──────────────────────────────────────────────────────── ───── ──── ── ─   ─ \n");
        sb.append("║" + Colores.YELLOW + " TAREA ║      " + Colores.RESET + Colores.BOLD + Colores.UNDERLINE).append(tarea.nombre).append(Colores.RESET + Colores.YELLOW + "\n");
        sb.append("╚──────────────────╗╔────────────────────────────────────────────────────── ─ \n");
        sb.append("║" + Colores.PURPLE + "      INICIO      " + Colores.YELLOW + "║║      " + Colores.RESET + Colores.BOLD).append(tarea.inicio.toLocalDate()).append("\n" + Colores.YELLOW);
        sb.append("║" + Colores.PURPLE + "   FECHA LÍMITE   " + Colores.YELLOW + "║║      " + Colores.RESET + Colores.BOLD).append(tarea.deadline.toLocalDate()).append("\n" + Colores.YELLOW);
        sb.append("║" + Colores.PURPLE + "       TIPO       " + Colores.YELLOW + "║║      " + Colores.RESET + Colores.BOLD).append(tarea.tipo).append("\n" + Colores.YELLOW);
        sb.append("║" + Colores.PURPLE + "    DESCRIPCIÓN   " + Colores.YELLOW + "║║      " + Colores.RESET + Colores.BOLD).append(tarea.descripcion).append("\n" + Colores.YELLOW);
        sb.append("║" + Colores.PURPLE + "    COMPLETADO?   " + Colores.YELLOW + "║║      ").append(tarea.completado ? "✅" : "❌").append("\n" + Colores.YELLOW);
        sb.append("╚──────────────────╝╚──────────────────────────────────── ──── ─  ─── ─    ─ \n");

        return sb.toString();
    }
    /**
     * Edits an existing task based on user input.
     *
     * @param tareas the list of tasks to edit
     * @param scanner the scanner for user input
     * @param usuarioLogin the logged-in user
     * @return true if the task is successfully edited / false otherwise
     */
    public static boolean editarTarea(List<Tarea> tareas, Scanner scanner, Usuario usuarioLogin) {
        String space = scanner.nextLine();
        tareas.clear();
        Guardar.cargarTareasDesdeBBDD(tareas, usuarioLogin);
        System.out.print(Colores.CYAN);
        System.out.println("╔═════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                                                         ║");
        System.out.println("║                               " + Colores.RESET + Colores.BOLD + "- E D I T A R   T A R E A -                               " + Colores.CYAN + "║");
        System.out.println("║                                                                                         ║");
        System.out.println("╚═════════════════════════════════════════════════════════════════════════════════════════╝");

        if (tareas.isEmpty()) {
            System.out.print(Colores.CYAN);
            System.out.println("╔═════════════════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                                                                                         ║");
            System.out.println("║                            " + Colores.RED + Colores.BOLD + "NO HAY TAREAS DISPONIBLES PARA EDITAR" + Colores.CYAN + "                             ║");
            System.out.println("║                                                                                         ║");
            System.out.println("╚═════════════════════════════════════════════════════════════════════════════════════════╝" + Colores.RESET);
            return false;
        }

        for (int i = 0; i < tareas.size(); i++) {
            Tarea tarea = tareas.get(i);
            System.out.println(Colores.CYAN + "║- " + Colores.RESET + Colores.BOLD + i + ". " + Colores.PURPLE + Colores.UNDERLINE + tarea.getNombre() + Colores.RESET);
        }

        System.out.print(Colores.CYAN);
        System.out.println("╔═════════════════════════════════════════════──────────────────────────────── ─── ── ─   ─ ");
        System.out.println("║                         " + Colores.RESET + "INGRESE EL NÚMERO DE LA TAREA A EDITAR");
        System.out.println(Colores.CYAN + "╚═══════════════════════════════════════════════───────────────────────────────── ─── ── ─ ");
        System.out.print("║-\\ ");
        int index = leerIndice(scanner, tareas.size());
        if (index == -1) return false;

        Tarea tarea = tareas.get(index);
        int id = tarea.getTareaID();
        System.out.println(id);

        System.out.println("╔═════════════════════════════════════════════──────────────────────────────── ─── ── ─   ─ ");
        System.out.println("║ " + Colores.RESET + "NOMBRE NUEVO PARA LA TAREA (ACTUAL: " + tarea.getNombre().toUpperCase() + ")");
        System.out.println(Colores.CYAN + "╚═══════════════════════════════════════════════───────────────────────────────── ─── ── ─ ");
        System.out.print("║-\\ ");
        String nombre = scanner.nextLine();
        if (!nombre.isBlank()) tarea.setNombre(nombre);

        System.out.println("╔═════════════════════════════════════════════──────────────────────────────── ─── ── ─   ─ ");
        System.out.println("║ " + Colores.RESET + "DESCRIPCIÓN NUEVA PARA LA TAREA (ACTUAL: " + tarea.getDescripcion().toUpperCase() + ")");
        System.out.println(Colores.CYAN + "╚═══════════════════════════════════════════════───────────────────────────────── ─── ── ─ ");
        System.out.print("║-\\ ");
        String desc = scanner.nextLine();
        if (!desc.isBlank()) tarea.setDescripcion(desc);

        System.out.println("╔═════════════════════════════════════════════──────────────────────────────── ─── ── ─   ─ ");
        System.out.println("║ " + Colores.RESET + "EDITAR FECHA LÍMITE |YYYY-MM-DD| (ACTUAL: " + tarea.getDeadline() + ")");
        System.out.println(Colores.CYAN + "╚═══════════════════════════════════════════════───────────────────────────────── ─── ── ─ ");
        System.out.print("║-\\ ");
        String fechaStr = scanner.nextLine();
        if (!fechaStr.isBlank()) {
            LocalDate fecha = parseFecha(fechaStr);
            if (fecha != null) {
                tarea.setDeadline(fecha.atStartOfDay());
            }
        }

        System.out.println("╔═════════════════════════════════════════════──────────────────────────────── ─── ── ─   ─ ");
        System.out.println("║ " + Colores.RESET + "EDITAR IMPORTANCIA DE LA TAREA (ACTUAL: " + tarea.getTipo() + ")");
        System.out.println(Colores.CYAN + "╚═══════════════════════════════════════════════───────────────────────────────── ─── ── ─ ");
        System.out.print("║-\\ ");
        String tipoStr = scanner.nextLine();
        if (!tipoStr.isBlank()) {
            try {
                TipoTarea tipo = TipoTarea.valueOf(tipoStr.toUpperCase());
                tarea.setTipo(tipo);
            } catch (IllegalArgumentException e) {
                System.out.println("║- " + Colores.RED + Colores.BOLD + "TIPO INVÁLIDO! NO SE REALIZAN CAMBIOS AL TIPO." + Colores.CYAN);
            }
        }

        System.out.println("╔═════════════════════════════════════════════──────────────────────────────── ─── ── ─   ─ ");
        System.out.println("║ " + Colores.RESET + "¿MARCAR TAREA COMO COMPLETADA? " + Colores.CYAN + Colores.BOLD + "Y " + Colores.RESET + "/ " + Colores.RED + Colores.BOLD + "N");
        System.out.println(Colores.CYAN + "╚═══════════════════════════════════════════════───────────────────────────────── ─── ── ─ ");
        System.out.print("║-\\ ");
        String comp = scanner.nextLine();
        if (comp.equalsIgnoreCase("y")) {
            tarea.setCompletado(true);
        } else if (comp.equalsIgnoreCase("n")) {
            tarea.setCompletado(false);
        }

        try (Connection conn = Conexion.obtenerConexion()) {
            String sql = "UPDATE tareas SET nombreTarea = ?, fechaLimite = ?, tipoTarea = ?, descripcion = ?, completado = ? WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, tarea.getNombre());
                stmt.setTimestamp(2, java.sql.Timestamp.valueOf(tarea.getDeadline()));
                stmt.setString(3, tarea.getTipo().toString());
                stmt.setString(4, tarea.getDescripcion());
                stmt.setBoolean(5, tarea.isCompletado());
                stmt.setInt(6, id);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("ERROR: " + e.getMessage());
        }

        return true;
    }

    /**
     * Deletes a task based on user input.
     *
     * @param tareas the list of tasks to edit
     * @param scanner the scanner for user input
     * @param usuarioLogin the logged-in user
     * @return true if the task is successfully deleted / false otherwise
     */
    public static boolean eliminarTarea(List<Tarea> tareas, Scanner scanner, Usuario usuarioLogin) {
        String space = scanner.nextLine();
        tareas.clear();
        Guardar.cargarTareasDesdeBBDD(tareas, usuarioLogin);
        System.out.print(Colores.BLUE);
        System.out.println("╔═════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                                                         ║");
        System.out.println("║                               " + Colores.RESET + Colores.BOLD + "- E L I M I N A R   T A R E A -                               " + Colores.BLUE + "║");
        System.out.println("║                                                                                         ║");
        System.out.println("╚═════════════════════════════════════════════════════════════════════════════════════════╝");

        if (tareas.isEmpty()) {
            System.out.print(Colores.BLUE);
            System.out.println("╔═════════════════════════════════════════════════════════════════════════════════════════╗");
            System.out.println("║                                                                                         ║");
            System.out.println("║                            " + Colores.RED + Colores.BOLD + "NO HAY TAREAS DISPONIBLES PARA ELIMINAR" + Colores.BLUE + "                             ║");
            System.out.println("║                                                                                         ║");
            System.out.println("╚═════════════════════════════════════════════════════════════════════════════════════════╝" + Colores.RESET);
            return false;
        }

        for (int i = 0; i < tareas.size(); i++) {
            Tarea tarea = tareas.get(i);
            System.out.println(Colores.BLUE + "║- " + Colores.RESET + Colores.BOLD + i + ". " + Colores.PURPLE + Colores.UNDERLINE + tarea.getNombre() + Colores.RESET);
        }

        System.out.print(Colores.BLUE);
        System.out.println("╔═════════════════════════════════════════════──────────────────────────────── ─── ── ─   ─ ");
        System.out.println("║                         " + Colores.RESET + "INGRESE EL NÚMERO DE LA TAREA A ELIMINAR");
        System.out.println(Colores.BLUE + "╚═══════════════════════════════════════════════───────────────────────────────── ─── ── ─ ");
        System.out.print("║-\\ ");
        int index = Tarea.leerIndice(scanner, tareas.size());
        if (index == -1) return false;

        Tarea tarea = tareas.get(index);
        int id = tarea.getTareaID();
        System.out.print(Colores.BLUE);
        System.out.println("╔═════════════════════════════════════════════──────────────────────────────── ─── ── ─   ─ ");
        System.out.println("║                         " + Colores.RESET + "ESTÁ A PUNTO DE ELIMNAR LA TAREA '" + tareas.get(index).getNombre() + "', ESTÁS SEGUR@? "+ Colores.CYAN + Colores.BOLD + "Y " + Colores.RESET + "/ " + Colores.RED + Colores.BOLD + "N");
        System.out.println(Colores.BLUE + "╚═══════════════════════════════════════════════───────────────────────────────── ─── ── ─ ");
        System.out.print("║-\\ ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("y")) {
            try (Connection conn = Conexion.obtenerConexion()) {
                String sql = "DELETE FROM tareas WHERE id = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setInt(1, id);
                    stmt.executeUpdate();
                }

            } catch (SQLException e) {
                System.err.println("ERROR: " + e.getMessage());
            }
            tareas.remove(index);
            return true;
        } else {
            return false;
        }
    }


    static int leerIndice(Scanner scanner, int max) {
        int index = -1;
        try {
            index = Integer.parseInt(scanner.nextLine());
            if (index < 0 || index >= max) {
                System.out.println("Índice fuera de rango.");
                return -1;
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida.");
            return -1;
        }
        return index;
    }
    // Getters & setters

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public LocalDateTime getDeadline() {
        return deadline;
    }
    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }
    public TipoTarea getTipo() {
        return tipo;
    }
    public void setTipo(TipoTarea tipo) {
        this.tipo = tipo;
    }
    public boolean isCompletado() {
        return completado;
    }
    public void setCompletado(boolean completado) {
        this.completado = completado;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getTareaID(){
        return tareaID;
    }
}
