package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Name} has no active task.
 */
public class NoTaskPredicate implements Predicate<Person> {
    public NoTaskPredicate() {
    }

    @Override
    public boolean test(Person person) {
        return person.getTask() == null;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NoTaskPredicate)) {
            return false;
        }

        NoTaskPredicate otherNoTaskPredicate = (NoTaskPredicate) other;
        return this.equals(otherNoTaskPredicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("a", "a").toString();
    }
}
