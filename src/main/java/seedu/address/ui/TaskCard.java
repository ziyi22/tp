package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.task.Task;

/**
 * An UI component that displays information of a {@code Task}.
 */
public class TaskCard extends UiPart<Region> {

    private static final String FXML = "TaskListCard.fxml";

    private static final String DONE_STRING = "Done";

    private static final String NOT_DONE_STRING = "Not Done";

    public final Task task;

    @FXML
    private Label id;

    @FXML
    private Label deadline;

    @FXML
    private Label taskName;

    @FXML
    private Label status;

    @FXML
    private Label inCharge;

    /**
     * Creates a {@code TaskCard} with the given {@code Task}, index and
     * other relevant information to display.
     */
    public TaskCard(Task task, int displayedIndex) {
        super(FXML);
        this.task = task;
        id.setText(displayedIndex + ". ");
        taskName.setText(task.getTaskTitle());
        deadline.setText(getDeadline(task));
        status.setText(getStatus(task));
        inCharge.setText(getPersonInCharge(task));

    }

    /**
     * Get name of person in charge.
     */
    private String getPersonInCharge(Task task) {
        return "In Charge: " + task.getPersonInCharge().getName().fullName;
    }

    /**
     * Get deadline of Task.
     */
    private String getDeadline(Task task) {
        return "Deadline: " + task.getDeadline().toString();
    }

    /**
     * Get the current status of Task.
     */
    private String getStatus(Task task) {
        String output = "";
        if (task.isDone()) {
            output += DONE_STRING;
            status.setStyle("-fx-text-fill: green;");
        } else {
            output += NOT_DONE_STRING;
            status.setStyle("-fx-text-fill: red;");
        }

        return output;
    }

}
