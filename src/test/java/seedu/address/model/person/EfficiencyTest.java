package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EfficiencyTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Efficiency(null));
    }

    @Test
    public void constructor_invalidEfficiency_throwsIllegalArgumentException() {
        String invalidEfficiency = "101"; // beyond valid range
        assertThrows(IllegalArgumentException.class, () -> new Efficiency(invalidEfficiency));
    }

    @Test
    public void isValidEfficiency() {
        // null efficiency
        assertThrows(NullPointerException.class, () -> Efficiency.isValidEfficiency(null));

        // invalid efficiencies
        assertFalse(Efficiency.isValidEfficiency("-1")); // below 0
        assertFalse(Efficiency.isValidEfficiency("101")); // above 100
        assertFalse(Efficiency.isValidEfficiency("100.5")); // non-integer
        assertFalse(Efficiency.isValidEfficiency("abc")); // non-numeric

        // valid efficiencies
        assertTrue(Efficiency.isValidEfficiency("0"));
        assertTrue(Efficiency.isValidEfficiency("50"));
        assertTrue(Efficiency.isValidEfficiency("100"));
    }

    @Test
    public void increase() {
        Efficiency efficiency = new Efficiency("20");

        // increase within bounds
        assertEquals(new Efficiency("25"), efficiency.increase(5));

        // increase to boundary
        assertEquals(new Efficiency("100"), efficiency.increase(80));

        // increase beyond boundary
        assertEquals(new Efficiency("100"), efficiency.increase(85));
    }

    @Test
    public void decrease() {
        Efficiency efficiency = new Efficiency("50");

        // decrease within bounds
        assertEquals(new Efficiency("40"), efficiency.decrease(5));

        // decrease to boundary
        assertEquals(new Efficiency("0"), efficiency.decrease(25));

        // decrease beyond boundary
        assertEquals(new Efficiency("0"), efficiency.decrease(30));
    }

    @Test
    public void equals() {
        Efficiency efficiency = new Efficiency("50");

        // same values -> returns true
        assertTrue(efficiency.equals(new Efficiency("50")));

        // same object -> returns true
        assertTrue(efficiency.equals(efficiency));

        // null -> returns false
        assertFalse(efficiency.equals(null));

        // different types -> returns false
        assertFalse(efficiency.equals(5.0f));

        // different values -> returns false
        assertFalse(efficiency.equals(new Efficiency("51")));
    }
}
