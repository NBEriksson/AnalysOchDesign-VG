
/**
 * Created by Nina Eriksson
 * Date: 2020-12-18
 * Time: 17:55
 * Project: AnalysOchDesign
 * Copyright: MIT
 */

import java.util.Scanner;

public class PersonFactory {
    Scanner userInput = new Scanner(System.in);

    public void createAccount (Database database) {
        String[] person = new String[4];

        System.out.println("Vad heter personen i förnamn?");
        person[0] = userInput.nextLine();

        System.out.println("Vad heter personen i efternamn?");
        person[1] = userInput.nextLine();

        System.out.println("Vad har personen för emailadress?");
        person[2] = userInput.nextLine();

        System.out.println("Är personen student eller lärare?");
        person[3] = userInput.nextLine();

        if (person[3].equalsIgnoreCase("student")){
            Student student = new Student(person[0], person[1], person[2]);
            database.personList.add(student);
            System.out.println("Kontot är skapat");
            database.saveToFile(database.personList, database.PERSONS);
        }

        else if (person[3].equalsIgnoreCase("lärare")){
            Teacher teacher = new Teacher(person[0], person[1], person[2]);
            database.personList.add(teacher);
            System.out.println("Kontot är skapat");
            database.saveToFile(database.personList, database.PERSONS);
        }
    }
}
