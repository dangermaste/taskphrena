import java.util.Scanner;
/**
 * Visual class is responsible for displaying the application's various user interface screens,
 * including the main menu, calendar menu, and messages related to log-in, registration, task
 * management, and user interactions.
 *
 * It uses ASCII art for the graphical interface and includes dynamic message displays.
 */

public class Visual {
    /**
     * Prints the main menu with ASCII logo, options, and dynamic messages.
     *
     * @param use Indicator of previous interaction status (0: normal, 1: login error, etc.).
     * @param normal Default normal message (e.g., copyright).
     * @param error1 Error message for login.
     * @param error2 Error message for registration.
     * @param success1 Success message for registration.
     * @param invalid Message for invalid option.
     */
    public static void imprimirPrimerMenu(int use, String normal, String error1, String error2, String success1, String invalid){
        System.out.println("╔╔═══════════════════════════════════════════════════════════════════════════════════════╗╗");
        System.out.println("║║                                                                                       ║║");
        System.out.println("║║  " + Colores.PURPLE + Colores.BOLD + "████████╗ █████╗ ███████╗██╗  ██╗" + Colores.RESET + "██████╗ ██╗  ██╗██████╗ ███████╗███╗   ██╗ █████╗   ║║");
        System.out.println("║║  " + Colores.PURPLE + Colores.BOLD + "╚══██╔══╝██╔══██╗██╔════╝██╗██╔╝ " + Colores.RESET + "██╔══██╗██║  ██║██╔══██╗██╔════╝████╗  ██║██╔══██╗  ║║");
        System.out.println("║║  " + Colores.PURPLE + Colores.BOLD + "   ██║   ███████║███████╗███╔╝   " + Colores.RESET + "██████╔╝███████║██████╔╝█████╗  ██╔██╗ ██║███████║  ║║");
        System.out.println("║║  " + Colores.PURPLE + Colores.BOLD + "   ██║   ██╔══██║╚════██║██║██╗  " + Colores.RESET + "██╔═══╝ ██╔══██║██╔══██╗██╔══╝  ██║╚██╗██║██╔══██║  ║║");
        System.out.println("║║  " + Colores.PURPLE + Colores.BOLD + "   ██║   ██║  ██║███████║██║ ╚██╗" + Colores.RESET + "██║     ██║  ██║██║  ██║███████╗██║ ╚████║██║  ██║  ║║");
        System.out.println("║║  " + Colores.PURPLE + Colores.BOLD + "   ╚═╝   ╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝" + Colores.RESET + "╚═╝     ╚═╝  ╚═╝╚═╝  ╚═╝╚══════╝╚═╝  ╚═══╝╚═╝  ╚═╝  ║║");
        System.out.println("║║                                                                                       ║║");
        System.out.println("║╠───────────────────────────────────────────────────────────────────────────────────────╣║");
        System.out.println("║╝     ╔─────────╗╔────────────────────────────────────────────────────────────────╗     ╚║");
        System.out.println("║      ║─   " + Colores.PURPLE + Colores.BOLD + "1   " + Colores.RESET + "─║║-" + Colores.PURPLE + Colores.BOLD + " █                     -INICIAR SESIÓN-                     █ " + Colores.RESET + "─║      ║");
        System.out.println("║      ╠─────────╣╠────────────────────────────────────────────────────────────────╣      ║");
        System.out.println("║      ║─   " + Colores.BLUE + Colores.BOLD + "2   " + Colores.RESET + "─║║-" + Colores.BLUE + Colores.BOLD + " █ █                  -REGISTRAR CUENTA-                  █ █ " + Colores.RESET + "─║      ║");
        System.out.println("║      ╠─────────╣╠────────────────────────────────────────────────────────────────╣      ║");
        System.out.println("║      ║─   " + Colores.RED + Colores.BOLD + "3   " + Colores.RESET + "─║║-" + Colores.RED + Colores.BOLD + " █ █ █                     -SALIR-                      █ █ █ " + Colores.RESET + "─║      ║");
        System.out.println("║╗     ╚─────────╝╚────────────────────────────────────────────────────────────────╝     ╔║");
        System.out.println("║╠───────────────────────────────────────────────────────────────────────────────────────╣║");
        if (use == 0) {
            System.out.println("║╝" + normal + "╚║");
        } else if (use == 1) {
            System.out.println("║╝" + error1 + "╚║");
        } else if (use == 2){
            System.out.println("║╝" + error2 + "╚║");
        } else if (use == 3){
            System.out.println("║╝" + success1 + "╚║");
        } else {
            System.out.println("║╝" + invalid + "╚║");
        }
        System.out.println("╚═════════════════════════════════════════════════════════════════════════════════════════╝");
    }

