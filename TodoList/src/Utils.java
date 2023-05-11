import java.io.File;

public class Utils {
    enum Priority {
        HIGH(1), MEDIUM(2), LOW(3);
        Priority(int priorityValue) {}
    }

    // Todos Storage file
    public static String getTodoListFile() {
        return "todolist.txt";
    }
}
