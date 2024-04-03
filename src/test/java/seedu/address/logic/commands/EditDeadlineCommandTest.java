package seedu.address.logic.commands;

//import static org.junit.jupiter.api.Assertions.assertEquals;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

//import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
//import seedu.address.logic.Messages;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
//import seedu.address.model.person.Person;
import seedu.address.model.person.Person;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Task;
//import seedu.address.testutil.PersonBuilder;
//import seedu.address.testutil.TaskBuilder;

class EditDeadlineCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_validInput_success() throws CommandException {

        Deadline newDeadline = new Deadline("31-12-2024 2359");
        Person person = model.getFilteredPersonList().get(0);
        EditDeadlineCommand editDeadlineCommand = new EditDeadlineCommand(INDEX_FIRST_PERSON, newDeadline);

        String expectedMessage = String.format(EditDeadlineCommand.MESSAGE_MARK_TASK_SUCCESS,
                Messages.printName(person), newDeadline.toString());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Task task = new Task("Complete Project Proposal", newDeadline);
        expectedModel.setTask(model.getFilteredPersonList().get(0).getTask(), task);
        expectedModel.commitAddressBook();

        assertCommandSuccess(editDeadlineCommand, model, commandHistory, expectedMessage, expectedModel);

    }
}
