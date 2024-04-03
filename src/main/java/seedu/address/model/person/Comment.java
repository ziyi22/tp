package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

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
