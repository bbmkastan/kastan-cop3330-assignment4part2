package ucf.assignments;

import javafx.scene.control.CheckBox;

import java.time.LocalDate;

public class Item {
    private String description;
    private String dueDate;
    private CheckBox completed;

    public Item(String dueDate, String description) {
        this.description = description;
        this.dueDate = dueDate;
        this.completed = new CheckBox();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public CheckBox isCompleted() {
        return completed;
    }

    public void setCompleted(CheckBox completed) {
        this.completed = completed;
    }
}
