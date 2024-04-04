package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.NoTaskPredicate;

/**
 * Finds and lists all persons in address book who does not have an active task.
 */
public class FindFreePersonCommand extends Command {

    public static final String COMMAND_WORD = "findfree";

    public static final String MESSAGE_SUCCESS = "Listed all persons without an active task";
    private final NoTaskPredicate predicate = new NoTaskPredicate();

    public FindFreePersonCommand() {
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.updateFilteredPersonList(new NoTaskPredicate());
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindCommand)) {
            return false;
        }

        FindFreePersonCommand otherFindFreeCommand = (FindFreePersonCommand) other;
        return predicate.equals(otherFindFreeCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
