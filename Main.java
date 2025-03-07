
import java.util.ArrayList;

public class Main {

    ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(String description) {
        tasks.add(new Task(description));

    }

    public void updateTask(int index, String description) {
        tasks.add(new Task("Old"));
        Task ta = new Task(); // creates an empty Task object to get a space to overwrite

        if (index > tasks.size()) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " out of " + tasks.size() + " out of bounds");
        }
        ta = ta.overWrite(description, tasks.get(index - 1));
        for (Task t : tasks) {
            if (t.id == index) {
                tasks.set(index - 1, ta);
            }
        }
        listTask();

    }

    public void listTask() {

        for (Task t : tasks) {
            t.displayDetails();
        }
    }

    public void listTask(String status) {

        tasks.add(new Task("test"));
        tasks.add(new Task("test1"));
        tasks.add(new Task("test2"));
        for (Task t : tasks) {
            if (t.status.equals(status)) {
                t.displayDetails();
            }
        }
    }

}
