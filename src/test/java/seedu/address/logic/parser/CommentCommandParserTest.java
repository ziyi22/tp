package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Comment;

public class CommentCommandParserTest {

    private final CommentCommandParser parser = new CommentCommandParser();

    @Test
    public void parse_validArgs_returnsCommentCommand() throws ParseException {
        String args = "1 " + CliSyntax.PREFIX_COMMENT + "This is a test comment";
        CommentCommand expectedCommand = new CommentCommand(Index.fromOneBased(1),
                new Comment("This is a test comment"));
        assertEquals(expectedCommand, parser.parse(args));
    }

    @Test
    public void parse_emptyComment_returnsCommentCommandWithEmptyComment() throws ParseException {
        String args = "1 " + CliSyntax.PREFIX_COMMENT;
        CommentCommand expectedCommand = new CommentCommand(Index.fromOneBased(1), new Comment(""));
        assertEquals(expectedCommand, parser.parse(args));
    }
}
