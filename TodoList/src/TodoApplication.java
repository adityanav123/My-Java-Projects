import com.sun.source.tree.Tree;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class TodoApplication {
    private final List<TodoItem> todoList = new ArrayList<>();
    private final List<TodoItem> completedTodo = new ArrayList<>();

    private TodoItem item;

    public void addTodoItem() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Todo [" + todoList.size() + "] = ");
        System.out.print("Description : ");
        String desc = sc.nextLine();
        System.out.print("Priority (1-3 ; High-Low) : ");
        int priority = sc.nextInt();
        if(priority >= 3) priority = 3;
        if(priority <= 1) priority = 1;

        item = new TodoItem(desc, priority);

        todoList.add(item);
    }

    public void seeTodoList() {
        todoList.sort(Comparator.comparingInt(TodoItem::getPriority));

        System.out.println("Todo List - ");
        for (int i = 0; i < todoList.size(); i++) {
            System.out.println(i + 1 + ": " + todoList.get(i));
        }
    }

    // EXCECUTES WHEN THE APPLICATION STARTS
    {
        System.out.println("Todo Application..");
        System.out.println("[Priority : High(1), Medium(2), Low(3)]");

        // fetch from storage
        fetchFromFile();
        if (todoList.size() > 0) {
            System.out.println("--fetching from file..");
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                System.err.println("err. "+ e.getLocalizedMessage());
            }
        }
    }

    public void deleteTodo() {
        // see list frist
        seeTodoList();

        System.out.print("Enter Todo Id to delete : ");
        int id = getId();
        if (id > todoList.size()) {
            System.err.println("invalid id.");
        }
        else {
            todoList.remove(id-1);
        }
    }

    public void todoComplete() {
        // first see the list
        seeTodoList();

        System.out.print("Enter todo id which is completed : ");
        int id = getId();

        if(todoList.size() < id) {
            System.err.println("invalid id.");
        }
        else {
            completedTodo.add(todoList.get(id-1));
            todoList.remove(id-1);
        }
    }

    public int getId() {
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    public void getCompletedTodos() {
        System.out.println("Todos Completed - [");
        for (TodoItem completedItem : completedTodo) {
            System.out.println(completedItem);
        }
        System.out.println("]");
    }


    // FILE STORAGE
    public void storeToFile () {
        String fileName = Utils.getTodoListFile();

        try (
                FileOutputStream fos = new FileOutputStream(fileName);
                ObjectOutputStream oos = new ObjectOutputStream(fos)
        ) {
            oos.writeInt(todoList.size());
            todoList.forEach( todoitem ->
                    {
                        try {
                            oos.writeObject(todoitem);
                        } catch (IOException exception) {
                            System.err.println("err. : " + exception.getLocalizedMessage());
                        }
                    }
            );
        } catch (Exception exception) {
            System.err.println("err. : " + exception.getLocalizedMessage());
        }
    }

    public void fetchFromFile() {
        String filename = Utils.getTodoListFile();
        try (
                FileInputStream fis = new FileInputStream(filename);
                ObjectInputStream ois = new ObjectInputStream(fis)
        ) {
            if (ois.available() == 0) {
                System.out.println("file empty.");
                return;
            }
            int todoListSize = ois.readInt();
            for (int i = 0; i < todoListSize; i++) {
                item = (TodoItem) ois.readObject();
                // store to list
                boolean found = false;
                for (TodoItem todoItem : todoList) {
                    if(todoItem.getTodoId().equals(item.getTodoId())) {
                        found = true;
                        break;
                    }
                }
                if (!found) todoList.add(item);

            }
        } catch (Exception ignored) {}
    }
}
