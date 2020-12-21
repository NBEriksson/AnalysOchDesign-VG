/**
 * Created by Nina Eriksson
 * Date: 2020-12-03
 * Time: 15:14
 * Project: AnalysOchDesign
 * Copyright: MIT
 */

import java.io.Serializable;

public class CourseInstance implements Serializable {
    private Course course;
    private Grade grade;

   /* public CourseInstance(Course course, Grade grade){
        this.course = course;
        this.grade = grade;

    }*/
    public CourseInstance(){}


    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) { this.course = course;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }


   /* public void printMe() {
        System.out.println("Kurs: " + getCourse().getName() + ", Betyg: " + grade);
    }*/
}