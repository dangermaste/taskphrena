import java.time.*;
import java.time.format.TextStyle;
import java.util.*;
import java.time.LocalDateTime;

/**
 * Calendario class represents a calendar functionality where users can view, add, edit, and delete tasks,
 * as well as navigate through months and view tasks associated with specific dates.
 */
public class Calendario {
    private LocalDate fechaActual;
    private List<Tarea> tareas;

    /**
     * Constructor for the Calendario class. Initializes the current date and the list of tasks.
     */
    public Calendario() {
        this.fechaActual = LocalDate.now();
        this.tareas = new ArrayList<>();
    }

    /**
     * Displays the calendar menu and processes user input for navigation and task management.
     *
     * @param scanner Scanner for reading user input.
     * @param usuarioLogin The logged-in user.
     * @return true if the menu was navigated successfully.
     */
    public boolean MenuCalendario(Scanner scanner, Usuario usuarioLogin) {
        Guardar.cargarTareasDesdeBBDD(tareas, usuarioLogin);
        int use = 0;
        int opcion;

        String login = Colores.RESET + "                          █ Bienvenid@, " + usuarioLogin.getNombre() + " a "+ Colores.YELLOW + "TASK" + Colores.RESET + "PHRENA©! █ " + Colores.PURPLE;
        String invalid ="║╝                    " + Colores.RED + "OPCIÓN INVALIDA! | USA UNA DE LAS SEIS OPCIONES" + Colores.PURPLE + "                    ╚║";
        String error1 ="║╝                   " + Colores.RED + "OPCIÓN FALLIDA! | NO SE HA PODIDO AGREGAR LA TAREA" + Colores.PURPLE + "                  ╚║";
        String error2 ="║╝                   " + Colores.RED + "OPCIÓN FALLIDA! | NO SE HA PODIDO EDITAR LA TAREA" + Colores.PURPLE + "                   ╚║";
        String error3 ="║╝                  " + Colores.RED + "OPCIÓN FALLIDA! | NO SE HA PODIDO ELIMINAR LA TAREA" + Colores.PURPLE + "                  ╚║";
        String success1 ="║╝                    " + Colores.GREEN + "OPCIÓN REALIZADA CON ÉXITO! | TAREA AGREGADA" + Colores.PURPLE + "                       ╚║";
        String success2 ="║╝                     " + Colores.GREEN + "OPCIÓN REALIZADA CON ÉXITO! | TAREA EDITADA" + Colores.PURPLE + "                       ╚║";
        String success3 ="║╝                    " + Colores.GREEN + "OPCIÓN REALIZADA CON ÉXITO! | TAREA ELIMINADA" + Colores.PURPLE + "                      ╚║";
        do {
            Visual.imprimirCalendarioMenu(use, login, invalid, error1, success1, error2, success2, error3, success3);
            System.out.print("║-\\ ");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    mostrarCalendario(scanner, usuarioLogin);
                    break;
                case 2:
                    if (Tarea.agregarTarea(tareas, scanner, usuarioLogin)) {
                        use = 2;
                    } else {
                        use = 3;
                    }

                    break;
                case 3:
                    Tarea.mostrarTareas(tareas, scanner, usuarioLogin);
                    break;
                case 4:
                    if (Tarea.editarTarea(tareas, scanner, usuarioLogin)) {
                        use = 4;
                    } else {
                        use = 5;
                    }
                    break;
                case 5:
                    if (Tarea.eliminarTarea(tareas, scanner, usuarioLogin)) {
                        use = 6;
                    } else {
                        use = 7;
                    }
                    break;
                case 6:
                    Visual.imprimirSalirCalendarioMenu(scanner);
                    System.out.print("║-\\ ");
                    String opt = scanner.nextLine();
                    if (opt.isBlank()) {
                        return true;
                    } else {
                        continue;
                    }
                default:
                    use = 1;
            }
        } while (opcion != 7);
        return true;
    }

    /**
     * Displays the calendar for the current month and allows users to navigate between months.
     *
     * @param scanner Scanner for reading user input.
     * @param usuarioLogin The logged-in user.
     */
    public void mostrarCalendario(Scanner scanner, Usuario usuarioLogin) {
        tareas.clear();
        Guardar.cargarTareasDesdeBBDD(tareas, usuarioLogin);
        Month mes = fechaActual.getMonth();
        int year = fechaActual.getYear();
        String nombreMes = mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES")).toUpperCase() + " " + year;
        String[] diasSemana = {" LUN ", " MAR ", " MIÉ ", " JUE ", " VIE ", " SÁB ", " DOM "};
        int anchoCelda = 7;
        LocalDate inicioMes = LocalDate.of(year, mes, 1);
        LocalDate finMes = LocalDate.of(year, mes, mes.length(false));
        int diaInicio = (inicioMes.getDayOfWeek().getValue() % 7);


        System.out.println("╔═════════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║         " + Colores.YELLOW + "╭─────────────────────────────────────────────────────────────────────╮" + Colores.RESET + "         ║");
        System.out.println("║         " + Colores.YELLOW + "│                             " + nombreMes + Colores.RESET);
        System.out.print("║         " + Colores.YELLOW + "│");
        for (int i = 0; i < 7; i++) {
            System.out.print("──────────");
        }
        System.out.println("\b│         "+ Colores.RESET + "║");
        // LUN, MAR, MIÉ... //
        System.out.print("║         " + Colores.YELLOW + "│");
        for (String dia : diasSemana) {
            if (dia.equals(" SÁB ") || dia.equals(" DOM ")){
                System.out.printf(Colores.CYAN + "  %-" + anchoCelda + "s" + Colores.YELLOW + "│", dia);
            } else {
                System.out.printf(Colores.RED + "  %-" + anchoCelda + "s" + Colores.YELLOW + "│", dia);
            }
        }
        System.out.print("         " + Colores.RESET + "║");
        System.out.println();
        System.out.print("║         " + Colores.YELLOW + "│");
        for (int i = 0; i < diasSemana.length; i++) {
            System.out.print("─────────" + "│");
        }
        System.out.println("\b│ " + Colores.RESET + "        ║");
        int dia = 1;
        for (int i = 0; i < 6; i++) {
            System.out.print("║         " + Colores.YELLOW + "│");
            for (int j = 0; j < 7; j++) {
                if ((i == 0 && j < diaInicio) || dia > finMes.getDayOfMonth()) { // Esto imprime las celdas vacías
                    System.out.printf("%" + (anchoCelda + 1) + "s │", "");
                } else {                                                        // Esto imprime los números
                    LocalDate fecha = LocalDate.of(year, mes, dia);
                    String textoDia = String.format("%2d", dia);
                    if (fecha.isEqual(LocalDate.now())) {                       // Si el día a imprimir es el de hoy, lo pinta de otro color
                        System.out.printf("    %s%s%s   │", Colores.CYAN + Colores.BOLD , textoDia, Colores.YELLOW);
                    } else if (tieneTarea(fecha)) {                             //
                        switch (getTaskStatusForDate(fecha)) {
                            case 0:
                                System.out.printf("    %s%s%s   │", Colores.PURPLE + Colores.BOLD , textoDia, Colores.YELLOW);
                                break;
                            case 1:
                                System.out.printf("    %s%s%s   │", Colores.GREEN + Colores.BOLD , textoDia, Colores.YELLOW);
                                break;
                            case 2:
                                System.out.printf("    %s%s%s   │", Colores.RED + Colores.BOLD , textoDia, Colores.YELLOW);
                        }
                    } else {
                        System.out.printf("    %s%s%s   │", Colores.RESET, textoDia, Colores.YELLOW);
                    }
                    dia++;
                }
            }
            System.out.print(Colores.RESET + "         ║");
            System.out.println();

            if (dia > finMes.getDayOfMonth()) break;
            System.out.print("║         " + Colores.YELLOW + "│");
            for (int j = 0; j < 7; j++) {
                System.out.print("─────────│");
            }
            System.out.println("\b│         " + Colores.RESET + "║");
        }
        System.out.print("║         " + Colores.YELLOW + "╰");
        for (int i = 0; i < 7; i++) {
            System.out.print("──────────");
        }
        System.out.println("\b╯         " + Colores.RESET + "║");
        System.out.println("╠─────────────────────────────────────────────────────────────────────────────────────────╣");
        System.out.println("║       Pulsa [ " + Colores.PURPLE + Colores.BOLD + "1" + Colores.RESET + " ] para ver el "  + Colores.PURPLE + Colores.BOLD +  "MES ANTERIOR" + Colores.RESET + ", Pulsa [ " + Colores.CYAN + Colores.BOLD + "2 " + Colores.RESET + "] para ver el " + Colores.CYAN + Colores.BOLD + "MES SIGUIENTE       " + Colores.RESET + "║");
        String d = scanner.nextLine();
        if (d.equals("1")) {
            retrocederMes(scanner, usuarioLogin);
        } else if (d.equals("2")) {
            avanzarMes(scanner, usuarioLogin);
        }
        System.out.println("╚═════════════════════════════════════════════════════════════════════════════════════════╝");
        // Prompt to press space when the user wants to go back
        String space = scanner.nextLine();
    }

    /**
     * Moves the calendar view to the previous month and displays it.
     *
     * @param scanner Scanner for reading user input.
     * @param usuarioLogin The logged-in user.
     */
    public void retrocederMes(Scanner scanner, Usuario usuarioLogin) {
        fechaActual = fechaActual.minusMonths(1);
        mostrarCalendario(scanner, usuarioLogin);
    }

    /**
     * Moves the calendar view to the next month and displays it.
     *
     * @param scanner Scanner for reading user input.
     * @param usuarioLogin The logged-in user.
     */
    public void avanzarMes(Scanner scanner, Usuario usuarioLogin) {
        fechaActual = fechaActual.plusMonths(1);
        mostrarCalendario(scanner, usuarioLogin);
    }

    /**
     * Checks the task status for a specific date.
     *
     * @param fecha The date to check.
     * @return An integer representing the task status:
     *         0 - task is upcoming,
     *         1 - task is completed in time,
     *         2 - task is overdue.
     */
    private int getTaskStatusForDate(LocalDate fecha) {
        for (Tarea tarea : tareas) {
            LocalDateTime deadline;
            LocalDateTime now = LocalDateTime.now();
            if (tarea.getDeadline().toLocalDate().isEqual(fecha)) {

                deadline = tarea.getDeadline();
                if (now.isAfter(deadline)) {
                    return tarea.isCompletado() ? 1 : 2;
                }
                return 0;
            }
        }
        return 3;
    }

    /**
     * Checks if there are any tasks assigned to a specific date.
     *
     * @param fecha The date to check.
     * @return true if there are tasks on the given date / false otherwise.
     */
    private boolean tieneTarea(LocalDate fecha) {
        for (Tarea tarea : tareas) {
            if (tarea.getDeadline().toLocalDate().isEqual(fecha)) return true;
        }
        return false;
    }
}