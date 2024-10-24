package view.menu.editmenu;

import view.ConsoleUI;
import view.commands.Command;

public class DelAnimal extends Command {

    public DelAnimal(ConsoleUI consoleUI) {
        super(consoleUI);
        description = "Удалить питомца из реестра домашних животных";
    }

    @Override
    public void execute() {
        consoleUI.deleteAnimal();
    }



}
