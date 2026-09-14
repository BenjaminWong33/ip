package benji;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void deadlineToString_formatsDateCorrectly() {
        Deadline task = new Deadline("submit report", LocalDate.of(2026, 9, 14));

        assertEquals("[D][ ] submit report (by: Sept 14 2026)", task.toString());
    }
}
