package seedu.address.model.task;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Task's deadline in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDeadline(String)}
 */
public class Deadline {

    public static final String MESSAGE_CONSTRAINTS = "Deadline should be in the format: dd-MM-yyyy HHmm";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
    private LocalDateTime dateTime;

    /**
     * Constructs a {@code Deadline} using Local Date Time
     * @param dateTime A valid deadline.
     */
    public Deadline(LocalDateTime dateTime) {
        requireNonNull(dateTime);
        this.dateTime = dateTime;
    }

    /**
     * Constructs a {@code Deadline} using Local Date Time
     * @param deadline A valid deadline.
     */
    public Deadline(String deadline) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        this.dateTime = LocalDateTime.parse(deadline, f);
    }



    /**
     * Returns true if the deadline is in the correct format.
     */
    public static boolean isValidDeadline(String deadline) {
        try {
            LocalDateTime.parse(deadline, FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }
    @Override
    public String toString() {
        return dateTime.format(FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Deadline)) {
            return false;
        }

        Deadline otherName = (Deadline) other;
        return dateTime.equals(otherName.dateTime);
    }
}
