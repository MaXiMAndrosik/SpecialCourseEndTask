package view.menu.editmenu;

import view.ConsoleUI;
import view.commands.Command;

public class СhangeCommandsList extends Command {

    public СhangeCommandsList(ConsoleUI consoleUI) {
		super(consoleUI);
		description = "Обучение питомца новым  командам";
	}

	@Override
	public void execute() {
		consoleUI.changeCommandsList();
	}
}
