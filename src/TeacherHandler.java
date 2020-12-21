/**
 * Created by Nina Eriksson
 * Date: 2020-12-03
 * Time: 15:17
 * Project: AnalysOchDesign
 * Copyright: MIT
 */

import java.util.*;


public class TeacherHandler {
    Scanner sc = new Scanner(System.in);
    String input;
    Student student;
    CourseInstance ci;


    public void setGrade(Database database) {
        ci = new CourseInstance();
        // Visar endast studenter som är registrerade på minst en kurs
        System.out.println("Vilken student vill du sätta betyg på? (ange e-mail)");
        for (Person p : database.personList) {
            if (p instanceof Student) {
                if (((Student) p).getActiveCourses().size() > 0) {
                    System.out.println(p.getFirstName() + " " + p.getLastName() + ", " + p.getEmail());
                }
            }
        }
        input = sc.nextLine();
        // visar vilka kurser studenten har, och ber läraren välja den kurs han/hon vill sätta betyg i
        for (Person p : database.personList) {
            if (input.equalsIgnoreCase(p.getEmail())) {
                student = (Student) p;
                System.out.println(p.getFirstName() + " har följande kurser, vilken kurs vill du betygsätta?");
                for (Course courses : student.getActiveCourses()) {
                    System.out.println(courses.getName());
                }
                Course course = null;
                input = sc.nextLine();
                String courseName = input;
                for (Course courses : student.getActiveCourses()) {
                    if (input.equalsIgnoreCase(courses.getName())) {
                        course = courses;
                    }
                }
                ci.setCourse(course);

                System.out.println("Vilket betyg vill du sätta? (IG, G, VG)");
                input = sc.nextLine();
                switch (input) {
                    case "g", "G":
                        ci.setGrade(Grade.G);
                        break;
                    case "vg", "VG":
                        ci.setGrade(Grade.VG);
                        break;
                    case "ig", "IG":
                        ci.setGrade(Grade.IG);
                        break;
                }
                student.courseInstances.add(ci);
                System.out.println("Student: " + student.getFirstName() + " får betyg: " + input);

                //radera kurs från studentens lista över aktiva kurser när betyg är satt,
                //kursen blir då enn kursinstans (kurs med betyg)
                int index = 0;
                for (Course c : student.getActiveCourses()) {
                    if (c.getName().equalsIgnoreCase(courseName)) {//om rätt kurs
                        student.activeCourses.remove(index);
                        break;
                    }
                    index++;
                }
            }
        }
    }


    public void addStudentToCourse(Database database) {
        Student tempStudent = null;

        System.out.println("Följande studenter finns:");
        for (Person person : database.personList) {
            if (person instanceof Student)
                System.out.println(person.getFirstName() + " " + person.getLastName() + ", e-mail: " + person.getEmail());
        }
        System.out.println("Ange e-mail på den student du vill lägga till:");
        input = sc.nextLine();
        for (Person person : database.personList) {
            if (person instanceof Student) {
                if (input.equalsIgnoreCase(person.getEmail())) {//om träff
                    tempStudent = (Student) person;
                }
            }
        }

        /***   NINA START   ******/
        //hämta kurser som studenten har gått klart eller har pågående och spara i ett set
        Set<String> notPossibleCourses = new HashSet<String>();

        for (Course course : tempStudent.activeCourses) {
            notPossibleCourses.add(course.getName());
        }
        for (CourseInstance courseInstance : tempStudent.courseInstances) {
            CourseInstance tempCourseInstance = courseInstance;
            Course tempCourse = tempCourseInstance.getCourse();
            notPossibleCourses.add(tempCourse.getName());
        }
        if (!(database.courseList.size() == notPossibleCourses.size())) {
            System.out.println("Följande kurser finns tillgängliga för denna student:");
            for (Course course : database.courseList) {
                if (!notPossibleCourses.contains(course.getName())) {
                    System.out.print(course.getName() + ", ");
                }
            }
            System.out.println("\nAnge vilken kurs du vill lägga till studenten till:");
            input = sc.nextLine();
            tempStudent.activeCourses.add(database.getCourse(input));
            database.getCourse(input).addStudent(tempStudent);
            System.out.println("Lade till student " + tempStudent.getFirstName() + " till kursen " + database.getCourse(input).getName());
            database.saveToFile(database.personList, database.PERSONS);
        } else
            System.out.println("Denna student har redan läst eller är aktiv på de kurser skolan erbjuder.");
        /***   NINA SLUT   ******/
    }


    public void listAllCourses(Teacher teacher) {
        int i = 1;
        for (Course course : teacher.getCourses()) {
            System.out.println("\n\n" + i + ". " + course.getName());
            i++;

            if (course.getStudentList().size() == 0)
                System.out.print("Inga studenter i denna kurs");
            else {
                for (Student student : course.getStudentList()) {
                    System.out.print(student.getFirstName() + " " + student.getLastName() + ", ");
                }
            }
        }
        System.out.println("\n");
    }


    public void deleteStudentFromCourse(Database database) {
        String email;
        String courseToDelete;
        //List<Course> studentActiveCourseList = null;

        System.out.println("Skriv studentens emailadress:");
        email = sc.nextLine();
        //hitta studentens lista med kurser
        for (Person p : database.personList) {
            if (p.getEmail().equals(email)) {//om rätt student
                student = (Student) p;
                //studentActiveCourseList = student.activeCourses;
            }
        }
        System.out.println("Vilken kurs vill du radera:");
        for (Course course : student.activeCourses) {
            System.out.print(course.getName() + ", ");
        }
        System.out.println("");
        courseToDelete = sc.nextLine();
        //ta bort kurs (bara kurser där inte slutbetyg är satt)
        for (int i = 0; i < student.activeCourses.size(); i++) {
            if (student.activeCourses.get(i).getName().equalsIgnoreCase(courseToDelete)) {
                student.activeCourses.remove(i);
            }
        }
    }

    /****    START NYTT    ************************/
    public void createNewCourse(Database database) {
        System.out.println("Vad heter nya kursen?");
        String nameOfNewCourse = sc.nextLine();

        Course newCourse = new CourseBuilder(nameOfNewCourse)
                .setTeacher().setStudentList().build();
        database.courseList.add(newCourse);
        //lägg till kurs i lärarens egna kurslista
        Teacher teacher = new Teacher();
        for (Person person : database.personList){
            if(person.getEmail().equalsIgnoreCase("almgren@")){//hårdkodat pga tidsbrist
                teacher = (Teacher)person;
                teacher.addCourse(newCourse);
            }
        }
    }
    /****    SLUT NYTT    ************************/
}
