package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditDeadlineCommand;
import seedu.address.model.task.Deadline;

public class EditDeadlineCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditDeadlineCommand.MESSAGE_USAGE);

    private EditDeadlineCommandParser parser = new EditDeadlineCommandParser();

    private Deadline deadline = new Deadline("31-10-2024 1600");

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, PREFIX_DEADLINE + "31-10-2024 1600", MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5 "
                + PREFIX_DEADLINE + "31-10-2024 1600", MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0 " + PREFIX_DEADLINE
                + "31-10-2024 1600", MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_DEADLINE + "31-10-2024 1600";

        EditDeadlineCommand expectedCommand = new EditDeadlineCommand(targetIndex, deadline);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

}
