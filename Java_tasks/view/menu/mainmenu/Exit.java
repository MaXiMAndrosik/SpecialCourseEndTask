package view.menu.mainmenu;

import view.ConsoleUI;
import view.commands.Command;

public class Exit extends Command {

    public Exit(ConsoleUI consoleUI) {
        super(consoleUI);
        description = "Выйти из программы";
    }

    @Override
    public void execute() {
        consoleUI.exit();
    }

}