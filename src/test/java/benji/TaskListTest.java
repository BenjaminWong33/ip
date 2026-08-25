package benji;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TaskListTest {
    @Test
    public void addTask_taskAppearsInList() {
        TaskList tasks = new TaskList();
        Todo task = new Todo("Testing");
        tasks.add(task);
        assertEquals(1, tasks.size());
        assertEquals(task, tasks.get(0));
    }
}
