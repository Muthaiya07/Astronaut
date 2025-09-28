package model;

public class Task {
    private String description;
    private String startTime;
    private String endTime;
    private String priority;
    private boolean completed;

    public Task(String description, String startTime, String endTime, String priority) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
        this.completed = false;
    }

    public String getDescription() { return description; }
    public String getStartTime() { return startTime; }
    public String getEndTime() { return endTime; }
    public String getPriority() { return priority; }
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    @Override
    public String toString() {
        return startTime + " - " + endTime + ": " + description + " [" + priority + "]"
                + (completed ? " (Completed)" : "");
    }
}