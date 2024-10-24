package view.menu.mainmenu;

import view.ConsoleUI;
import view.commands.Command;

public class GetSortByNameAnimals extends Command {

	public GetSortByNameAnimals (ConsoleUI consoleUI) {
		super(consoleUI);
		description = "Отсортировать реестр по имени питомцев";
	}

	@Override
	public void execute() {
		consoleUI.sortByName();

	}


}
