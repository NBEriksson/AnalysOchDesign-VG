/**
 * Created by Nina Eriksson
 * Date: 2020-12-03
 * Time: 15:14
 * Project: AnalysOchDesign
 * Copyright: MIT
 */

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    protected List<Person> personList = new ArrayList<>();
    protected List<Course> courseList = new ArrayList<>();

    protected final String PERSONS = "resources\\persons.dat";
    protected final String COURSES = "resources\\courses.dat";

    private List<Student> studentListJava = new ArrayList<>();
    private List<Student> studentListFysik = new ArrayList<>();
    private List<Student> studentListKemi = new ArrayList<>();

/*
    Student nina = new Student("Nina", "Eriksson", "nina@");
    Student rune = new Student("Johan", "Rune", "rune@");
    Student thilander = new Student("Johan", "Thilander", "thilander@");
    Student nick = new Student("Nick", "Danielsson", "nick@");

    Teacher almgren = new Teacher("Peter", "Almgren", "almgren@");

    Course java = new Course("Java",almgren,studentListJava);
    Course fysik = new Course("Fysik",almgren,studentListFysik);
    Course kemi = new Course("Kemi",almgren,studentListKemi);

    //CourseInstance courseInstanceJavaIG = new CourseInstance(java, Grade.IG);
    //CourseInstance courseInstanceJavaG = new CourseInstance(java, Grade.G);
    //CourseInstance courseInstanceJavaVG = new CourseInstance(java, Grade.VG);

    //CourseInstance courseInstanceFysikIG = new CourseInstance(fysik, Grade.IG);
    //CourseInstance courseInstanceFysikG = new CourseInstance(fysik, Grade.G);
    //CourseInstance courseInstanceFysikVG = new CourseInstance(fysik, Grade.VG);

    //CourseInstance courseInstanceKemiIG = new CourseInstance(kemi, Grade.IG);
    //CourseInstance courseInstanceKemiG = new CourseInstance(kemi, Grade.G);
    //CourseInstance courseInstanceKemiVG = new CourseInstance(kemi, Grade.VG);
*/

    public Database (){
/*
        //nina.courseInstances.add(courseInstanceJavaIG);
        //nina.courseInstances.add(courseInstanceFysikG);
        //nina.courseInstances.add(courseInstanceKemiVG);
        nina.activeCourses.add(fysik);
        nina.activeCourses.add(java);

        //rune.courseInstances.add(courseInstanceJavaG);
        //rune.courseInstances.add(courseInstanceFysikG);
        //rune.courseInstances.add(courseInstanceKemiG);//avslutad kurs med betyg
        rune.activeCourses.add(fysik);//pågående kurs

        //thilander.courseInstances.add(courseInstanceJavaVG);
        //thilander.courseInstances.add(courseInstanceFysikG);
        //thilander.courseInstances.add(courseInstanceKemiIG);
        thilander.activeCourses.add(java);

        //nick.courseInstances.add(courseInstanceJavaG);
        //nick.courseInstances.add(courseInstanceFysikG);
        //nick.courseInstances.add(courseInstanceKemiIG);
        nick.activeCourses.add(fysik);

        personList.add(rune);
        personList.add(almgren);
        personList.add(nick);
        personList.add(thilander);
        personList.add(nina);

        courseList.add(java);
        courseList.add(fysik);
        courseList.add(kemi);

        fysik.addStudent(nina);
        fysik.addStudent(rune);
        fysik.addStudent(nick);
        java.addStudent(nina);
        java.addStudent(thilander);

        almgren.addCourse(java);
        almgren.addCourse(fysik);
        almgren.addCourse(kemi);

        saveToFile(courseList,COURSES);
        saveToFile(personList, PERSONS);
*/

        personList = retrieveFromFile(PERSONS);
        courseList = retrieveCourseFromFile(COURSES);
    }

    public List<Person> getPersonList (){
        return this.personList;
    }

    /*public List<Course> getCourseList (){
        return this.courseList;
    }*/


    /***************************************************/
    public <T> void saveToFile(List<T> list, String filePath) {

        try (ObjectOutputStream saveToFile = new ObjectOutputStream(new FileOutputStream(filePath))) {

            saveToFile.writeObject(list);

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.getCause();
            System.out.println("Det gick inte att spara till fil.");
        }

    }

    public List<Person> retrieveFromFile(String path) {

        List<Person> list = new ArrayList<>();

        try (ObjectInputStream retrieveFromFile = new ObjectInputStream(new FileInputStream(path))) {

            list = (List<Person>) retrieveFromFile.readObject();

        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            e.getCause();
            System.out.println("Det gick inte att läsa från fil.");

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.getCause();
            System.out.println("Det gick inte att läsa från fil.");

        }
        return list;
    }
    public List<Course> retrieveCourseFromFile(String path) {

        List<Course> list = new ArrayList<>();

        try (ObjectInputStream retrieveFromFile = new ObjectInputStream(new FileInputStream(path))) {

            list = (List<Course>) retrieveFromFile.readObject();

        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            e.getCause();
            System.out.println("Det gick inte att läsa från fil.");

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.getCause();
            System.out.println("Det gick inte att läsa från fil.");
        }
        return list;
    }


    public Course getCourse (String userInput){
        for (Course c:courseList) {
            if (userInput.equalsIgnoreCase(c.getName())){
                return c;
            }
        }
        return null;
    }
}


