package presenter;

import java.time.LocalDate;
import java.util.List;

import model.service.Service;
import view.View;

public class Presenter implements Contract.Presenter {
        private View view;
        private Service service;
        private String answer;


        public Presenter(View view) {
                this.view = view;
                service = new Service();
                loadAllAnimalsRegistry();
              }

        public void addAnimal(String animalsClass, String animalsType, String name, LocalDate birthday, String commandsList) {
                answer = service.addAnimal(animalsClass, animalsType, name, birthday, commandsList);
                view.printAnswer(answer);
                view.printAnswer(service.saveRegistry(animalsClass));
        }

        public String getClasses() {
                return service.getClasses();
        }

        public String getTypes(int choice) {
                return service.getTypes(choice);
        }

        public List<String> getNames(String animalsClass, String animalsType) {
                return service.getNames(animalsClass, animalsType);
        }
       
        public String getCommands(String name) {
                return service.getCommands(name);
        }

        public void setCommands(String name, String addCommandString, String animalsClass) {
                view.printAnswer(service.setCommands(name, addCommandString));
                view.printAnswer(service.saveRegistry(animalsClass));
        }

        public void deleteAnimal(String animalsClass, String animalsType, String name) {
                view.printAnswer(service.deleteAnimal(animalsClass, animalsType, name));
                view.printAnswer(service.saveRegistry(animalsClass));
        }

        public void getCounter() {
                answer = service.getCounter();
                view.printAnswer(answer);
        }

        public void getAllAnimalsInfo() {
                view.printAnswer(service.getAllAnimalsInfo());
        }

        public void loadAllAnimalsRegistry() {
                view.printAnswer(service.loadAllAnimalsRegistry());
        }


        public void loadRegistry(String file_name) {
                view.printAnswer(service.loadRegistry(file_name));
        }

        // public void saveRegistry() {
        //         view.printAnswer(service.saveRegistry());                
        // }

        public void sortByName() {
                service.sortByName();
                getAllAnimalsInfo();
        }

        public void sortByAge() {
                service.sortByAge();
                getAllAnimalsInfo();
        }

        public void exit() {
                // view.printAnswer(service.saveRegistry());
                view.printAnswer("Завершение работы приложения...");
        }


}