    /**
     * Prints an ASCII message when the user exits the application.
     */
    public static void imprimirSalirPrimerMenu(){
        System.out.print(Colores.RED);
        System.out.println("╔╔═══════════════════════════════════════════════════════════════════════════════════════╗╗");
        System.out.println("║║                                                                                       ║║");
        System.out.println("║║  " + Colores.RED + Colores.BOLD + "████████╗ █████╗ ███████╗██╗  ██╗" + Colores.RESET + "██████╗ ██╗  ██╗██████╗ ███████╗███╗   ██╗ █████╗   " + Colores.RED + "║║");
        System.out.println("║║  " + Colores.RED + Colores.BOLD + "╚══██╔══╝██╔══██╗██╔════╝██╗██╔╝ " + Colores.RESET + "██╔══██╗██║  ██║██╔══██╗██╔════╝████╗  ██║██╔══██╗  " + Colores.RED + "║║");
        System.out.println("║║  " + Colores.RED + Colores.BOLD + "   ██║   ███████║███████╗███╔╝   " + Colores.RESET + "██████╔╝███████║██████╔╝█████╗  ██╔██╗ ██║███████║  " + Colores.RED + "║║");
        System.out.println("║║  " + Colores.RED + Colores.BOLD + "   ██║   ██╔══██║╚════██║██║██╗  " + Colores.RESET + "██╔═══╝ ██╔══██║██╔══██╗██╔══╝  ██║╚██╗██║██╔══██║  " + Colores.RED + "║║");
        System.out.println("║║  " + Colores.RED + Colores.BOLD + "   ██║   ██║  ██║███████║██║ ╚██╗" + Colores.RESET + "██║     ██║  ██║██║  ██║███████╗██║ ╚████║██║  ██║  " + Colores.RED + "║║");
        System.out.println("║║  " + Colores.RED + Colores.BOLD + "   ╚═╝   ╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝" + Colores.RESET + "╚═╝     ╚═╝  ╚═╝╚═╝  ╚═╝╚══════╝╚═╝  ╚═══╝╚═╝  ╚═╝  " + Colores.RED + "║║");
        System.out.println("║║                                                                                       ║║");
        System.out.println("║╠───────────────────────────────────────────────────────────────────────────────────────╣║");
        System.out.println("║╝     ╔───────────────────────────────────────────────────────────────────────────╗     ╚║");
        System.out.println("║                              " + Colores.RESET + "GRACIAS POR USAR " + Colores.RED + "TASK" + Colores.RESET + "PHRENA!" + Colores.RED + "                               ║");
        System.out.println("║╗     ╚───────────────────────────────────────────────────────────────────────────╝     ╔║");
        System.out.println("╚╚═══════════════════════════════════════════════════════════════════════════════════════╝╝");
    }

