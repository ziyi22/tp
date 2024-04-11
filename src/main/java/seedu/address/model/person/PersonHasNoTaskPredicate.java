package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Name} has no active task.
 */
public class PersonHasNoTaskPredicate implements Predicate<Person> {
    public PersonHasNoTaskPredicate() {
    }

    @Override
    public boolean test(Person person) {
        return !person.isBusy();
    }

    @Override
    public boolean equals(Object other) {
        // Check for identity
        if (other == this) {
            return true;
        }

        // Check for correct type
        return other instanceof PersonHasNoTaskPredicate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("a", "a").toString();
    }
}
