/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import DTO.User;
import UTILS.MyValidation;
import java.util.ArrayList;
import java.util.Scanner;
import UTILS.Menu;
import java.util.Collections;
import UTILS.FileManagement;
/**
 *
 * @author HP
 */
public class UserList extends ArrayList<User> {
    private ArrayList<User> list = new ArrayList<>();
    private ArrayList<User> tempList = new ArrayList<>();
    public UserList() {
        super();
    }

     public Integer search(String username) {
        return this.indexOf(new User(username));
    }
 ArrayList<String> storedCodes = new ArrayList<>();
    public void createAccount() {
        list = FileManagement.loadUser();
        tempList = list;
        while (true) {
            String username;
            while (true) {
                System.out.print("Enter username: ");
                username = MyValidation.checkInputUserName();

                // check exist username
                if (!MyValidation.checkUserExisted(list, username)) {
                    System.out.println("Username existed! Enter again");
                } else {
                    break;
                }
            }

            // enter password
            System.out.print("Enter password: ");
            String password = MyValidation.checkInputPass();

            // check password again
            while (true) {
                System.out.print("Enter confirm password: ");
                String password_again = MyValidation.checkInputPass2();
                if (password_again.equals(password)) {
                    break;
                } else System.out.println("Confirm password not matches password. Please enter again: ");
            }
            String enpassword = MyValidation.sha256(password);
            // enter other in4
            System.out.print("Enter first name: ");
            String firstname = MyValidation.checkInputString();
            System.out.print("Enter last name: ");
            String lastname = MyValidation.checkInputString();
            System.out.print("Enter phone number: ");
            String phoneNumber = MyValidation.checkInputPhone();
            System.out.print("Enter email: ");
            String email = MyValidation.checkInputEmail();

            list.add(new User(username, enpassword, firstname, lastname, phoneNumber, email));
            System.out.println("Added");
            System.out.println("New data shoud be saved first!");
            System.out.print("Continue to create user account? (y/n)");
            if (!MyValidation.checkInputYN()) {
                return;
            } else FileManagement.writeUser(list);
            System.out.println();
        }
    }

    // check exist user
    public void checkExistedUser() {
        list = FileManagement.loadUser();
        while (true) {
            System.out.print("Enter username: ");
            String username = MyValidation.checkInputString();

            if (!MyValidation.checkUserExisted(list, username)) {
                System.out.println("This username is existed!");
            } else {
                System.out.println("This username isn't existed!");
            }

            System.out.print("Continue to check existed user? (y/n): ");
            if (!MyValidation.checkInputYN()) {
                return;
            }
            System.out.println();
        }
    }

    // search user in4 by name
    public void searchUser() {
        list = FileManagement.loadUser();

        // check if list is empty
        if (list.isEmpty()) {
            System.out.println("Nothing to search");
            return;
        }
        while (true) {
            System.out.print("Enter first name that you want to search: ");
            String firstName = MyValidation.checkInputString();
            for (User user : list) {
                if (user.getFirstName().equalsIgnoreCase(firstName)) {
                    System.out.println("User " + firstName + " is at " + list.indexOf(user) + " of the list.");
                    System.out.print("User info: " + user);
                    System.out.println();
                    return;
                } else {
                    System.out.println("Can not find user");
                    System.out.print("Do you want to continue search user? (y/n): ");
                    if (!MyValidation.checkInputYN()) {
                        return;
                    } else break;
                }
            }
        }
    }

    // search user
    // update user
    public void updateUser() {
        Scanner sc = new Scanner(System.in);
        list = FileManagement.loadUser();
        boolean confirmed;
        int pos = -1;
        do {
            // login
            System.out.println("Please login first ");
            System.out.print("Username: ");
            String username = MyValidation.checkInputString();
            System.out.print("Password: ");
            String password = MyValidation.checkInputPass();
            pos = MyValidation.checkUserExisted2(list, username);
            // check existed
            String enpassword = MyValidation.sha256(password);
            if (pos < 0 || MyValidation.checkPassExisted2(list, enpassword) < 0) {
                System.out.println("Username is not existed or password is wrong!");
                System.out.print(" Do you want to try again? (y/n): ");
                if (!MyValidation.checkInputYN()) {
                    return;
                }
            }
            if (pos >= 0) {
                System.out.print("Enter new first name: ");
                String firstname = sc.nextLine();
                if (!firstname.isEmpty())
                    list.get(pos).setFirstName(firstname);

                System.out.print("Enter new last name: ");
                String lastname = sc.nextLine();
                if (!lastname.isEmpty())
                    list.get(pos).setLastName(lastname);

                System.out.print("Enter new phone: ");
                String phone = MyValidation.checkInputPhone2();
                if (phone != null) list.get(pos).setPhone(phone);

                System.out.print("Enter new email: ");
                String mail = MyValidation.checkInputEmail2();
                if (mail != null) list.get(pos).setEmail(mail);
                System.out.println("Update successful");
                System.out.println("Update data should be saved!");
            }
            System.out.print("Continue to update a user? (y/n): ");
            confirmed = MyValidation.checkInputYN();
            if (!confirmed) {
                return;
            }
        } while (true);
    }


    // delete user
    public void deleteUser() {
        list = FileManagement.loadUser();

        while (true) {
            // login
            System.out.println("Please login first");
            System.out.print("Username: ");
            String username = MyValidation.checkInputString();
            System.out.print("Password: ");
            String password = MyValidation.checkInputPass();
            int pos = MyValidation.checkUserExisted2(list, username);
            // check username or password is correct
            String enpassword = MyValidation.sha256(password);
            if (pos < 0 || MyValidation.checkPassExisted2(list, enpassword) < 0) {
                System.out.println("Username is not existed or password is wrong!");
                System.out.print("Do you want to try again? (y/n): ");
                if (!MyValidation.checkInputYN()) {
                    return;
                }
            } else {
                // remove user
                System.out.println("User " + list.get(pos).getUsername() + " has been removed!");
                list.remove(pos);
            }

            System.out.print("Continue to remove a user? (y/n): ");
            if (!MyValidation.checkInputYN()) {
                return;
            }
        }
    }

    // save to file
    public void saveFile() {
        if (list.isEmpty()) {
            System.out.println("Nothing to save!");
            return;
        }
        FileManagement.writeUser(list);
        System.out.println("Saved");
    }

    // print all list from file
    public void printFile() {
        int count = -1;
        list = FileManagement.loadUser();
        Collections.sort(list, User.FirstNameComparator);
        System.out.println("The list show in order: Username, Encrypted Password, First Name, Last Name, Phone Number, Email");
        for (User user : list) {
            count++;
            System.out.print(count + ". ");
            System.out.println(user);
        }
    }
}
