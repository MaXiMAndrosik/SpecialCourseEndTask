package view.menu.editmenu;

import view.ConsoleUI;
import view.commands.Command;

public class BackMainMenu extends Command {

    public BackMainMenu(ConsoleUI consoleUI) {
		super(consoleUI);
		description = "Вернуться в главное меню";
	}

    @Override
    public void execute() {
        consoleUI.loadAllAnimalsRegistry();
        consoleUI.start(0);
    }

}
