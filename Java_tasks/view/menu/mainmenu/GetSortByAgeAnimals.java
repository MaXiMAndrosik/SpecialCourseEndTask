package view.menu.mainmenu;

import view.ConsoleUI;
import view.commands.Command;

public class GetSortByAgeAnimals extends Command{

	public GetSortByAgeAnimals(ConsoleUI consoleUI) {
		super(consoleUI);
		description = "Отсортировать реестр по возрасту питомцев";
	}

	@Override
	public void execute() {
		consoleUI.sortByAge();
	}

}
