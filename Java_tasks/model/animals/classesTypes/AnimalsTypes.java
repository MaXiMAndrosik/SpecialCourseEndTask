package model.animals.classesTypes;

import java.util.ArrayList;
import java.util.List;

import model.animals.animalTypes.PackAnimals;
import model.animals.animalTypes.Pets;

public class AnimalsTypes {
    
    public String getAnimalsType(int classChoice, int typeChoice) {
        String animalType = "";
        switch (classChoice) {
            case 0:     // Pets
                animalType = Pets.getPet(typeChoice);
                break;
            case 1:     //PackAnimals
                animalType = PackAnimals.getPackAnimal(typeChoice);
                break;
            default:
                break;
        }
		return animalType;
	}

    public static List<String> getAnimalsTypesList(int classChoice) {
        List<String> animalsTypesList = new ArrayList<>();
        switch (classChoice) {
            case 0:     // Pets
                animalsTypesList = Pets.getPetsList();
                break;
            case 1:     //PackAnimals
            animalsTypesList = PackAnimals.getPackAnimalsList();
                break;
            default:
                break;
        }
		return animalsTypesList;
	}



    
}
