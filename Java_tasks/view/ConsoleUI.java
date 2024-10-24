package view;

import java.time.LocalDate;
import java.util.List;

import presenter.Presenter;
import view.input.InputData;
import view.menu.AllMenuMetods;
import view.menu.editmenu.EditMenu;
import view.menu.mainmenu.MainMenu;

public class ConsoleUI implements View {
    private Presenter presenter;
    private AllMenuMetods allMenu;
    private MainMenu mainMenu;
    private EditMenu editMenu;
    private boolean work;
    private InputData newData;
    private String name;
    private int choice;
    private String animalCommands;
    private String[] classArray;
    private int classChoice;
    private String[] typesArray;
    private int typeChoice;
    private List<String> namesArray;
    private int nameChoice;
    private String addCommandString;


    public ConsoleUI() {
        System.out.print("\033[H\033[2J");
        presenter = new Presenter(this);
        work = true;        
        allMenu = new AllMenuMetods(this);
        mainMenu = new MainMenu(this);
        editMenu = new EditMenu(this);
        newData = new InputData();
    }

    @Override
    public void printAnswer(String answer) {
        System.out.println(answer);
    }

    @Override
    public void start(int menuChoice) {
        while (work) {
            
            if (menuChoice == 0) { // Выбор в главном меню
                System.out.println(allMenu.menu("Главное меню", menuChoice));
                choice = newData.inputChoice(allMenu.getSize());
                mainMenu.execute(choice);
                continue;
            }
            if (menuChoice == 1) { // Выбор меню редактирования реестра данных
                System.out.println(allMenu.menu("Меню редактирования реестра данных", menuChoice));
                choice = newData.inputChoice(allMenu.getSize());
                editMenu.execute(choice);
                continue;
            }
        }
    }

    public void getAllAnimalsInfo() {
        System.out.print("\033[H\033[2J");
        presenter.getAllAnimalsInfo();
    }

    public void getAnimalsCounter() {
        presenter.getCounter();
    }

    public void loadAllAnimalsRegistry() {
        presenter.loadAllAnimalsRegistry();
    }

    public void sortByName() {
        System.out.print("\033[H\033[2J");
        presenter.sortByName();
    }

    public void sortByAge() {
        System.out.print("\033[H\033[2J");
        presenter.sortByAge();
    }

    public void choiceEditRegistry () {
        System.out.println("\nВыберите реестр данных для редактирования: ");
        classArray = presenter.getClasses().split(" ");
        for (int i = 0; i < classArray.length; i++) {
            System.out.print((i+1) + ". " + classArray[i] + " ");
        }
        System.out.print("\nВаш выбор (номер реестра): ");
        classChoice = newData.inputChoice(classArray.length) - 1;
        presenter.loadRegistry(classArray[classChoice]);
    }

    private void choiceEditType (String input) {
        System.out.println(input);
        typesArray =  presenter.getTypes(classChoice).split(" ");
        for (int i = 0; i < typesArray.length; i++) {
            System.out.print((i+1) + ". " + typesArray[i] + " ");
        }
        System.out.print("\nВаш выбор (порядковый номер вида): ");
        typeChoice = newData.inputChoice(typesArray.length) - 1;
    }  

    private void choiceAnimalNames (String input) {
        namesArray =  presenter.getNames(classArray[classChoice], typesArray[typeChoice]);
        if (namesArray.size() > 0) {
            System.out.println(input);
            for (int i = 0; i < namesArray.size(); i++) {
                System.out.print((i+1) + ". " + namesArray.get(i) + " ");
            }
            System.out.print("\nВаш выбор (порядковый номер имени): ");
            nameChoice = newData.inputChoice(namesArray.size()) - 1;
        } else {
            System.out.print("\nПитомцев такого вида в реестре нет! \n");
        }
    }

    public void addAnimal() {
        choiceEditType("Определите название вида для Вашего питомца: ");
        name = toUpperCaseForFirstLetter(newData.inputStringData("\nВведите имя питомца (не более 25 символов в имени): ").toLowerCase());
        List<Integer> localData = newData.inputLocalDate();
        animalCommands = newData.inputStringData("Введите список команд, которые может выполнять питомец (не более 25 символов): ");
        animalCommands = toUpperCaseForFirstLetter(animalCommands.replaceAll("[0-9]","").toLowerCase());
        presenter.addAnimal(classArray[classChoice], typesArray[typeChoice], name, 
                LocalDate.of(localData.get(0), localData.get(1), localData.get(2)), animalCommands);
    }

    public void deleteAnimal() {
        getAllAnimalsInfo();
        choiceEditType("\nДля удаления питомца, укажите к какому виду он относится: ");
        choiceAnimalNames("\nДля удаления питомца, укажите его имя: ");
        presenter.deleteAnimal(classArray[classChoice], typesArray[typeChoice], namesArray.get(nameChoice));
    }

    public void getCommandsList() {
        getAllAnimalsInfo();
        choiceEditType("\nДля получения списка команд питомца, укажите к какому виду он относится: ");
        choiceAnimalNames("\nДля получения списка команд питомца, укажите его имя: ");
        if (namesArray.size() > 0) {
            System.out.println("\n" + namesArray.get(nameChoice) + " умеет " + presenter.getCommands(namesArray.get(nameChoice)) + "\n");
        }        
    }

    public void changeCommandsList() {
        getAllAnimalsInfo();
        choiceEditType("\nДля обучения питомца, укажите к какому виду он относится: ");
        choiceAnimalNames("\nДля обучения питомца, укажите его имя: ");
        if (namesArray.size() > 0) {
            System.out.println("\n" + namesArray.get(nameChoice) + " умеет " + presenter.getCommands(namesArray.get(nameChoice)) + "\n");
            addCommandString = newData.inputStringData("\nВведите список команд, которые изучил питомец: ");
            animalCommands = toUpperCaseForFirstLetter(addCommandString.replaceAll("[0-9]","").toLowerCase());
            presenter.setCommands(namesArray.get(nameChoice), animalCommands, classArray[classChoice]);
            System.out.println("\n" + namesArray.get(nameChoice) + " умеет " + presenter.getCommands(namesArray.get(nameChoice)) + "\n");
        }
    }

    public void exit() {
        presenter.exit();
        work = false;
    }

    private String toUpperCaseForFirstLetter(String text) {
        StringBuilder builder = new StringBuilder(text);
        if (Character.isAlphabetic(text.codePointAt(0)))
            builder.setCharAt(0, Character.toUpperCase(text.charAt(0)));
        for (int i = 1; i < text.length(); i++)
            if (Character.isAlphabetic(text.charAt(i)) && Character.isSpaceChar(text.charAt(i - 1)))
                builder.setCharAt(i, Character.toUpperCase(text.charAt(i)));
    
        return builder.toString();
    }

}
