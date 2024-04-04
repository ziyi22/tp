package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Department department;
    private final Set<Tag> tags = new HashSet<>();
    private transient Task task = null;
    private Efficiency efficiency;
    private final Comment comment;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Department department, Set<Tag> tags,
                  Efficiency efficiency, Comment comment) {

        requireAllNonNull(name, phone, email, address, department, tags, comment);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.department = department;
        this.tags.addAll(tags);
        this.efficiency = efficiency;
        this.comment = comment;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Department getDepartment() {
        return department;
    }
    public Efficiency getEfficiency() {
        return efficiency;
    }

    public Comment getComment() {
        return comment;
    }

    public Task getTask() {
        return task;
    }

    /**
     * Sets a {@code Task} to person
     * @param task Task to be assigned to this person.
     */
    public void setTask(Task task) {
        this.task = task;
    }

    public boolean isBusy() {
        return this.task != null;
    }

    /**
     * Check if person has specified task assigned
     * @param taskName
     * @return boolean
     */
    public boolean hasTask(String taskName) {
        if (this.task == null) {
            return false;
        } else {
            return this.task.getTaskTitle().equals(taskName);
        }
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Updates the efficiency value based on when the task was completed,
     * +1 on the deadline day itself, +N for N days early and -2N for N days late.
     */
    public Efficiency updateEfficiency() {
        if (this.task == null) {
            return this.efficiency;
        }

        LocalDateTime deadlineDateTime = this.task.getDeadline().getDateTime();
        int daysBetween = (int) ChronoUnit.DAYS.between(LocalDateTime.now(), deadlineDateTime);

        if (daysBetween == 0) {
            return this.efficiency.increase(1);
        } else if (daysBetween > 0) {
            return this.efficiency.increase(daysBetween);
        } else {
            return this.efficiency.decrease(-daysBetween);
        }
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && address.equals(otherPerson.address)
                && tags.equals(otherPerson.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, department, tags, comment);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("department", department)
                .add("tags", tags)
                .add("comment", comment)
                .toString();
    }

}
