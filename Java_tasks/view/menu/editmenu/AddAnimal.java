package view.menu.editmenu;

import view.ConsoleUI;
import view.commands.Command;

public class AddAnimal extends Command {

    public AddAnimal(ConsoleUI consoleUI) {
        super(consoleUI);
        description = "Добавить питомца в реестр домашних животных";
    }

    @Override
    public void execute() {
        consoleUI.addAnimal();
    }

}