package view.menu.editmenu;

import view.ConsoleUI;
import view.commands.Command;

public class GetCommandsList extends Command{

    	public GetCommandsList(ConsoleUI consoleUI) {
		super(consoleUI);
		description = "Список команд питомца";
	}

	@Override
	public void execute() {
		consoleUI.getCommandsList();
	}

}
