
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {

    static int idTracker;
    static String formatrn = "MMMM-dd-yyyy eeee HH:mm:ss";
    int id;
    String description;
    String status;
    String createdAt;
    String updatedAt;

    Task(String description, String status) {
        status = status.toLowerCase();
        System.out.println("status is: " + status);
        if (!status.equalsIgnoreCase("todo")) {
            if (!status.equalsIgnoreCase("in-progress")) {
                if (!status.equalsIgnoreCase("done")) {
                    throw new IllegalArgumentException("Invalid status type only: \"todo\" \"in-progress\" \"done\"");

                }
                throw new IllegalArgumentException("Invalid status type only: \"todo\" \"in-progress\" \"done\"");

            }
            throw new IllegalArgumentException("Invalid status type only: \"todo\" \"in-progress\" \"done\"");

        }

        idTracker++;
        this.id = idTracker;
        this.description = description;
        this.status = status;
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern(formatrn);
        createdAt = time.format(format);
        updatedAt = "";
        System.out.println("Task Created");

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
}
