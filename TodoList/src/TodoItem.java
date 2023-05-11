import java.io.Serializable;
import java.util.UUID;

public class TodoItem implements Serializable {
    private final String description;
    private final int priority;
    private final UUID uniqueId;
    private boolean completed;

    public TodoItem (String desc, int priority) {
        this.uniqueId = UUID.randomUUID(); // to store and retrieve from file.
        this.description = desc;
        this.priority = priority;
        this.completed = false;
    }

    @Override
    public String toString() {
        return description;
    }

    public int getPriority() {
        return this.priority;
    }

    // late usage; need to check.
    private void complete(){ this.completed = true; }

    public UUID getTodoId() {
        return this.uniqueId;
    }
}
