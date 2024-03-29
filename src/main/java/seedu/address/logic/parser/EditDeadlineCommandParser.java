package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_OWNER;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditDeadlineCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.Deadline;

/**
 * Parses input arguments and creates a new EditDeadlineTaskCommand object
 */
public class EditDeadlineCommandParser implements Parser<EditDeadlineCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditDeadlineCommand
     * and returns an EditDeadlineCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public EditDeadlineCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_TASK_OWNER, PREFIX_DEADLINE);

        if (!arePrefixesPresent(argMultimap, PREFIX_TASK_OWNER, PREFIX_DEADLINE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditDeadlineCommand.MESSAGE_USAGE));
        }
        Index personIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_TASK_OWNER).get());
        Deadline deadline = ParserTaskUtil.parseDeadline(argMultimap.getValue(PREFIX_DEADLINE).get());

        return new EditDeadlineCommand(personIndex, deadline);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
