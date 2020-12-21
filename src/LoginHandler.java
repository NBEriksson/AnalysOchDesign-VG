/**
 * Created by Nina Eriksson
 * Date: 2020-12-03
 * Time: 15:15
 * Project: AnalysOchDesign
 * Copyright: MIT
 */

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class LoginHandler {
    Scanner userInput = new Scanner(System.in);
    String inputFromUser;
    Database database = new Database();
    StudentHandler studentHandler = new StudentHandler();
    TeacherHandler teacherHandler = new TeacherHandler();
    Person activePerson;
    PersonFactory personFactory = new PersonFactory();
    boolean exit = false;


    public void logIn() {
        String userNameFromInput;

        do {
            printMainMenu();
            boolean stop;
            inputFromUser = userInput.nextLine();

            if (!inputFromUser.matches("1|2|3|4"))
                logIn();

            if (inputFromUser.equals("1")) {
                System.out.println("Ange användarnamn: ");
                userNameFromInput = userInput.nextLine();
                activePerson = getPerson(userNameFromInput, database.getPersonList());

                if (activePerson instanceof Student) {
                    System.out.println("Välkommen " + activePerson.getFirstName() + " " + activePerson.getLastName());
                    stop = false;

                    do {
                        printStudentMenu();
                        inputFromUser = userInput.nextLine();
                        if (inputFromUser.equalsIgnoreCase("1")) {
                            studentHandler.printCourseInstances((Student) activePerson);
                        }
                        if (inputFromUser.equalsIgnoreCase("2")) {
                            studentHandler.printCourses((Student) activePerson);
                        }
                        if (inputFromUser.equalsIgnoreCase("3")) {
                            try {
                                studentHandler.printStudyCertificate((Student) activePerson);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                        if (inputFromUser.equalsIgnoreCase("4")) {
                            stop = true;
                        }
                    } while (stop == false);
                }


                if (activePerson instanceof Teacher) {
                    System.out.println("Välkommen " + activePerson.getFirstName() + " " + activePerson.getLastName());
                    stop = false;

                    do {
                        printTeacherMenu();
                        inputFromUser = userInput.nextLine();

                        if (inputFromUser.equalsIgnoreCase("1")) {
                            teacherHandler.addStudentToCourse(database);
                        }
                        if (inputFromUser.equals("2")) {
                            teacherHandler.deleteStudentFromCourse(database);
                        }
                        if (inputFromUser.equals("3")) {
                            teacherHandler.setGrade(database);
                        }
                        if (inputFromUser.equals("4")) {
                            teacherHandler.listAllCourses((Teacher) activePerson);
                        }
                        if (inputFromUser.equalsIgnoreCase("5")) {
                            teacherHandler.createNewCourse(database);
                        }
                        if (inputFromUser.equals("6")) {
                            stop = true;
                        }
                    } while (stop == false);
                }
                inputFromUser = "";//"rensa"
            }

            if (inputFromUser.equals("2")) {
                personFactory.createAccount(database);
                logIn();
            }

            if (inputFromUser.equals("3")) {
                exit = true;
                System.out.println("Programmet avslutas!");
                System.exit(0);
            }
            inputFromUser = "";//"rensa"
        } while (!exit);
    }


    private void printMainMenu() {
        System.out.println("-----  HUVUDMENY  -----");
        System.out.println("1. Logga in");
        System.out.println("2. Skapa konto");
        System.out.println("3. Avsluta");
    }

    private void printStudentMenu() {
        System.out.println("1. Avslutade kurser (betygssatta)");
        System.out.println("2. Pågående kurser");
        System.out.println("3. Skriv ut studieintyg");
        System.out.println("4. Logga ut");
    }

    private void printTeacherMenu() {
        System.out.println("1. Lägg till student till kurs");
        System.out.println("2. Ta bort student från kurs");
        System.out.println("3. Sätt betyg");
        System.out.println("4. Lista mina kurser (inklusive studenter)");
        System.out.println("5. Registrera ny kurs");
        System.out.println("6. Logga ut");
    }

    private void printCreateAccountMenu() {
        System.out.println("1. Skapa studentkonto");
        System.out.println("2. Skapa lärarkonto");
    }

    public static Person getPerson(String userNameFromInput, List<Person> personList) {
        for (Person person : personList) {
            if (person.getEmail().equalsIgnoreCase(userNameFromInput)) {
                return person;
            }
        }
        return null;
    }
}

