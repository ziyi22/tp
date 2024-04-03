package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommentCommand.MESSAGE_ADD_COMMENT_SUCCESS;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Comment;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class CommentCommandTest {

    private static final String COMMENT_STUB = "Some comment";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_addCommentUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withComment(COMMENT_STUB).build();

        CommentCommand commentCommand = new CommentCommand(INDEX_FIRST_PERSON,
                new Comment(editedPerson.getComment().comment));

        String expectedMessage = String.format(MESSAGE_ADD_COMMENT_SUCCESS, firstPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        CommandHistory commandHistory = new CommandHistory();

        assertCommandSuccess(commentCommand, model, commandHistory, expectedMessage, expectedModel);
    }
}
