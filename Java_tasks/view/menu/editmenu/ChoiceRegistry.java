package view.menu.editmenu;

import view.ConsoleUI;
import view.commands.Command;

public class ChoiceRegistry extends Command {

	public ChoiceRegistry(ConsoleUI consoleUI) {
		super(consoleUI);
		description = "Изменить выбор реестра домашних животных для редактирования";
	}

	@Override
	public void execute() {
		consoleUI.choiceEditRegistry();
	}

}
