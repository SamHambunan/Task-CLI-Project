
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Task {

    static int idTracker;
    static String formatrn = "MMMM-dd-yyyy eeee HH:mm:ss";
    int id;
    String description;
    String status;
    String createdAt;
    String updatedAt;

    Task(String description) {

        idTracker++; // tracks the ID
        this.id = idTracker;
        this.description = description;
        this.status = "todo";
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern(formatrn);
        createdAt = time.format(format);
        updatedAt = "";
        System.out.println("Task Created");

    }

    Task() { //creates an empty task object for updating

    }

    public void displayDetails() {
        System.out.println("ID: " + this.id);
        System.out.println("Description: " + this.description);
        System.out.println("Status: " + this.status);
        System.out.println("Date Created: " + this.createdAt);
        if (updatedAt.isEmpty()) {
            System.out.println("Did not update yet");
        } else {
            System.out.println("Updated at: " + this.updatedAt);
        }

    }

    public Task overWrite(String description, Task t) { // simply overWrites description and returns the empty newly created task
        Task empty = new Task();
        empty.id = t.id;
        empty.description = description;
        empty.status = t.status;
        empty.createdAt = t.createdAt;
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern(formatrn);
        empty.updatedAt = time.format(format);
        System.out.println("Task Overwritten");

        return empty;
    }

}
