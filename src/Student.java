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

public class Student extends Person implements Serializable {

   // private static final long serialVersionUID = -6241524212370851996L;

    public List<Course> activeCourses = new ArrayList<>();
    public List<CourseInstance> courseInstances = new ArrayList<>();

    public List<Course> getActiveCourses() {
        return activeCourses;
    }

    public Student(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
    }

    public List<CourseInstance> getCourseInstances() {
        return courseInstances;
    }

    /*public Course removeCourseFromStudent(String input) {
        for (Course c : courses) {
            if (input.equalsIgnoreCase(c.getName())) {
                return c;
            }
        }
        return null;
    }*/
}