    /**
     * Displays the calendar menu, where users can select options related to task management.
     *
     * @param use      |   Indicator of previous interaction status.
     * @param login    |   Message to display when logging in.
     * @param invalid  |   Message for invalid option.
     * @param error1   |   Error message related to calendar actions.
     * @param error2   |   Error message after a task operation.
     * @param error3   |   Error message related to task deletion.
     * @param success1 |   Success message after an action.
     * @param success2 |   Success message after a task operation.
     * @param success3 |   Success message related to task deletion.
     */
    public static void imprimirCalendarioMenu(int use, String login, String invalid, String error1, String success1, String error2, String success2, String error3, String success3){
        System.out.println(Colores.PURPLE);
        System.out.println("╔╔═══════════════════════════════════════════════════════════════════════════════════════╗╗");
        System.out.println("║║                                                                                       ║║");
        System.out.println("║║  " + Colores.YELLOW + "╭─────────────────────╮ " + Colores.RESET + " ████  ███  ██   ████ ████  ██ ████   ███  ████  ████  ███   " + Colores.PURPLE + "║║");
        System.out.println("║║  " + Colores.YELLOW + "│─────────────────────│ " + Colores.RESET + " ██   ██ ██ ██   ██   ██ █  ██ ██ ██ ██ ██ ██ ██  ██  ██ ██  " + Colores.PURPLE + "║║");
        System.out.println("║║  " + Colores.YELLOW + "│ " + Colores.RESET + "□  □  □  □  □  " + Colores.RED + "■ " + Colores.GREEN + " ■ " + Colores.YELLOW + "│" + Colores.RESET + "  ██   ██ ██ ██   ██   ██ ██ ██ ██ ██ ██ ██ ██ ██  ██  ██ ██  " + Colores.PURPLE + "║║");
        System.out.println("║║  " + Colores.YELLOW + "│ " + Colores.RESET + "□  □  □  □  □  " + Colores.CYAN + "■  " + Colores.RESET + "□ "+ Colores.YELLOW + "│" + Colores.RESET + "  ██   ██ ██ ██   ████ ██ ██ ██ ██ ██ ██ ██ █████  ██  ██ ██  " + Colores.PURPLE + "║║");
        System.out.println("║║  " + Colores.YELLOW + "│ " + Colores.RESET + "□  " + Colores.PURPLE + "■" + Colores.RESET + "  □  " + Colores.PURPLE + Colores.BOLD + "T  A  S  K " + Colores.YELLOW + "│ " + Colores.YELLOW + " ██   █████ ██   ██   ██ ██ ██ ██ ██ " + Colores.RESET + "█████ ████   ██  ██ ██  " + Colores.PURPLE + "║║");
        System.out.println("║║  " + Colores.YELLOW + "│ " + Colores.RESET + "□  " + Colores.BOLD + "P  H  R  E  N  A " + Colores.YELLOW + "│ " + Colores.YELLOW + " ██   ██ ██ ██   ██   ██  █ ██ ██ ██ ██ ██ ██ ██  ██  ██ ██  " + Colores.PURPLE + "║║");
        System.out.println("║║  " + Colores.YELLOW + "╰─────────────────────╯ " + Colores.YELLOW + " ████ ██ ██ ████ ████ ██  ████ ████  ██ ██ ██ ██ ████  ███   " + Colores.PURPLE + "║║");
        System.out.println("║║                                                                                       ║║");
        System.out.println("║╠───────────────────────────────────────────────────────────────────────────────────────╣║");
        System.out.println("║╝     ╔─────────╗╔────────────────────────────────────────────────────────────────╗     ╚║");
        System.out.println("║      ║─   " + Colores.RESET + Colores.BOLD + "1   " + Colores.PURPLE + "─║║- " + Colores.RESET + Colores.BOLD + "█                    - CALENDARIO -                        █ " + Colores.PURPLE + "─║      ║");
        System.out.println("║      ╠─────────╣╠────────────────────────────────────────────────────────────────╣      ║");
        System.out.println("║      ║─   " + Colores.GREEN + Colores.BOLD + "2   " + Colores.PURPLE + "─║║- " + Colores.GREEN + Colores.BOLD + "█ █                 - AGREGAR TAREA -                    █ █ " + Colores.PURPLE + "─║      ║");
        System.out.println("║      ╠─────────╣╠────────────────────────────────────────────────────────────────╣      ║");
        System.out.println("║      ║─   " + Colores.YELLOW + Colores.BOLD + "3   " + Colores.PURPLE + "─║║- " + Colores.YELLOW + Colores.BOLD + "█ █ █               - MOSTRAR TAREAS -                 █ █ █ " + Colores.PURPLE + "─║      ║");
        System.out.println("║      ╠─────────╣╠────────────────────────────────────────────────────────────────╣      ║");
        System.out.println("║      ║─   " + Colores.CYAN + Colores.BOLD + "4   " + Colores.PURPLE + "─║║- " + Colores.CYAN + Colores.BOLD + "█ █ █ █              - EDITAR TAREA -                █ █ █ █ " + Colores.PURPLE + "─║      ║");
        System.out.println("║      ╠─────────╣╠────────────────────────────────────────────────────────────────╣      ║");
        System.out.println("║      ║─   " + Colores.BLUE + Colores.BOLD + "5   " + Colores.PURPLE + "─║║- " + Colores.BLUE + Colores.BOLD + "█ █ █ █ █           - ELIMINAR TAREA -             █ █ █ █ █ " + Colores.PURPLE + "─║      ║");
        System.out.println("║      ╠─────────╣╠────────────────────────────────────────────────────────────────╣      ║");
        System.out.println("║      ║─   " + Colores.RED + Colores.BOLD + "6   " + Colores.PURPLE + "─║║- " + Colores.RED + "█ █ █ █ █ █          - CERRAR SESIÓN -           █ █ █ █ █ █ " + Colores.PURPLE + "─║      ║");
        System.out.println("║╗     ╚─────────╝╚────────────────────────────────────────────────────────────────╝     ╔║");
        System.out.println("║╠───────────────────────────────────────────────────────────────────────────────────────╣║");
        if (use == 0) {
            System.out.println(login);
        } else if (use == 1){
            System.out.println(invalid);
        } else if (use == 2){
            System.out.println(success1);
        } else if (use == 3){
            System.out.println(error1);
        } else if (use == 4){
            System.out.println(success2);
        } else if (use == 5){
            System.out.println(error2);
        } else if (use == 6){
            System.out.println(success3);
        } else if (use == 7){
            System.out.println(error3);
        }
        System.out.println("╚═════════════════════════════════════════════════════════════════════════════════════════╝");
        System.out.print(Colores.RESET);
    }

