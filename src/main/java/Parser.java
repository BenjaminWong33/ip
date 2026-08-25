public class Parser {
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
        } else if (upperInput.startsWith("TODO")) {
            return Command.TODO;
        } else if (upperInput.startsWith("DEADLINE")) {
            return Command.DEADLINE;
        } else if (upperInput.startsWith("EVENT")) {
            return Command.EVENT;
        } else if (upperInput.startsWith("DELETE ")) {
            return Command.DELETE;
        } else {
            return Command.UNKNOWN;
        }
    }
}
