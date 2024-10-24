package view.menu.editmenu;

import java.util.ArrayList;
import java.util.List;

import view.ConsoleUI;
import view.commands.Command;
import view.menu.mainmenu.GetAllAnimalsRegistry;


public class EditMenu {

    private List<Command> commandList;

    public EditMenu(ConsoleUI consoleUI) {
        commandList = new ArrayList<>();
        commandList.add(new GetAllAnimalsRegistry(consoleUI));
        commandList.add(new AddAnimal(consoleUI));
        commandList.add(new GetCommandsList(consoleUI));
        commandList.add(new СhangeCommandsList(consoleUI));
        commandList.add(new DelAnimal(consoleUI));
        commandList.add(new ChoiceRegistry(consoleUI));
        commandList.add(new BackMainMenu(consoleUI));
    }

    public void execute(int choice) {
        Command command = commandList.get(choice - 1);
        command.execute();
    }

    public List<Command> getMenuList() {
        return commandList;
    }

}