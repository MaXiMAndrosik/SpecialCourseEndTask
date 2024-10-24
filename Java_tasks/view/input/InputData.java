package view.input;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputData {

    private static final String INPUT_ERROR = "Вы ввели неверное значение";
    private Scanner scanner;
    private CheckValidityInput checkValidity;
    private String str;
    private List<Integer> localDataList;
    private boolean flag;

    public InputData() {
        scanner = new Scanner(System.in);
        checkValidity = new CheckValidityInput();
        localDataList = new ArrayList<>();
    }

    public String inputStringData(String input) {
        str = "";
        flag = true;
        while (flag) {
            System.out.print(input + " ");
            String someString = scanner.nextLine();
            if (!checkValidity.checkTextForOnlyDigits(someString) && someString.length() < 25) {
                flag = false;
                someString = someString.replaceAll("[^A-Za-zА-Яа-я0-9]", " ");
                str = someString.replaceAll("[\\s]{2,}", " ").trim();
            } else {
                System.out.println(INPUT_ERROR);
            }
        }
        return str;
    }

    public List<Integer> inputLocalDate() {
        localDataList.clear();
        int year = 0;
        int month = 0;
        String temp = "";
        System.out.println("Укажите дату рождения питомца: ");
        flag = true;
        while (flag) {
            System.out.print("Год: ");
            temp = scanner.nextLine();
            if (checkValidity.checkYear(temp)) {
                year = Integer.parseInt(temp);
                flag = false;
                localDataList.add(year);
            } else {
                System.out.println(INPUT_ERROR);
            }
        }
        flag = true;
        while (flag) {
            System.out.print("Месяц: ");
            temp = scanner.nextLine();
            if (checkValidity.checkMonth(temp, year)) {
                month = Integer.parseInt(temp);
                flag = false;
                localDataList.add(month);
            } else {
                System.out.println(INPUT_ERROR);
            }
        }
        flag = true;
        while (flag) {
            System.out.print("День: ");
            temp = scanner.nextLine();
            if (checkValidity.checkDay(temp, year, month)) {
                int day = Integer.parseInt(temp);
                flag = false;
                localDataList.add(day);
            } else {
                System.out.println(INPUT_ERROR);
            }
        }
        return localDataList;
    }

    public int inputChoice(int num) {
        boolean flag = true;
        int choice = 0;
        while (flag) {
            String choiceString = scanner.nextLine();
            if (checkValidity.choiceInput(choiceString, num)) {
                choice = Integer.parseInt(choiceString);
                flag = false;
            } else {
                System.out.println(INPUT_ERROR);
            }
        }
        return choice;
    }
}
