import factory.TaskFactory;
import manager.ScheduleManager;
import model.Task;
import observer.TaskObserver;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ScheduleManager manager = ScheduleManager.getInstance();

        manager.addObserver((existingTask, newTask) -> 
            System.out.println("Error: Task conflicts with existing task \"" + existingTask.getDescription() + "\".")
        );

        boolean running = true;
        while (running) {
            System.out.println("\n1. Add Task\n2. Remove Task\n3. View Tasks\n4. Exit");
            System.out.print("Choose an option: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter description: ");
                    String desc = sc.nextLine();
                    System.out.print("Enter start time (HH:MM): ");
                    String start = sc.nextLine();
                    System.out.print("Enter end time (HH:MM): ");
                    String end = sc.nextLine();
                    System.out.print("Enter priority (High/Medium/Low): ");
                    String priority = sc.nextLine();

                    Task task = TaskFactory.createTask(desc, start, end, priority);
                    if (manager.addTask(task)) {
                        System.out.println("Task added successfully. No conflicts.");
                    }
                    break;
                case "2":
                    System.out.print("Enter task description to remove: ");
                    String removeDesc = sc.nextLine();
                    if (manager.removeTask(removeDesc)) {
                        System.out.println("Task removed successfully.");
                    } else {
                        System.out.println("Error: Task not found.");
                    }
                    break;
                case "3":
                    manager.viewTasks();
                    break;
                case "4":
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}