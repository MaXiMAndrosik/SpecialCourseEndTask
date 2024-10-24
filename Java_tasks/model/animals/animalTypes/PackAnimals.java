package model.animals.animalTypes;

import java.util.ArrayList;
import java.util.List;

public enum PackAnimals {
    Horse, Camel, Donkey;

    public static String getPackAnimal(int numChoice) {
        PackAnimals[] types = PackAnimals.values();
        if (numChoice < types.length) {
            return types[numChoice].toString();
        }
        return null;
    }

    public static List<String> getPackAnimalsList() {
        List<String> lis = new ArrayList<>();
        PackAnimals[] types = PackAnimals.values();
        for (PackAnimals packs : types) {
            lis.add(packs.toString());
        }        
        return lis;
    }
}
