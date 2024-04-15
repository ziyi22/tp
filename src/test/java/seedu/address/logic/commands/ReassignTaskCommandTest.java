package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ReassignTaskCommand.MESSAGE_PERSON_IS_BUSY;
import static seedu.address.logic.commands.ReassignTaskCommand.MESSAGE_REASSIGN_TASK_SUCCESS;
import static seedu.address.logic.commands.ReassignTaskCommand.MESSAGE_TASK_DOES_NOT_EXIST;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
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

public class ReassignTaskCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ReassignTaskCommand(null, null));
    }

    @Test
    public void execute_validIndex_success() {
        ReassignTaskCommand reassignTaskCommand = new ReassignTaskCommand(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON);
        Person assignedFrom = expectedModel.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person assignedTo = expectedModel.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        String expectedMessage = String.format(MESSAGE_REASSIGN_TASK_SUCCESS,
                Messages.printTask(TASK_ALICE), Messages.printName(assignedFrom), Messages.printName(assignedTo));
        assignedTo.removeTask();
        assignedFrom.setTask(TASK_ALICE);
        expectedModel.reassignTask(TASK_ALICE, assignedFrom, assignedTo);
        expectedModel.commitAddressBook();

        assertCommandSuccess(reassignTaskCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_assignedFromHasNoTasks_throwsCommandException() {
        Person targetAlice = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        targetAlice.removeTask();
        Person targetBenson = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        targetBenson.removeTask();
        ReassignTaskCommand reassignTaskCommand = new ReassignTaskCommand(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON);

        assertCommandFailure(reassignTaskCommand, model, commandHistory,
                String.format(MESSAGE_TASK_DOES_NOT_EXIST, Messages.printName(targetAlice)));
    }

    @Test
    public void execute_assignedToIsBusy_throwsCommandException() {
        Person targetBenson = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        targetBenson.setTask(TASK_BENSON);
        ReassignTaskCommand reassignTaskCommand = new ReassignTaskCommand(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON);
        assertCommandFailure(reassignTaskCommand, model, commandHistory,
                String.format(MESSAGE_PERSON_IS_BUSY, Messages.printName(targetBenson)));
    }

    @Test
    public void execute_assignedToIndexOutOfBounds_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        ReassignTaskCommand reassignTaskCommand = new ReassignTaskCommand(INDEX_FIRST_PERSON, outOfBoundIndex);

        assertCommandFailure(reassignTaskCommand, model, commandHistory,
                Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_assignedFromIndexOutOfBounds_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        ReassignTaskCommand reassignTaskCommand = new ReassignTaskCommand(outOfBoundIndex, INDEX_SECOND_PERSON);

        assertCommandFailure(reassignTaskCommand, model, commandHistory,
                Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ReassignTaskCommand firstReassignCommand = new ReassignTaskCommand(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON);
        ReassignTaskCommand secondReassignCommand = new ReassignTaskCommand(INDEX_SECOND_PERSON, INDEX_FIRST_PERSON);
        ReassignTaskCommand thirdReassignCommand = new ReassignTaskCommand(INDEX_FIRST_PERSON, INDEX_THIRD_PERSON);

        // same object -> returns true
        assertTrue(firstReassignCommand.equals(firstReassignCommand));

        // same values -> returns true
        ReassignTaskCommand firstReassignCommandCopy = new ReassignTaskCommand(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON);
        assertTrue(firstReassignCommandCopy.equals(firstReassignCommand));

        // different types -> returns false
        assertFalse(firstReassignCommand.equals(1));

        // null -> returns false
        assertFalse(firstReassignCommand.equals(null));

        // Swapped Index -> returns false
        assertFalse(firstReassignCommand.equals(secondReassignCommand));

        // different index -> returns false
        assertFalse(firstReassignCommand.equals(thirdReassignCommand));
    }
}
