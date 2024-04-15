package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.MarkTaskCommand.MESSAGE_MARK_TASK_SUCCESS;
import static seedu.address.logic.commands.MarkTaskCommand.MESSAGE_TASK_DOES_NOT_EXIST;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalTasks.TASK_ALICE;
import static seedu.address.testutil.TypicalTasks.TASK_BENSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

public class MarkTaskCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new MarkTaskCommand(null, null));
    }

    @Test
    public void execute_validTaskAndIndex_success() {
        Person targetAlice = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        MarkTaskCommand markTaskCommand = new MarkTaskCommand(TASK_ALICE.getTaskTitle(), INDEX_FIRST_PERSON);

        String expectedMessage = String.format(MESSAGE_MARK_TASK_SUCCESS,
                Messages.printName(targetAlice), Messages.printTask(TASK_ALICE));

        expectedModel.markTask(TASK_ALICE);
        expectedModel.commitAddressBook();

        assertCommandSuccess(markTaskCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_taskNotFound_throwsCommandException() {
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        MarkTaskCommand markTaskCommand = new MarkTaskCommand(TASK_ALICE.getTaskTitle(), INDEX_SECOND_PERSON);
        Person taskOwner = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());

        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        assertCommandFailure(markTaskCommand, model, commandHistory,
                String.format(MESSAGE_TASK_DOES_NOT_EXIST, Messages.printName(taskOwner)));
    }

    @Test
    public void execute_indexOutOfBound_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        MarkTaskCommand markTaskCommand = new MarkTaskCommand(TASK_ALICE.getTaskTitle(), outOfBoundIndex);

        assertCommandFailure(markTaskCommand, model, commandHistory, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        String taskAliceTitle = TASK_ALICE.getTaskTitle();
        String taskBensonTitle = TASK_BENSON.getTaskTitle();
        MarkTaskCommand firstMarkCommand = new MarkTaskCommand(taskAliceTitle, INDEX_FIRST_PERSON);
        MarkTaskCommand secondMarkCommand = new MarkTaskCommand(taskBensonTitle, INDEX_FIRST_PERSON);
        MarkTaskCommand thirdMarkCommand = new MarkTaskCommand(taskAliceTitle, INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(firstMarkCommand.equals(firstMarkCommand));

        // same values -> returns true
        MarkTaskCommand firstMarkCommandCopy = new MarkTaskCommand(taskAliceTitle, INDEX_FIRST_PERSON);
        assertTrue(firstMarkCommandCopy.equals(firstMarkCommand));

        // different types -> returns false
        assertFalse(firstMarkCommand.equals(1));

        // null -> returns false
        assertFalse(firstMarkCommand.equals(null));

        // different person -> returns false
        assertFalse(firstMarkCommand.equals(secondMarkCommand));

        // different index -> returns false
        assertFalse(firstMarkCommand.equals(thirdMarkCommand));
    }
}