    /**
     * Displays the exit prompt when the user is about to leave the calendar menu.
     *
     * @param scanner Scanner object to read user input.
     */
    public static void imprimirSalirCalendarioMenu(Scanner scanner){
        String space = scanner.nextLine();
        System.out.println(Colores.RED);
        System.out.println("╔╔═══════════════════════════════════════════════════════════════════════════════════════╗╗");
        System.out.println("║║                                                                                       ║║");
        System.out.println("║║  " + Colores.YELLOW + "╭─────────────────────╮ " + Colores.RESET + " ████  ███  ██   ████ ████  ██ ████   ███  ████  ████  ███   " + Colores.RED + "║║");
        System.out.println("║║  " + Colores.YELLOW + "│─────────────────────│ " + Colores.RESET + " ██   ██ ██ ██   ██   ██ █  ██ ██ ██ ██ ██ ██ ██  ██  ██ ██  " + Colores.RED+ "║║");
        System.out.println("║║  " + Colores.YELLOW + "│ " + Colores.RESET + "□  □  □  □  □  " + Colores.RED + "■ " + Colores.GREEN + " ■ " + Colores.YELLOW + "│" + Colores.RESET + "  ██   ██ ██ ██   ██   ██ ██ ██ ██ ██ ██ ██ ██ ██  ██  ██ ██  " + Colores.RED + "║║");
        System.out.println("║║  " + Colores.YELLOW + "│ " + Colores.RESET + "□  □  □  □  □  " + Colores.CYAN + "■  " + Colores.RESET + "□ "+ Colores.YELLOW + "│" + Colores.RESET + "  ██   ██ ██ ██   ████ ██ ██ ██ ██ ██ ██ ██ █████  ██  ██ ██  " + Colores.RED + "║║");
        System.out.println("║║  " + Colores.YELLOW + "│ " + Colores.RESET + "□  " + Colores.PURPLE + "■" + Colores.RESET + "  □  " + Colores.PURPLE + Colores.BOLD + "T  A  S  K " + Colores.YELLOW + "│ " + Colores.RED + " ██   █████ ██   ██   ██ ██ ██ ██ ██ " + Colores.RESET + "█████ ████   ██  ██ ██  " + Colores.RED + "║║");
        System.out.println("║║  " + Colores.YELLOW + "│ " + Colores.RESET + "□  " + Colores.BOLD + "P  H  R  E  N  A " + Colores.YELLOW + "│ " + Colores.RED + " ██   ██ ██ ██   ██   ██  █ ██ ██ ██ ██ ██ ██ ██  ██  ██ ██  " + Colores.RED + "║║");
        System.out.println("║║  " + Colores.YELLOW + "╰─────────────────────╯ " + Colores.RED + " ████ ██ ██ ████ ████ ██  ████ ████  ██ ██ ██ ██ ████  ███   " + Colores.RED + "║║");
        System.out.println("║║                                                                                       ║║");
        System.out.println("║╠───────────────────────────────────────────────────────────────────────────────────────╣║");
        System.out.println("║╝     ╔───────────────────────────────────────────────────────────────────────────╗     ╚║");
        System.out.println("║      ║─     " + Colores.RESET + "ESTÁS A PUNTO DE SALIR DE LA APLICACIÓN!   " + Colores.BOLD + "¿DESEAS CONTINUAR?       " + Colores.RED + "─║      ║");
        System.out.println("║      ╚───────────────────────────────────────────────────────────────────────────╝      ║");
        System.out.println("║      ╔─────────────────────╗╔────────────────────────────────────────────────────╗      ║");
        System.out.println("║      ║─   " + Colores.RESET + "PULSA " + Colores.CYAN + Colores.BOLD + "ESPACIO   " + Colores.RED + "─║║- " + Colores.RESET + "              SI QUIERES SALIR                   " + Colores.RED + "─║      ║");
        System.out.println("║      ╚─────────────────────╝╚────────────────────────────────────────────────────╝      ║");
        System.out.println("║      ╔───────────────────────────────────────────────────────────────────────────╗      ║");
        System.out.println("║      ║─          " + Colores.RESET + "O INTRODUCE CUALQUIER OTRA TECLA SI QUIERES QUEDARTE           " + Colores.RED + "─║      ║");
        System.out.println("║      ╚───────────────────────────────────────────────────────────────────────────╝      ║");
        System.out.println("║                                                                                         ║");
        System.out.println("╚═════════════════════════════════════════════════════════════════════════════════════════╝");
        System.out.print(Colores.RESET);
    }
}
