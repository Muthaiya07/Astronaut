package observer;

import model.Task;

public interface TaskObserver {
    void onTaskConflict(Task existingTask, Task newTask);
}