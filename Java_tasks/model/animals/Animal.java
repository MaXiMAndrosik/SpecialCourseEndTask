package model.animals;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

import model.animals.animalsList.AnimalsItem;

public class Animal implements Serializable, AnimalsItem<Animal> {

    private String animalsClass;
    private String animalsType;
    private String name;
    private LocalDate birthday;
    private String commandsList;

    public Animal(String animalsClass, String animalsType, String name, LocalDate birthday,
            String commandsList) {
        this.animalsClass = animalsClass;
        this.animalsType = animalsType;
        this.name = name;
        this.birthday = birthday;
        this.commandsList = commandsList;
    }

    public String animalInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        if (animalsClass.length() <= 5) {
            stringBuilder.append("| " + animalsClass + "\t\t\t" );
        } else if (animalsClass.length() >= 6 && animalsClass.length() <= 13) {
            stringBuilder.append("| " + animalsClass + "\t\t" );            
        } else if (animalsClass.length() >= 14 && animalsClass.length() <= 21) {
            stringBuilder.append("| " + animalsClass + "\t" );            
        } 
        if (animalsType.length() <= 5) {
            stringBuilder.append("| " + animalsType + "\t\t\t" );
        } else if (animalsType.length() >= 6 && animalsType.length() <= 13) {
            stringBuilder.append("| " + animalsType + "\t\t" );            
        } else if (animalsType.length() >= 14 && animalsType.length() <= 21) {
            stringBuilder.append("| " + animalsType + "\t" );            
        } 
        if (name.length() <= 5) {
            stringBuilder.append("| " + name + "\t\t\t" );
        } else if (name.length() >= 6 && name.length() <= 13) {
            stringBuilder.append("| " + name + "\t\t" );            
        } else if (name.length() >= 14 && name.length() <= 21) {
            stringBuilder.append("| " + name + "\t" );            
        }
        stringBuilder.append("| " +birthday + "\t");
        if (commandsList.length() <= 5) {
            stringBuilder.append("| " + commandsList + "\t\t\t\t\t|" );
        } else if (commandsList.length() >= 6 && commandsList.length() <= 13) {
            stringBuilder.append("| " + commandsList + "\t\t\t\t|" );            
        } else if (commandsList.length() >= 14 && commandsList.length() <= 21) {
            stringBuilder.append("| " + commandsList + "\t\t\t|" );            
        }
        stringBuilder.append("\n" );
        return stringBuilder.toString();
    }

    public String animalsClass() {
        return animalsClass;
    }   
    
    public String getAnimalsType() {
        return animalsType;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthday() {
        return birthday;
    } 

    @Override
    public String toString() {
        return animalInfo();
    }

    public String getCommandsList() {
        return commandsList;
    }

    public boolean setCommandsList(String newCommands) {
        if (this.commandsList != null) {
            this.commandsList = newCommands;
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Animal o) {
        return name.compareTo(o.name);
    }

    @Override
    public int getNumAge() {
        Period diff = Period.between(birthday, LocalDate.now());
        return diff.getYears()*12 + diff.getMonths();
    }
}
