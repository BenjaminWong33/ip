import java.io.IOException;
import java.nio.file.Files; // provides an efficient way to manipulate files and directories
import java.nio.file.Path; // to locate manipulate and work with file and directory paths
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    // Look for benji.txt inside the data folder.
    // Path: A Java object that represents a file or folder location on your computer.
    // Paths.get(...): A tool that joins folder names and file names together correctly.
    private static final Path FILE_PATH = Paths.get("data", "benji.txt");

    public static void saveTasks(ArrayList<Task> tasks) {
        try {
            // FILE_PATH: The full path to your target file (like C:/data/reports/summary.txt).
            // .getParent(): Grabs just the folder path where the file lives (like C:/data/reports).
            // Files.createDirectories(...): Builds those folders on your computer.
            // If any middle folder is missing, it creates all of them at once. If the folders are already there, it does nothing and does not crash
            Files.createDirectories(FILE_PATH.getParent());
            ArrayList<String> lines = new ArrayList<>();
            for (Task task : tasks) {
                if (task instanceof Todo) {
                    lines.add("T | " + (task.isDone ? "1" : "0") + " | " + task.description);
                } else if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    lines.add("D | " + (task.isDone ? "1" : "0") + " | " + task.description
                                                                 + " | " + deadline.by);
                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    lines.add("E | " + (task.isDone ? "1" : "0") + " | " + task.description
                                                                 + " | " + event.start
                                                                 + " | " + event.end);
                }
            }
            Files.write(FILE_PATH, lines); // write and overwrite content in a specific file

        } catch (IOException e) {
            System.out.println("Sorry, I couldn't save your tasks");
        }
    }

    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        if (!Files.exists(FILE_PATH)) {
            return tasks;
        }
        try {
            List<String> lines = Files.readAllLines(FILE_PATH);
            for (String line : lines) {
                //  cuts a text line into smaller pieces wherever it finds a vertical bar (|) character
                String[] parts = line.split(" \\|");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                Task task = null;
                if (type.equals("T")) {
                    task = new Todo(parts[2]);
                } else if (type.equals("D")) {
                    task = new Deadline(parts[2], parts[3]);
                } else if (type.equals("E")) {
                    task = new Event(parts[2], parts[3], parts[4]);
                }

                if (task != null) {
                    if (isDone) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                }
            }
        } catch (IOException e ) {
            System.out.println("Sorry, i couldn't load your tasks");
        }
        return tasks;
    }
}
