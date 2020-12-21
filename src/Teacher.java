/**
 * Created by Nina Eriksson
 * Date: 2020-12-03
 * Time: 15:16
 * Project: AnalysOchDesign
 * Copyright: MIT
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Teacher extends Person implements Serializable {

    //private static final long serialVersionUID = -1298429723027191470L;

    private List<Course> courses = new ArrayList<>();

    public Teacher() {
        super();
    }

    public Teacher(String firstName, String lastName, String email) {
        super(firstName, lastName, email);

    }

    public void addStudentToCourse(Student student, Course course) {
        // lägga till student till kurs. Enbart inskrivna studenter ska kunna läggas till).
    }

    public void removeStudentFromCourse(Student student, Course course) {

    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public List<Course> getCourses(){
        return this.courses;
    }

}
