package model.writers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.animals.Animal;
import model.animals.animalsList.AnimalsList;

public class FileHandler implements Writable {

    private AnimalsList<Animal> animalsList;

    public String saveData(AnimalsList<Animal> animalsList, String fileName) throws Exception {
        String path = "model/registry/" + fileName + "_registry.txt";
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream(path));
        objectOutputStream.writeObject(animalsList);
        objectOutputStream.close();
        return "Данные реестра домашних животных сохранены.";
    }

    public AnimalsList<Animal> loadData(String file_name) throws Exception {
        ObjectInputStream objectInputStream = new ObjectInputStream(
                new FileInputStream("model/registry/" + file_name+ "_registry.txt"));
        animalsList = new AnimalsList<>();
        animalsList = (AnimalsList<Animal>) objectInputStream.readObject();
        objectInputStream.close();
        return animalsList;
    }

}
