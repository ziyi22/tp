package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindTaskCommand;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.TaskContainsKeywordsPredicate;

public class FindTaskCommandParserTest {
    private FindTaskCommandParser parser = new FindTaskCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTaskCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindTaskCommand expectedFindCommand =
                new FindTaskCommand(new TaskContainsKeywordsPredicate(Arrays.asList("fizz", "buzz")));
        assertParseSuccess(parser, "fizz buzz", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n fizz \n \t buzz  \t", expectedFindCommand);
    }
}
