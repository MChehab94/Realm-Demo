package mchehab.com.realmdemo;

import io.realm.RealmObject;

/**
 * Created by muhammadchehab on 1/2/18.
 */
public class Person extends RealmObject{

    private String firstName;
    private String lastName;
    private int age;

    public Person() {
        firstName = lastName = "";
        age = 0;
    }

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
