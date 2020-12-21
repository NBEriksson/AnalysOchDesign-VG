import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nina Eriksson
 * Date: 2020-12-20
 * Time: 18:26
 * Project: AnalysOchDesign
 * Copyright: MIT
 */

public class CourseBuilder {
    //Builder Class

    // required parameters
    private String courseName;
    // optional parameters
    private Person person;
    private List<Student> studentList;


    /*public CourseBuilder(String courseName, Teacher teacher, List<Student> studentList) {
        this.courseName = courseName;
        this.person = teacher;
        this.studentList = studentList;
    }*/
    public CourseBuilder(String courseName) {
        this.courseName = courseName;
        //this.person = teacher;
        //this.studentList = studentList;
    }

    public CourseBuilder setCourseName(String courseName) {
        this.courseName = courseName;
        return this;
    }
    public CourseBuilder setTeacher() {
        Teacher newTeacher = new Teacher("Peter", "Almgren", "almgren@");
        this.person = newTeacher;
        return this;
    }

    public CourseBuilder setStudentList() {
        this.studentList = new ArrayList<>();
        return this;
    }

    /*public Course build() {
        return new Course(this);
    }*/

    public Course build() {
        return new Course(courseName, person, studentList);
    }
}
