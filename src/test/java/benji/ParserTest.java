package benji;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ParserTest {
    @Test
    public void parseToDoCommand_returnsToDoCommand() {
        Command command = Parser.getCommand("todo testing 12344");
        assertEquals(Command.TODO, command);
    }

    @Test
    public void parseUnKnownCommand_returnsUnknownCommand() {
        Command command = Parser.getCommand("need to book testing date");
        assertEquals(Command.UNKNOWN, command);
    }

}
