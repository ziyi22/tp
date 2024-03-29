package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ReassignTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parse input arguments and create a new ReassignTaskCommand object.
 */
public class ReassignTaskCommandParser implements Parser<ReassignTaskCommand> {

    @Override
    public ReassignTaskCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_FROM, PREFIX_TO);

        if (!arePrefixesPresent(argMultimap, PREFIX_FROM, PREFIX_TO)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReassignTaskCommand.MESSAGE_USAGE));
        }
        Index fromIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_FROM).get());
        Index toIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_TO).get());

        return new ReassignTaskCommand(fromIndex, toIndex);

    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
