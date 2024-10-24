package view.menu;

import java.util.ArrayList;
import java.util.List;

import view.ConsoleUI;
import view.commands.Command;
import view.menu.editmenu.EditMenu;
import view.menu.mainmenu.MainMenu;

public class AllMenuMetods<T> {

    private List<T> commandList;
    private MainMenu mainMenu;
    private EditMenu editMenu;

    public AllMenuMetods(ConsoleUI consoleUI) {
        commandList = new ArrayList<>();
        mainMenu = new MainMenu(consoleUI);
        editMenu = new EditMenu(consoleUI);
        // addRelatives = new RelativesMenu(consoleUI);
        // namesMenu = new HumanNamesMenu(consoleUI);
    }

    public String menu(String string, int choice) {
        StringBuilder stringBuilder = new StringBuilder();
        if (choice == 0) {
            commandList = (List<T>) mainMenu.getMenuList();
        }
        if (choice == 1) {
            commandList = (List<T>) editMenu.getMenuList();
        }
        // if (choice == 2) {
        //     commandList = (List<T>) namesMenu.getMenuList();
        // }
        stringBuilder.append(string + "\n");
        for (int i = 0; i < commandList.size(); i++) {
            stringBuilder.append(i + 1);
            stringBuilder.append(". ");
            try {
                stringBuilder.append(((Command) commandList.get(i)).getDescription());                
            } catch (Exception e) {
                stringBuilder.append(((T) commandList.get(i))); 
            }
            
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }



    public int getSize() {
        return commandList.size();
    }

}