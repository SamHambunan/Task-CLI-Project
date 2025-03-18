# Task Cli Tracker

A project that saves user Inputted tasks and saves it using String class manually in java.

- ## Note: Please [download](https://git-scm.com/) git at this website to clone the file into your pc

# Step by step cloning code into device

1. Run this on Windows powershell.
   ```git
   git clone https://github.com/SamHambunan/Task-CLI-Project
   ```
2. Change `directory`
   ```git
   cd Task-CLI-Project
   ```
3. Compile the project
   ```java
   javac Main.java CLI.java Task.java
   ```

# Commands Guide

### There are various commands for this CLI Tracker

- **add (String task)**: Adds a `task` and inserts it to the last task `index`.
- **list**: Gives the list of `task` (Will check if file is not created).
- **list (String status)**: Gives the list of `task` according to different kinds of status:
  - **"todo"**: Tasks that are newly created will be marked as todo.
  - **"in-progress"**: Tasks that are marked in-progress.
  - **"done"**: Tasks that are marked done.
- **"update (int ID, String status)"**: Updates the Task of the given ID and will give an `updatedAt` string.

**NOTE: IF ID DOES NOT EXIST THEN IT WILL THROW AN ERROR**

- **"delete (int ID)"**: deletes a Task if `ID` exists.
- **"mark: (int ID)"**:
  - todo: marks given ID as `todo`
  - in-progress: marks given ID as `in-progress`
  - done: marks given ID as `done`

# Example of using CLI in powershell

```java
 java CLI add "Task Name"
 //adds a new task

 java CLI delete 1
 // deletes a task based on its ID

 java CLI update 1 "Edited Task Name"
 // edits the Task of ID 1 and changing it to given task

 java CLI list
 // displays list of task

 java CLI list todo
 java CLI list in-progress
 java CLI list done
//displays list of task based on its status

java CLI 1 mark-in-progress
java CLI 2 mark-done
// marks the status of ID to "In-progress" or "done"
```

# Acknowledgement

![alt text](https://encrypted-tbn0.gstatic.com/licensed-image?q=tbn:ANd9GcRNG5az_vgnLGH4QtfTD-5entqo3kpLrkhqgaBLs1iaCnPLTub-Ui_R0EQKQKKg0MgIGvHO4nHgpFUBM80)

## LEBRON JAMESSSS!!!
