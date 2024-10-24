package view.menu.mainmenu;

import java.util.ArrayList;
import java.util.List;

import view.ConsoleUI;
import view.commands.Command;

public class MainMenu {

    private List<Command> commandList;

    public MainMenu(ConsoleUI consoleUI) {
        commandList = new ArrayList<>();
        commandList.add(new GetAllAnimalsRegistry(consoleUI));
        commandList.add(new GetSortByNameAnimals(consoleUI));
        commandList.add(new GetSortByAgeAnimals(consoleUI));
        commandList.add(new NextMenu(consoleUI));
        commandList.add(new Exit(consoleUI));
    }

    public void execute(int choice) {
        Command command = commandList.get(choice - 1);
        command.execute();
    }

    public List<Command> getMenuList() {
        return commandList;
    }

}
