import java.io.FileNotFoundException;

/**
 * Created by Nina Eriksson
 * Date: 2020-12-03
 * Time: 15:16
 * Project: AnalysOchDesign
 * Copyright: MIT
 */

public class StudentHandler {

    public void printCourses(Student student){
        if(student.getActiveCourses().size() > 0){//om några kurser finns i listan
            for(Course course : student.getActiveCourses()){
                course.printMe();
            }
        }
        else
            System.out.println("Du (" + student.getFirstName() + " " + student.getLastName() + ") är inte registrerad på några kurser!");
    }


    public void printCourseInstances(Student student){
        if(student.getCourseInstances().size() > 0) {//om några kursertillfällen finns i listan
            for(CourseInstance courseInstance : student.getCourseInstances()){
                System.out.println("Betyg i " + courseInstance.getCourse().getName()+ ": " + courseInstance.getGrade() );
            }
        }
        else
            System.out.println("Du (" + student.getFirstName() + " " + student.getLastName() + ") har inga kurser med betyg");
    }

    public void printStudyCertificate(Student student) throws FileNotFoundException {
        SingletonPrinter printer = SingletonPrinter.getInstance();
        printer.print(student);
    }
}

