/**
 * Class that defines ANSI color codes used in the user interface.
 * This class contains constants that represent different colors and text styles
 * for use in console output, enhancing the visibility and aesthetics of the application
 * by customizing colors.

 * The available color options include:
 * - Basic colors: red, green, yellow, blue, purple, cyan, and reset (which means the normal color of the terminal).
 * - Text styles: bold and underline.
 */
public class Colores {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String BOLD = "\u001B[1m";
    public static final String UNDERLINE = "\u001B[4m";
}
