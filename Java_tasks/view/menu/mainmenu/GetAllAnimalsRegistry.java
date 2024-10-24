package view.menu.mainmenu;

import view.ConsoleUI;
import view.commands.Command;

public class GetAllAnimalsRegistry extends Command {

	public GetAllAnimalsRegistry(ConsoleUI consoleUI) {
		super(consoleUI);
		description = "Показать реестр домашних животных";
	}

	@Override
	public void execute() {
		consoleUI.getAllAnimalsInfo();
	}

}
