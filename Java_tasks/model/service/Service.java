package model.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.animals.Animal;
import model.animals.animalClasses.AnimalsClasses;
import model.animals.animalsList.AnimalsList;
import model.animals.classesTypes.AnimalsTypes;
import model.counter.Counter;
import model.writers.FileHandler;

public class Service {

    private AnimalsList<Animal> animalsList;
    private AnimalsList<Animal> tempList;
    private FileHandler writeObject;
    private Counter counter;

    public Service() {
        animalsList = new AnimalsList<Animal>();
        counter = new Counter();
        writeObject = new FileHandler();
    }

    public String addAnimal(String animalsClass, String animalsType, String name, LocalDate birthday,
            String commandsList) {
        Animal animal = new Animal(animalsClass, animalsType, name, birthday, commandsList);
        if (animalsList.addAnimal(animal)) {
            counter.addCounter();
            return "\nДанные успешно добавлены. " + getCounter() + "\n";
        }
        return "\nНевозможно выполнить данную операцию. Такой питомец уже существует.\n";
    }

    public String getClasses() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String classes : AnimalsClasses.getAnimalsClassesList()) {
            stringBuilder.append(classes + " ");
        }
        return stringBuilder.toString();
    }

    public String getTypes(int choice) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String classes : AnimalsTypes.getAnimalsTypesList(choice)) {
            stringBuilder.append(classes + " ");
        }
        return stringBuilder.toString();
    }

    public List<String> getNames(String animalsClass, String animalsType) {
        List<String> nameList = new ArrayList<>();
        for (Animal animal : animalsList) {
            if (animal.animalsClass().equalsIgnoreCase(animalsClass) && 
                animal.getAnimalsType().equalsIgnoreCase(animalsType)) {
                nameList.add(animal.getName());
            }
        }
        return nameList;
    }

    public String getCommands(String name) {
        String commands = "";
        for (Animal animal : animalsList) {
            if (animal.getName().equalsIgnoreCase(name)) {
                commands = animal.getCommandsList();
            }
        }
        return commands;
    }

    public String setCommands(String name, String addCommandString) {
        String oldCommands = "";
        String newCommands = "";
        for (Animal animal : animalsList) {
            if (animal.getName().equalsIgnoreCase(name)) {
                oldCommands = animal.getCommandsList();
                newCommands = oldCommands + " " + addCommandString;
                if (animal.setCommandsList(newCommands)) {
                    return "\nДанные успешно обновлены.\n";
                }
            }
        }
        return "\nОшибка редактирования данных реестра\n";
	}

    public String deleteAnimal(String animalsClass, String animalsType, String name) {
        if (animalsList.deleteAnimal(name)) {
            counter.reduceCounter();
            return "\nДанные на питомца успешно удалены. " + getCounter() + "\n";
        }
        return "\nНевозможно выполнить данную операцию. Такого питомца не существует.\n";
	}

    public String getAllAnimalsInfo() {
        if (animalsList.getSize() == 0) {
            return "\nРеестр домашних животных ещё не создан.\n";
        } else {
            return getCounter() + "\n" + animalsList.getAllAnimalsInfo();
        }
    }

    public String getCounter() {
        return "\nКоличество записей в реестре животных: " + String.valueOf(counter.getCounter()) + "\n";
    }

    public String loadRegistry(String file_name) {
        try {
            animalsList = (AnimalsList<Animal>) writeObject.loadData(file_name);
            return "\nРеестр домашних животных " + file_name + " загружен.\n";
        } catch (Exception e) {
            animalsList = new AnimalsList<Animal>();
            return "\nРеестр домашних животных " + file_name + " не создан.\n";
        }
    }

    public String loadAllAnimalsRegistry() {
        String loadResult = "";
        try {
            for (String className : AnimalsClasses.getAnimalsClassesList()) {
                tempList = new AnimalsList<>();
                try {
                    tempList = (AnimalsList<Animal>) writeObject.loadData(className);
                    loadResult += "\nРеестр домашних животных " + className + " загружен.\n";
                } catch (Exception e) {
                    loadResult += "\nРеестр домашних животных " + className + " не создан.\n";
                }                
                for (Animal animal : tempList) {
                    animalsList.addAnimal(animal);
                }
            }
            counter.setCounter(animalsList.getSize());
            return loadResult + getCounter() + "\n";
        } catch (Exception e) {
            return "\nРеестр домашних животных ещё не создан.\n";
        }
    }

    public String saveRegistry(String animalsClass) {
        try {
            writeObject.saveData((AnimalsList<Animal>) animalsList, animalsClass);
            return "Данные реестра домашних животных сохранены.\n";
        } catch (Exception e) {
            return "Ошибка сохранения. Реестр не обновлен.\n";
        }
    }

    public void sortByName() {
        if (animalsList == null) {
            System.out.println("Данные реестра домашних животных не загружены.\n");
        } else {
            animalsList.sortByName();
        }
    }

    public void sortByAge() {
        if (animalsList == null) {
            System.out.println("Данные реестра домашних животных не загружены.\n");
        } else {
            animalsList.sortByAge();
        }
    }
}
