import java.util.Scanner;

public class ApplicationWrapper {

    public void run() {
        TodoApplication todo = new TodoApplication();

        while(true) {
            System.out.println("--------------------------------------------------------\n");
            System.out.println("""
                       1. Add Todo
                       2. Show Todo List
                       3. Delete Todo
                       4. Todo Complete
                       5. Show Completed Todo(s)
                       6. Save All Todo(s) to File
                       7. Fetch Todo(s) from File (default : clean slate)
                       99. Quit
            """);
            System.out.print("[Choice]: ");
            int choice = new Scanner(System.in).nextInt();

            switch (choice) {
                case 1 -> todo.addTodoItem();
                case 2 -> todo.seeTodoList();
                case 3 -> todo.deleteTodo();
                case 4 -> todo.todoComplete();
                case 5 -> todo.getCompletedTodos();
                case 6 -> todo.storeToFile();
                case 7 -> todo.fetchFromFile();
                case 99 -> System.exit(0);
                default -> System.out.println("invalid option.");
            }
        }
    }
}
