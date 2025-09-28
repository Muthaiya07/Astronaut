package manager;

import model.Task;
import observer.TaskNotifier;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ScheduleManager {
    private static ScheduleManager instance;
    private final List<Task> tasks;
    private final TaskNotifier notifier;

    private ScheduleManager() {
        tasks = new ArrayList<>();
        notifier = new TaskNotifier();
    }

    public static ScheduleManager getInstance() {
        if (instance == null) {
            instance = new ScheduleManager();
        }
        return instance;
    }

    public void addObserver(observer.TaskObserver observer) {
        notifier.addObserver(observer);
    }

    public boolean addTask(Task task) {
        for (Task t : tasks) {
            if (isOverlap(t, task)) {
                notifier.notifyConflict(t, task);
                return false;
            }
        }
        tasks.add(task);
        return true;
    }

    public boolean removeTask(String description) {
        return tasks.removeIf(task -> task.getDescription().equalsIgnoreCase(description));
    }

    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks scheduled for the day.");
            return;
        }
        tasks.stream()
                .sorted(Comparator.comparing(Task::getStartTime))
                .forEach(System.out::println);
    }

    private boolean isOverlap(Task t1, Task t2) {
        return !(t2.getEndTime().compareTo(t1.getStartTime()) <= 0 ||
                 t2.getStartTime().compareTo(t1.getEndTime()) >= 0);
    }
}