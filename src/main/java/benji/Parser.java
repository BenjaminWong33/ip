package benji;

/**
 * Parses user input and identifies the corresponding command
 */
public class Parser {
    /**
     * Converts a user command into the corresponding {@link Command}
     * @param input the command entered by the user
     * @return the {@link Command} represents by the input
     */
    public static Command getCommand(String input) { // get command function, map it to enum
        String upperInput = input.toUpperCase();
        if (upperInput.equals("BYE")) {
            return Command.BYE;
        } else if (upperInput.equals("LIST")) {
            return Command.LIST;
        } else if (upperInput.startsWith("MARK ")) {
            return Command.MARK;
        } else if (upperInput.startsWith("UNMARK ")) {
            return Command.UNMARK;
        } else if (upperInput.startsWith("TODO ")) {
            return Command.TODO;
        } else if (upperInput.startsWith("DEADLINE ")) {
            return Command.DEADLINE;
        } else if (upperInput.startsWith("EVENT ")) {
            return Command.EVENT;
        } else if (upperInput.startsWith("DELETE ")) {
            return Command.DELETE;
        } else if (upperInput.startsWith("FIND ")) {
            return Command.FIND;
        } else {
            return Command.UNKNOWN;
        }
    }
}
