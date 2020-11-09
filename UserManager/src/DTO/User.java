/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;
import java.util.Comparator;
/**
 *
 * @author HP
 */
public class User  {
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String phone;

    public User(String username, String firstName, String lastName, String password, String email, String phone) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }
 public User(String username){
        this.username = username;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getName(){
        return firstName + " " + lastName;
    }
    

    @Override
    public String toString() {
        return username + "," + password + "," + firstName + "," + lastName + "," + phone + "," + email;
    }
    public static Comparator<User> FirstNameComparator = new Comparator<User>() {

        public int compare(User s1, User s2) {
            String FirstName1 = s1.getFirstName().toUpperCase();
            String FirstName2 = s2.getFirstName().toUpperCase();

            //ascending order
            return FirstName1.compareTo(FirstName2);

            //descending order
            //return StudentName2.compareTo(StudentName1);
        }
    };
}
