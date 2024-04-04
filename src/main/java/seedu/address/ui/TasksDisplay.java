package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.model.task.Task;

/**
 * List containing all tasks.
 */
public class TasksDisplay extends UiPart<Region> {

    private static final String FXML = "TasksDisplay.fxml";

    @FXML
    private ListView<Task> taskListView;


    /**
     * Creates a {@code TasksDisplay} with the given {@code ObservableList}.
     */
    public TasksDisplay(ObservableList<Task> taskList) {
        super(FXML);
        taskListView.setItems(taskList);
        taskListView.setCellFactory(listview -> new TaskListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Task} using a {@code TaskCard}.
     */
    class TaskListViewCell extends ListCell<Task> {
        @Override
        protected void updateItem(Task task, boolean isEmpty) {
            super.updateItem(task, isEmpty);

            if (isEmpty || task == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TaskCard(task, getIndex() + 1).getRoot());
            }
        }
    }
}
