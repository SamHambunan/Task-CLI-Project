
import java.io.IOException;
import java.util.Scanner;

public class CLI {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        args[0] = args[0].toLowerCase(); // makes sure the CLI the user has inputted is in lowercase to avoid error
        Main main = new Main(); // creates a object that will manage the Lists of class and the flow of JSON

        switch (args[0]) {
            case "add" -> { // if user command line argument add
                if (args.length > 2) {  // to check if arg length has sufficient parameters
                    System.out.println("Invalid parameters should be: CLI add <\"Task Description\">");
                } else {
                    main.addTask(args[1]); // adds the task the user has created
                }
            }
            case "list" -> {
                if (args.length > 1) { // if user inputs (CLI list todo || in-progress || done)
                    switch (args[1]) {
                        case "todo" ->
                            main.listTask("todo");
                        case "in-progress" ->
                            main.listTask("in-progress");
                        case "done" ->
                            main.listTask("done");
                        default ->
                            System.out.println("Invalid input!: arg 1 should be \"todo\" \"in-progress\" \"done\"");
                    }
                } else {
                    main.listTask();
                }
            }
            case "update" -> {
                if (args.length == 3) {
                    try {
                        int id = Integer.parseInt(args[1]); // parses the input to an integer
                        main.updateTask(id, args[2]);
                    } catch (NumberFormatException e) { // if user did not enter an integer
                        System.out.println("You need to enter a number for the ID!");
                    }
                } else {
                    System.out.println("Please follow this format");
                    System.out.println("update <taskID> \"<TaskName>\"");
                }

            }
            case "delete" -> {
                try {
                    int id = Integer.parseInt(args[1]);
                    main.deleteTask(id);
                } catch (NumberFormatException e) {
                    System.out.println("You need to enter a number for the ID!");
                }
            }
            case "mark-in-progress" -> {
                try {
                    int id = Integer.parseInt(args[1]); // parses the input to an integer
                    main.updateTask(id, "in-progress");
                } catch (NumberFormatException e) {
                    System.out.println("You need to enter a number for the ID!");
                }
            }
            case "mark-done" -> {
                try {
                    int id = Integer.parseInt(args[1]); // parses the input to an integer
                    main.updateTask(id, "done");
                } catch (NumberFormatException e) {
                    System.out.println("You need to enter a number for the ID!");
                }
            }
            default ->
                System.out.println("Invalid input do CLI --help"); // --help be done soon
        }
        scanner.close();

    }
}
