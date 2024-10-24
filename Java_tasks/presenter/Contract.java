package presenter;

import java.time.LocalDate;

public interface Contract {


    public interface Service     {
        String getCounter();
        String getAllAnimalsInfo();
        void sortByName();
        void sortByAge();
        String getCommands(String name);
        String setCommands(String name, String addCommandString);
        String addAnimal(String animalsClass, String animalsType, String name, LocalDate birthday, String commandsList);
        String deleteAnimal(String animalsClass, String animalsType, String name);
  
    }

    public interface  Presenter {
        void getCounter();
        void getAllAnimalsInfo();
        void sortByName();
        void sortByAge();
        String getCommands(String name);
        void setCommands(String name, String addCommandString, String animalsClass);
        void addAnimal(String animalsClass, String animalsType, String name, LocalDate birthday, String commandsList);
        void deleteAnimal(String animalsClass, String animalsType, String name);
    }

    public interface  View {
        void start(int choice);
        void printAnswer(String answer);
        void getAllAnimalsInfo();
        void getAnimalsCounter();
        void sortByName();
        void sortByAge();
        void addAnimal();
        void deleteAnimal();
        void getCommandsList();
        void changeCommandsList();
        void exit();      
    }

}
