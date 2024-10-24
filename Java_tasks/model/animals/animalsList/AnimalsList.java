package model.animals.animalsList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class AnimalsList<T extends AnimalsItem<T>> implements Serializable, Iterable<T> {
    private List<T> animalsList;

    public AnimalsList() {
        animalsList = new ArrayList<>();
    }

    public boolean addAnimal(T newAnimal) {
        boolean flag = true;
        for (T animal : animalsList) {
            if (animal.getName().equalsIgnoreCase(newAnimal.getName())
                && animal.getBirthday().equals(newAnimal.getBirthday())) {
                    flag = false;
            }
        }
        if (flag) {
            animalsList.add(newAnimal);
            return true;
        }
        return false;
    }

    public boolean deleteAnimal(String name) {
        for (T animal : animalsList) {
            if (animal.getName().equalsIgnoreCase(name)) {
                animalsList.remove(animal);
                return true;
            }
        }
		return false;
	} 

    public String getAllAnimalsInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("| AnimalsClass\t\t| AnimalsType\t\t| Name\t\t\t| BirthDate\t| CommandsList\t\t\t\t|\n");
        stringBuilder.append("|-----------------------|-----------------------|-----------------------|---------------|---------------------------------------|\n");
        for (T animal : animalsList) {
            stringBuilder.append(animal);
        }
        return stringBuilder.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return animalsList.iterator();
    }

    public void sortByName() {
        animalsList.sort(new ComparatorByName<>());
    }

    public void sortByAge() {
        animalsList.sort(new ComparatorByAge<>());
    }

    public int getSize() {
        return animalsList.size();
    }



}
