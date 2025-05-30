/**
 * Main class of the TASKPHRENA© application.
 *
 * TASKPHRENA© is a task management application with calendar integration.
 * This class contains only the main entry menu of the application, offering account
 * creation, login, and program exit options.
 *
 * - The menu is designed using ASCII art and ANSI color codes.
 * - The user input is validated using a switch statement and methods from other classes.
 * - This is also where the program can be terminated via the terminal.
 *
 * Requires the following classes:
 * - {@link Usuario}        |       (Handles user-related functionality)
 * - {@link Calendario}     |       (Handles the calendar and internal menu system)
 * - {@link Colores}        |       (Defines ANSI color constants used in the UI)
 *
 * @author tomas
 * @author arturo
 * @version 2.0
 * @since 2025
 */

import java.util.Scanner;

public class Main {
    /**
     * Main class: Launches the initial menu of the TASKPHRENA© application.
     * It controls the initial flow and navigates the user based on their input:
     *
     * There are 3 available options:
     * - Option 1: INICIAR SESIÓN / LOG IN (redirects to the calendar menu upon successful login)
     * - Option 2: REGISTRAR CUENTA / REGISTER ACCOUNT (registers a new user)
     * - Option 3: SALIR / EXIT (the safe way to close the application)
     *
     * The class also displays personalized success or error messages based on user actions.
     *
     * @param args command-line arguments (not used)
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int use = 0;

        // Strings that change based on user interaction
        String normal ="                    " + Colores.PURPLE + Colores.BOLD + "TASK" + Colores.RESET + "PHRENA© 2025 - " + Colores.RED + "Todos" + Colores.RESET + " los derechos reservados                   ";
        String invalid ="                    " + Colores.RED + "OPCIÓN INVALIDA! | USA UNA DE LAS TRES OPCIONES" + Colores.RESET + "                    ";
        String error1 ="                " + Colores.RED + "OPCIÓN INVALIDA! | INTENTO DE INICIO DE SESIÓN FALLIDO!" + Colores.RESET + "                ";
        String error2 ="                  " + Colores.RED + "   OPCIÓN INVALIDA! | INTENTO DE REGISTRO FALLIDO!" + Colores.RESET + "                   ";
        String success1 ="                  " + Colores.GREEN + "   REGISTRO FINALIZADO! | POR FAVOR, INICIE SESIÓN" + Colores.RESET + "                   ";

        // Main loop for the Menu
        while (true) {
            // Print the first menu (changes based on user choices)
            Visual.imprimirPrimerMenu(use, normal, error1, error2, success1, invalid);

            // Read user input
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":   // Login
                    System.out.println();
                    System.out.println();
                    Usuario usuarioLogin = Usuario.iniciarSesion(scanner);
                    if (usuarioLogin != null) {  // Successful login → open main menu
                        Calendario calendario = new Calendario();
                        boolean volvioCorrectamente = calendario.MenuCalendario(scanner, usuarioLogin);
                        if (volvioCorrectamente) {
                            use = 0;
                        }
                    } else {
                       use = 1;  // the user cannot log in → show  error message
                    }
                    break;

                case "2":   // Register account
                    if (Usuario.registrar(scanner)) {
                        use = 3;   // Successful registration → show good message
                    } else {
                        use = 2;   // Registration failed → show error message
                    }
                    break;

                case "3": // Exit the app
                    Visual.imprimirSalirPrimerMenu();
                    return;

                default:   // user *somehow* manages to write other option → show invalid option message
                    use = 2;
            }
        }
    }
}