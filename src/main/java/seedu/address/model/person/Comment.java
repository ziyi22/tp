package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.person.Name.VALIDATION_REGEX;

/**
 * Represents a Person's comment in the address book.
 */
public class Comment {


    public final String comment;

    /**
     * Constructs an {@code comment}.
     *
     * @param comment A valid comment.
     */
    public Comment(String comment) {
        requireNonNull(comment);
        this.comment = comment;
    }

    /**
     * Returns true if a given string is a valid comment.
     */
    public static boolean isValidComment(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.comment;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Comment)) {
            return false;
        }

        Comment otherComment = (Comment) other;
        return comment.equals(otherComment.comment);
    }

    @Override
    public int hashCode() {
        return this.comment.hashCode();
    }

}
