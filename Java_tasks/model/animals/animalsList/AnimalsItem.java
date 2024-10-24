package model.animals.animalsList;

import java.time.LocalDate;

public interface AnimalsItem<T> extends Comparable<T> {
    String getName();
    int getNumAge();
    LocalDate getBirthday();
}
