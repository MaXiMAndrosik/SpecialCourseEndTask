package model.animals.animalClasses;

import java.util.ArrayList;
import java.util.List;

public enum AnimalsClasses {
    Pets,
    PackAnimals;

    public static String getAnimalsClass(int numChoice) {
        AnimalsClasses[] types = AnimalsClasses.values();
        if (numChoice < types.length) {
            return types[numChoice].toString();
        }
        return "Упс!!!";
    }

    public static List<String> getAnimalsClassesList() {
        List<String> lis = new ArrayList<>();
        AnimalsClasses[] types = AnimalsClasses.values();
        for (AnimalsClasses clas : types) {
            lis.add(clas.toString());
        }        
        return lis;
    }
}
