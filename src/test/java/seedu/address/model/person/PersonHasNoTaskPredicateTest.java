package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

class PersonHasNoTaskPredicateTest {

    @Test
    public void equals() {
        PersonHasNoTaskPredicate firstPredicate = new PersonHasNoTaskPredicate();
        PersonHasNoTaskPredicate secondPredicate = new PersonHasNoTaskPredicate();

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // same class -> returns true, since there's no state to differ
        assertTrue(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_personIsNotBusy_returnsTrue() {
        // Person is not busy
        PersonHasNoTaskPredicate predicate = new PersonHasNoTaskPredicate();
        assertTrue(predicate.test(new PersonBuilder().build()));
    }

    @Test
    public void toStringMethod() {
        PersonHasNoTaskPredicate predicate = new PersonHasNoTaskPredicate();

        String expected = "PersonHasNoTaskPredicate";
        assertTrue(predicate.toString().contains(expected));
    }
}
