package model.animals.animalsList;

import java.util.Comparator;

public class ComparatorByAge <T extends AnimalsItem<T>> implements Comparator<T> {
    
    @Override
    public int compare(T o1, T o2) {
        return Integer.compare(o2.getNumAge(), o1.getNumAge());        
    }

}