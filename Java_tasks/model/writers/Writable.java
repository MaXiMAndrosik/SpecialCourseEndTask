package model.writers;

import model.animals.Animal;
import model.animals.animalsList.AnimalsList;

public interface Writable {

    String saveData(AnimalsList<Animal> animalsList, String fileName) throws Exception;

    AnimalsList<Animal> loadData(String file_name) throws Exception;


}
