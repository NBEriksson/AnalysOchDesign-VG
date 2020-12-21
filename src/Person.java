/**
 * Created by Nina Eriksson
 * Date: 2020-12-03
 * Time: 15:16
 * Project: AnalysOchDesign
 * Copyright: MIT
 */

import java.io.Serializable;

public abstract class Person implements IPrintable, Serializable {

   // private static final long serialVersionUID = 8386352110290145248L;
    private String firstName;
    private String lastName;
    private String email;

    public Person(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Person() {

    }

    public String getFirstName() {
        return firstName;
    }

    //public void setFirstName(String firstName) {
       // this.firstName = firstName;
    //}

    public String getLastName() {
        return lastName;
    }

    //public void setLastName(String lastName) {
        //this.lastName = lastName;
   // }

    public String getEmail() {
        return email;
    }

   // public void setEmail(String email) {
       // this.email = email;
   // }

    @Override
    public void printMe() {
      
    }
}
