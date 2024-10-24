package model.animals.animalTypes;

import java.util.ArrayList;
import java.util.List;

public enum Pets {
    Dog, Cat, Hamster;

    public static String getPet(int numChoice) {
        Pets[] types = Pets.values();
        if (numChoice < types.length) {
            return types[numChoice].toString();
        }
        return "Упс!!!";
    }

    public static List<String> getPetsList() {
        List<String> lis = new ArrayList<>();
        Pets[] types = Pets.values();
        for (Pets pets : types) {
            lis.add(pets.toString());
        }        
        return lis;
    }


}
