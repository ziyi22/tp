package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_OWNER;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Task;

/**
 * Mark tasks done by employees
 */
public class EditDeadlineCommand extends Command {

    public static final String COMMAND_WORD = "edit_deadline";
    public static final String MESSAGE_MARK_TASK_SUCCESS = "Deadline for %1$s's task changed to %2$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Change the deadline of the task of a user.\n"
            + "Parameters: "
            + PREFIX_TASK_OWNER + "INDEX (must be a positive integer)\n"
            + PREFIX_DEADLINE + "dd-MM-yyyy HHmm \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TASK_OWNER + "1 "
            + PREFIX_DEADLINE + "22-04-2024 2359 ";

    public static final String MESSAGE_TASK_DOES_NOT_EXIST = "%s does not have a Task. "
            + "Please select another Task";
    private final Index index;

    private final Deadline deadline;

    /**
     * Change the deadline of the task of a user.
     */
    public EditDeadlineCommand(Index index, Deadline deadline) {
        requireAllNonNull(index, deadline);
        this.index = index;
        this.deadline = deadline;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Person> personList = model.getFilteredPersonList();

        if (index.getOneBased() >= personList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person taskOwner = personList.get(index.getZeroBased());

        if (!taskOwner.isBusy()) {
            throw new CommandException(MESSAGE_TASK_DOES_NOT_EXIST);
        }

        Task task = taskOwner.getTask();
        task.editDeadline(deadline);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_MARK_TASK_SUCCESS,
                Messages.printName(taskOwner), Messages.printDeadline(task)));

    }
}
