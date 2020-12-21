
/**
 * Created by Nina Eriksson
 * Date: 2020-12-18
 * Time: 19:31
 * Project: AnalysOchDesign
 * Copyright: MIT
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class SingletonPrinter {
    // Privat konstruktor som förhindrar att det skapas en publik automatiskt
    private SingletonPrinter() {
    }

    private static class SingletonHolder {
        private static SingletonPrinter instance = new SingletonPrinter();
    }

    public static SingletonPrinter getInstance() {
        return SingletonHolder.instance;
    }

    public void print(Student student) throws FileNotFoundException {
        String filename = "studycertificate\\" + student.getFirstName() + "." + student.getLastName() + ".txt";
        File file = new File(filename);
        PrintWriter output = new PrintWriter(new FileOutputStream(file, false));
        output.println ("STUDIEINTYG");
        output.println ("\n" + student.getFirstName() + " " + student.getLastName() + "\n");
        for(CourseInstance courseInstance : student.getCourseInstances()){
            output.println ("Kurs: " + courseInstance.getCourse().getName() + ", betyg: " + courseInstance.getGrade());
        }
        output.close ();
        System.out.println("Studieintyget ligger i mappen \"studycertificate\"");
    }
}
