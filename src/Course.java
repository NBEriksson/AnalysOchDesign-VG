/**
 * Created by Nina Eriksson
 * Date: 2020-12-03
 * Time: 15:13
 * Project: AnalysOchDesign
 * Copyright: MIT
 */

import java.io.Serializable;
import java.util.List;


public class Course implements IPrintable, Serializable {

    //private static final long serialVersionUID = 6610376131342115320L;

    private String courseName;
    //private Teacher teacher;
    private Person person;
    private List<Student> studentList;


    public Course(String courseName, Person teacher, List<Student> studentList) {
        this.courseName = courseName;
        this.person = teacher;
        this.studentList = studentList;
    }

    public Course(String courseName, Person teacher) {
        this.courseName = courseName;
        this.person = teacher;
    }

    public Course(String courseName) {
        this.courseName = courseName;
    }

    public String getName() {
        return courseName;
    }

    public Teacher getTeacher() {
        return (Teacher) this.person;
    }

    public List<Student> getStudentList() {
        return this.studentList;
    }

    public void addStudent(Student student) {
        studentList.add(student);
    }

    @Override
    public void printMe() {
        System.out.println("Kurs: " + courseName);
    }
}
