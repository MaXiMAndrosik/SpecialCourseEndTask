package view.menu.mainmenu;

import view.ConsoleUI;
import view.commands.Command;

public class NextMenu extends Command {

	public NextMenu (ConsoleUI consoleUI) {
		super(consoleUI);
		description = "Перейти к меню выбра реестра и редактирования данных";
	}

	@Override
	public void execute() {
		consoleUI.choiceEditRegistry();
		consoleUI.start(1);
	}
    
}
