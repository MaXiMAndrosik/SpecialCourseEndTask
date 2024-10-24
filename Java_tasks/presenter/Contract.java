package presenter;

import java.time.LocalDate;

public interface Contract {


    public interface Service {
        String getAllAnimalsInfo();
        void sortByName();
        void sortByAge();
        String addAnimal(String animalsClass, String animalsType, String name, LocalDate birthday, String commandsList);
  
    }

    public interface  Presenter {
        void getAllAnimalsInfo();
        void sortByName();
        void sortByAge();
        void addAnimal(String animalsClass, String animalsType, String name, LocalDate birthday, String commandsList);
    
    }

    public interface  View {
        void start(int choice);
        void printAnswer(String answer);
        void getAllAnimalsInfo();
        void sortByName();
        void sortByAge();
        void addAnimal();
      
    }



}
