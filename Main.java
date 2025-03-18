
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Main {

    ArrayList<Task> tasks = new ArrayList<>();
    FileWriter file = null;

    public void addTask(String description) throws IOException {
        String after;
        ArrayList<String> lines = new ArrayList<>();
        try {

            File fileread = new File("data.json");
            BufferedReader buffer = new BufferedReader(new FileReader(fileread.getAbsolutePath()));
            file = new FileWriter("data.json", true);

            do {
                after = buffer.readLine();
                try {
                    if (after.charAt(after.length() - 1) != ',' && after.length() > 1) {
                        after += ",\n";
                    } else {
                        after += "\n";
                    }
                } catch (NullPointerException e) {
                    break;
                }
                lines.add(after);

            } while (true);
            System.out.println("lines size: " + lines.size());
            lines.set(lines.size() - 2, (lines.get(lines.size() - 2).replace("]", "")));
            int ID = getInt("ID", lines.get(lines.size() - 2));
            ID++;
            tasks.add(new Task(description, ID));

            lines.set(lines.size() - 1, tasks.get(tasks.size() - 1).toString());
            buffer.close();
            FileWriter copyWriter = new FileWriter(fileread.getAbsolutePath(), false);
            for (String i : lines) {

                copyWriter.write(i);

            }

            copyWriter.close();

        } catch (FileNotFoundException e) { // create new file
            System.out.println("File does not exist!");
            file = new FileWriter("data.json");
            file.write("[\n");
            tasks.add(new Task(description));
            file.write(tasks.get(tasks.size() - 1).toString());
        }

        file.close();
    }

    public void updateTask(int index, String description) throws IOException {
        ArrayList<String> lines = new ArrayList<>();
        FileReader file = null;
        BufferedReader buffer = null;
        boolean found = false;

        try {
            file = new FileReader("data.json");
            buffer = new BufferedReader(file);
            while (true) {
                try {
                    lines.add(buffer.readLine());
                    if (lines.get(lines.size() - 1).equals("[") || lines.get(lines.size() - 1).equals("]")) {
                        lines.remove(lines.size() - 1);
                    } else {
                        if (index - 1 == lines.size() - 1) {
                            found = true;
                            LocalDateTime time = LocalDateTime.now();
                            DateTimeFormatter format = DateTimeFormatter.ofPattern("MMMM-dd-yyyy eeee HH:mm:ss");
                            String updatedAt = time.format(format);
                            String jsonString = lines.get(lines.size() - 1);
                            String currentDescription = getString("Description", lines.get(lines.size() - 1));
                            String currentUpdatedAt = getString("updatedAt", lines.get(lines.size() - 1));
                            if (description.equals("in-progress") || description.equals("done")) {
                                currentDescription = getString("status", lines.get(lines.size() - 1));
                            }

                            jsonString = jsonString.replace("\"" + currentDescription + "\"", "\"" + description + "\"");
                            jsonString = jsonString.replace("\"updatedAt\": \"" + currentUpdatedAt + "\"", "\"updatedAt\": \"" + updatedAt + "\"");
                            lines.set(lines.size() - 1, jsonString);
                        }
                    }

                } catch (NullPointerException f) {

                    buffer.close();
                    lines.remove(lines.size() - 1);
                    break;
                } catch (IOException e) {
                    System.out.println("Something went wrong");
                }

            }
            if (!found) {
                System.out.println("Task ID does not exist!");
                System.exit(0);
            }
            FileWriter writer = new FileWriter("data.json", false);
            writer.write("[\n");
            for (String i : lines) {

                writer.write(i + "\n");
            }
            writer.write("]\n");
            writer.close();

        } catch (FileNotFoundException f) {
            System.out.println("You did not create any tasks yet!");
        } catch (IOException e) {
            System.out.println("Something went wrong!");
        }

    }

    public void listTask() throws IOException {
        int ID;
        String description;
        String status;
        String createdAt;
        String updatedAt;
        ArrayList<String> lines = new ArrayList<>();
        try {
            FileReader file = new FileReader("data.json");
            BufferedReader reader = new BufferedReader(file);
            System.out.println("#   Description                 Status   Created At                                  Updated At");
            while (true) {
                try {
                    lines.add(reader.readLine());
                    if (lines.get(lines.size() - 1).equals("[") || lines.get(lines.size() - 1).equals("]")) {
                        lines.remove(lines.size() - 1);
                    } else {
                        ID = getInt("ID", lines.get(lines.size() - 1));
                        description = getString("Description", lines.get(lines.size() - 1));
                        status = getString("status", lines.get(lines.size() - 1));
                        createdAt = getString("createdAt", lines.get(lines.size() - 1));
                        updatedAt = getString("updatedAt", lines.get(lines.size() - 1));
                        System.out.printf("%-4d%-29s%-8s%-44s", ID, description, status, createdAt);
                        if (updatedAt.isBlank()) {
                            System.out.print("Not updated yet");
                        } else {
                            System.out.printf("%s", updatedAt);
                        }
                        System.out.println();
                    }

                } catch (NullPointerException e) {
                    reader.close();
                    break;
                }

            }

        } catch (FileNotFoundException e) {
            System.out.println("No tasks yet!");
        }
    }

    public void deleteTask(int id) throws IOException {
        try {
            FileReader filepath = new FileReader("data.json");
            BufferedReader buffer = new BufferedReader(filepath);
            ArrayList<String> lines = new ArrayList<>();
            boolean removed = false;
            int copyCounter = 0;

            while (true) {
                try {
                    lines.add(buffer.readLine());
                    if (lines.get(lines.size() - 1).equals("]") || lines.get(lines.size() - 1).equals("[")) {
                        lines.remove(lines.size() - 1);
                        continue;
                    }
                    copyCounter++;
                    if (copyCounter == id) {
                        removed = true;
                        lines.remove(id - 1);

                    }

                } catch (NullPointerException e) {
                    buffer.close();
                    lines.remove(lines.size() - 1); // remove the null String in the lines Arraylist
                    break;

                }
            }
            if (!removed) {
                System.out.println("Task ID does not exist!");
                System.exit(0);
            }
            for (int i = id - 1; i < lines.size(); i++) {
                String edit = lines.get(i);

                edit = edit.replace("\"ID\": " + (i + 2), "\"ID\": " + (id));
                lines.set(i, edit);
                id++;
            }

            FileWriter writer = new FileWriter("data.json", false);
            writer.write("[\n");
            int counter = 1;
            for (String i : lines) {
                if (counter == lines.size()) {
                    i = i.replace("},", "}");
                }

                writer.write(i + "\n");
                counter++;
            }
            writer.write("]");
            writer.close();

        } catch (FileNotFoundException e) {
            System.out.println("You did not create any tasks yet!");
        }
    }

    public void listTask(String status) throws IOException {
        int ID;
        String description;
        String currentStatus;
        String createdAt;
        String updatedAt;
        boolean found = false;

        ArrayList<String> lines = new ArrayList<>();
        try {
            FileReader file = new FileReader("data.json");
            BufferedReader buffer = new BufferedReader(file);
            while (true) {
                try {
                    lines.add(buffer.readLine());
                    if (lines.get(lines.size() - 1).equals("[") || lines.get(lines.size() - 1).equals("]")) {
                        lines.remove(lines.size() - 1); // removes "["  or "]"
                    } else {
                        String current = lines.get(lines.size() - 1);
                        ID = getInt("ID", current);
                        description = getString("Description", current);
                        currentStatus = getString("status", current);
                        createdAt = getString("createdAt", current);
                        updatedAt = getString("updatedAt", current);
                        if (currentStatus.equals(status)) {
                            found = true;
                            System.out.printf("%-4d%-29s%-8s%-44s", ID, description, status, createdAt);
                            if (updatedAt.isBlank()) {
                                System.out.print("Not updated yet");
                            } else {
                                System.out.printf("%s", updatedAt);
                            }
                            System.out.println();
                        }

                    }

                } catch (NullPointerException e) {
                    buffer.close();
                    break;
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("You did not create any task yet!");
        }
        if (!found) {
            System.out.println("There are no " + status + " task/tasks");
        }

    }

    public int getInt(String key, String jsonString) {
        int ID = 0;
        if (!jsonString.contains("\"" + key + "\"")) {
            System.out.println("Key not found!");
            return ID;
        }
        int index = jsonString.indexOf("\"" + key + "\"");
        String stringID = jsonString.substring(index);
        index = stringID.indexOf(":");
        int lastIndex = stringID.indexOf(",");

        stringID = stringID.substring(index + 1, lastIndex).trim();

        try {
            ID = Integer.parseInt(stringID);
        } catch (NumberFormatException e) {
            System.out.println("This is not an integer!");
            return 0;
        }

        return ID;
    }

    public String getString(String key, String jsonString) {
        int index;
        if (jsonString.contains("\"" + key + "\"")) {
            index = jsonString.indexOf("\"" + key + "\""); // moves index to ->"key" points to the first double quotation 
            String substring = jsonString.substring(index); // returns "key": "data"
            index = substring.indexOf(":");
            substring = substring.substring(index + 1); // returns ": \"data\" "
            index = substring.indexOf("\"");
            substring = substring.substring(index + 1); // returns " data\" "
            int lastIndex = substring.indexOf("\""); // returns the index of key"<-
            substring = substring.substring(0, lastIndex).trim();

            return substring;

        } else {
            System.out.println("INVALID KEY!");
            return null;
        }

    }

}
