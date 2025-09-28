package observer;

import model.Task;
import java.util.ArrayList;
import java.util.List;

public class TaskNotifier {
    private final List<TaskObserver> observers = new ArrayList<>();

    public void addObserver(TaskObserver observer) { observers.add(observer); }
    public void removeObserver(TaskObserver observer) { observers.remove(observer); }

    public void notifyConflict(Task existingTask, Task newTask) {
        for (TaskObserver observer : observers) {
            observer.onTaskConflict(existingTask, newTask);
        }
    }
}