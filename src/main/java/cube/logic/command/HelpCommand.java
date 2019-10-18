package cube.logic.command;

import cube.model.food.FoodList;
import cube.ui.Ui;
import cube.storage.StorageManager;

public class HelpCommand extends Command{

	String MESSAGE_SUCCESS = "Currently we support the following commands: (all command ignore cases)\n"
			+ "Manipulate tasks:\n"
			+ String.format("%1$-50s", "  'add -n <food name> -t <food type> -p <price> -s <stock> -e <expiry date>' ") + "add a new food product with all details/description\n"
			+ String.format("%1$-50s", "  'delete -i <number>'") + "delete the nth food product in the list\n"
			+ String.format("%1$-50s", "  'sold -n <food name> -q <quantity>'") + "marks quantity q of product n sold\n"
			+ "Show task list:\n"
			+ String.format("%1$-50s", "  'list'") + "shows the list of food products\n"
			+ String.format("%1$-50s", "  'reminder'") + "show the list of food products that are low on stock and/or are approaching its expiry date\n"
			+ "Miscellaneous:\n"
			+ String.format("%1$-50s", "  'bye' OR 'exit' OR 'quit'") + "to exit the programme\n"
			+ String.format("%1$-50s", "  'help'") + "to show a list of available command\n";

	/**
	 * Always returns false since this is not an exit command.
	 *
	 * @return false.
	 */

	@Override
	public boolean isExit() {
		return false;
	}

	/**
	 * Shows the list of available command.
	 *
	 * @param list the list of food products.
	 * @param ui the user interface to output message.
	 * @param storage storage of Duke.
	 */

	@Override
	public CommandResult execute(FoodList list, StorageManager storage) {
		//System.out.println("reach help");
		// todo: ui.showHelp();
		return new CommandResult(MESSAGE_SUCCESS, true, false);
	}
}