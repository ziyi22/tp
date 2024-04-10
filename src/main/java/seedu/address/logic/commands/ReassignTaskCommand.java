package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;

/**
 * Reassigns task to another person
 */
public class ReassignTaskCommand extends Command {

    public static final String COMMAND_WORD = "reassign";

    public static final String MESSAGE_REASSIGN_TASK_SUCCESS = "Reassigned task %1$s from %2$s to %3$s successfully";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Reassigns a task to a person identified by index number.\n"
            + "Parameters: "
            + PREFIX_FROM + "INDEX (must be a positive integer) "
            + PREFIX_TO + "INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_FROM + "2 "
            + PREFIX_TO + "1";
    public static final String MESSAGE_PERSON_IS_BUSY = "A task has already been assigned for %1$s. "
            + "Please select another person.";
    public static final String MESSAGE_TASK_DOES_NOT_EXIST = "%s has not been assigned a task. "
            + "Please select another Task";
    private final Index fromIndex;
    private final Index toIndex;

    /**
     * Reassigns a task from one person to another.
     * @param fromIndex
     * @param toIndex
     */
    public ReassignTaskCommand(Index fromIndex, Index toIndex) {
        requireAllNonNull(fromIndex, toIndex);
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory commandHistory) throws CommandException {
        requireNonNull(model);
        List<Person> personList = model.getFilteredPersonList();

        if ((fromIndex.getZeroBased() >= personList.size() || toIndex.getZeroBased() >= personList.size())) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person assignedFrom = personList.get(fromIndex.getZeroBased());
        Person assignedTo = personList.get(toIndex.getZeroBased());

        if (assignedTo.isBusy()) {
            throw new CommandException(String.format(MESSAGE_PERSON_IS_BUSY, Messages.printName(assignedTo)));
        }
        Task task = assignedFrom.getTask();
        if (task == null) {
            throw new CommandException(String.format(MESSAGE_TASK_DOES_NOT_EXIST, Messages.printName(assignedFrom)));
        }

        model.reassignTask(task, assignedFrom, assignedTo);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        model.commitAddressBook();

        return new CommandResult(String.format(MESSAGE_REASSIGN_TASK_SUCCESS,
                Messages.printTask(task), Messages.printName(assignedFrom), Messages.printName(assignedTo)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ReassignTaskCommand)) {
            return false;
        }

        ReassignTaskCommand otherReassignCommand = (ReassignTaskCommand) other;
        return fromIndex.equals(otherReassignCommand.fromIndex)
                && toIndex.equals(otherReassignCommand.toIndex);
    }
}
