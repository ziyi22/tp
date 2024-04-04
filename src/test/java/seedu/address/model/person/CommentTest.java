package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CommentTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void isValidCommand() {
        // null name
        assertThrows(NullPointerException.class, () -> Comment.isValidComment(null));

        // invalid name
        assertFalse(Comment.isValidComment("")); // empty string
        assertFalse(Comment.isValidComment(" ")); // spaces only

        // valid comment
        assertTrue(Name.isValidName("punctual")); // alphabets only
        assertTrue(Name.isValidName("12345")); // numbers only
        assertTrue(Name.isValidName("took 2 days leave")); // alphanumeric characters
        assertTrue(Name.isValidName("Good at Project Management")); // with capital letters
        assertTrue(Name.isValidName("Team leader for the building hygiene department")); // long names
    }

    @Test
    public void equals() {
        Comment comment = new Comment("Valid Comment");

        // same values -> returns true
        assertTrue(comment.equals(new Comment("Valid Comment")));

        // same object -> returns true
        assertTrue(comment.equals(comment));

        // null -> returns false
        assertFalse(comment.equals(null));

        // different values -> returns false
        assertFalse(comment.equals(new Comment("Other Valid Comment")));
    }
}
