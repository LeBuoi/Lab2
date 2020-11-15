/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;
import java.util.Collections;
import DTO.User;
import UTILS.MyValidations;
import UTILS.Menu;
import UTILS.InputUtils;
import UTILS.PrintUtils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.File;

/**
 *
 * @author HP
 */
public class UserList extends ArrayList<User> {

    private static final String filename = System.getProperty("user.dir") + "/users.txt";

    public static UserList loadFromFile() {
        String s;
        FileReader f = null;
        BufferedReader rf = null;
        UserList list = new UserList();
        File f1 = new File(filename);
        if (!f1.exists()) {
            try {
                f1.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            f = new FileReader(filename);
            rf = new BufferedReader(f);

            while ((s = rf.readLine()) != null) {

                String[] arr = s.split("[;]");

                User temp = new User(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5]);
                list.add(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("The file is not existed! Please save the data to file or create \"users.txt\" in project folder!");
        } finally {
            try {
                if (f != null) {
                    f.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    // save to file
    public static void writeUser(ArrayList<User> list) {
        // check null list
        File file = new File(filename);
        if (list == null || list.isEmpty()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        PrintWriter w = null;

        try {

            w = new PrintWriter(file);
            for (User user : list) {
                w.println(user.toString());
                w.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("The file is not existed! Please save the data to file or create \"users.txt\" in project folder!");
        } finally {
            try {
                if (w != null) {
                    w.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private UserList() {

    }

    public Integer search(String username) {
        return this.indexOf(new User(username));
    }

    public void add() {

        while (true) {
            String username;
            String password;
            String firstName;
            String lastName;
            String email;
            String phoneNumber;
            while (true) {
                username = InputUtils.inputUsername(false);
                if (search(username) == -1) {
                    break;
                }
                System.out.println("User Existed!");
            }
            password = InputUtils.inputPassword(false, true);
            firstName = InputUtils.inputFirstName(false);
            lastName = InputUtils.inputLastName(false);
            email = InputUtils.inputEmail(false);
            phoneNumber = InputUtils.inputPhoneNumber(false);

            User currentUser = new User(username, password, phoneNumber, email, firstName, lastName);

            System.out.println("Current Student Info:");
            PrintUtils.printUserInfo(currentUser);

            boolean isUserConfirm = MyValidations.chooseYN("Confirm: Add This User?");

            if (isUserConfirm) {
                this.add(currentUser);
            } else {
                System.out.println("User NOT Added");
            }

            if (!MyValidations.chooseYN("Do you want to continue adding user")) {
                System.out.println("Exited!");
                return;
            }

        }
    }

    public void updateMenu() {
        Integer posID;

        if (this.isEmpty()) {
            System.out.println("The List is Empty!");
            return;
        }

        if ((posID = InputUtils.loginUser(this)) == -1) {
            return;
        }

        Menu menu = new Menu("Update and Delete Menu");

        menu.addItems("Update User Info");
        menu.addItems("Delete User");

        while (true) {
            menu.printMenu("Exit");
            Integer choice = menu.getChoice();

            switch (choice) {
                case 1:
                    update(posID);
                    break;
                case 2:
                    if (delete(posID)) {
                        return;
                    }
                    break;

                default:
                    System.out.println("Menu Exited!");
                    return;
            }
        }

    }

    private void update(Integer posID) {

        Menu updateMenu = new Menu("Update memu");
        updateMenu.add("Update password");
        updateMenu.add("Update First Name");
        updateMenu.add("Update Last Name");
        updateMenu.add("Update email");
        updateMenu.add("Update Phone Number");

        while (true) {
            updateMenu.printMenu("Exit");
            Integer choice = updateMenu.getChoice();
            switch (choice) {
                case 1:
                    String password = InputUtils.inputPassword(true, true);
                    if (MyValidations.isEmptyString(password)) {
                        System.out.println("Password unchanged!");
                    } else {
                        this.get(posID).setPassword(password);
                        System.out.println("Password changed successfully!");
                    }
                    break;
                case 2:
                    String firstName = InputUtils.inputFirstName(true);
                    if (MyValidations.isEmptyString(firstName)) {
                        System.out.println("First Name unchanged!");
                    } else {
                        this.get(posID).setFirstName(firstName);
                        System.out.println("First Name changed successfully!");
                    }
                    break;
                case 3:
                    String lastName = InputUtils.inputLastName(true);
                    if (MyValidations.isEmptyString(lastName)) {
                        System.out.println("Last Name unchanged!");
                    } else {
                        this.get(posID).setLastName(lastName);
                        System.out.println("Last Name changed successfully!");
                    }
                    break;
                case 4:
                    String email = InputUtils.inputEmail(true);
                    if (MyValidations.isEmptyString(email)) {
                        System.out.println("Email unchanged!");
                    } else {
                        this.get(posID).setEmail(email);
                        System.out.println("Email changed successfully!");
                    }
                    break;
                case 5:
                    String phoneNumber = InputUtils.inputPhoneNumber(true);
                    if (MyValidations.isEmptyString(phoneNumber)) {
                        System.out.println("Phone Number unchanged!");
                    } else {
                        this.get(posID).setPhoneNumber(phoneNumber);
                        System.out.println("Phone Number changed successfully!");
                    }
                    break;
                default:
                    System.out.println("Update Exited!");
                    return;

            }
        }
    }

    private boolean delete(Integer posID) {

        Boolean confirm = MyValidations.chooseYN("Do you want to delete this user?");
        if (!confirm) {
            System.out.println("User Remove Cancelled");
            return false;
        }
        this.remove(this.get(posID));
        System.out.println("User Remove Successfully!");
        return true;
    }

    public void checkExistedUser() {

        // if (loadFromFile() == null) {
        //   System.out.println("File Read Error");
        //  return;
        //   }
        // if (loadFromFile().isEmpty()) {
        //    System.out.println("Empty File!");
        //    return;
        //   }
        if (isEmpty()) {
            System.out.println("This List Is Empty");
            return;
        }
        while (true) {
            String username = InputUtils.inputUsername(false);

            if (this.search(username) == -1) {
                System.out.println("Not Found!");
            } else {
                System.out.println("Found!");
            }
            boolean isContinue = MyValidations.chooseYN("Do you want to continue?");
            if (!isContinue) {
                return;
            }
        }
    }

    public void saveFile() {
        writeUser(this);
        System.out.println("Saved");
    }

    public void printFromList() {
        //if (loadFromFile() == null) {
        //  System.out.println("File Read Error");
        //   return;
        //  }

        //  if (loadFromFile().isEmpty()) {
        //      System.out.println("Empty File!");
        //      return;
        //   }
        if (isEmpty()) {
            System.out.println("This List Is Empty");
            return;
        }
        Collections.sort(this);
        PrintUtils.printUserList(this);

    }

    public void findNameFromFile() {

        //  if (loadFromFile() == null) {
        //     System.out.println("File Read Error");
        //      return;
        //   }
        //  if (loadFromFile().isEmpty()) {
        //        System.out.println("Empty File!");
        //       return;
        //    }
        if (isEmpty()) {
            System.out.println("This List Is Empty");
            return;
        }
        while (true) {
            String nameToSearch = InputUtils.inputName(false).toUpperCase().trim();

            Integer count = 0;

            for (User user : this) {
                if (user.getName().toUpperCase().contains(nameToSearch)) {
                    PrintUtils.printUserInfo(user);
                    ++count;
                }
            }

            if (count == 0) {
                System.out.println("No User Found!");
            }

            boolean isContinue = MyValidations.chooseYN("Do you want to continue?");
            if (!isContinue) {
                return;
            }
        }

    }

}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.ArrayList;
import java.util.Collections;
import DTO.User;
import UTILS.MyValidations;
import UTILS.Menu;
import UTILS.InputUtils;
import UTILS.PrintUtils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.File;

/**
 *
 * @author HP
 */
public class UserList extends ArrayList<User> {

    private static final String filename = System.getProperty("user.dir") + "/users.txt";

    public static UserList loadFromFile() {
        String s;
        FileReader f = null;
        BufferedReader rf = null;
        UserList list = new UserList();
        File f1 = new File(filename);
        if (!f1.exists()) {
            try {
                f1.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            f = new FileReader(filename);
            rf = new BufferedReader(f);

            while ((s = rf.readLine()) != null) {

                String[] arr = s.split("[;]");

                User temp = new User(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5]);
                list.add(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("The file is not existed! Please save the data to file or create \"users.txt\" in project folder!");
        } finally {
            try {
                if (f != null) {
                    f.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    // save to file
    public static void writeUser(ArrayList<User> list) {
        // check null list
        File file = new File(filename);
        if (list == null || list.isEmpty()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        PrintWriter w = null;

        try {

            w = new PrintWriter(file);
            for (User user : list) {
                w.println(user.toString());
                w.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("The file is not existed! Please save the data to file or create \"users.txt\" in project folder!");
        } finally {
            try {
                if (w != null) {
                    w.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private UserList() {

    }

    public Integer search(String username) {
        return this.indexOf(new User(username));
    }

    public void add() {

        while (true) {
            String username;
            String password;
            String firstName;
            String lastName;
            String email;
            String phoneNumber;
            while (true) {
                username = InputUtils.inputUsername(false);
                if (search(username) == -1) {
                    break;
                }
                System.out.println("User Existed!");
            }
            password = InputUtils.inputPassword(false, true);
            firstName = InputUtils.inputFirstName(false);
            lastName = InputUtils.inputLastName(false);
            email = InputUtils.inputEmail(false);
            phoneNumber = InputUtils.inputPhoneNumber(false);

            User currentUser = new User(username, password, phoneNumber, email, firstName, lastName);

            System.out.println("Current Student Info:");
            PrintUtils.printUserInfo(currentUser);

            boolean isUserConfirm = MyValidations.chooseYN("Confirm: Add This User?");

            if (isUserConfirm) {
                this.add(currentUser);
            } else {
                System.out.println("User NOT Added");
            }

            if (!MyValidations.chooseYN("Do you want to continue adding user")) {
                System.out.println("Exited!");
                return;
            }

        }
    }

    public void updateMenu() {
        Integer posID;

        if (this.isEmpty()) {
            System.out.println("The List is Empty!");
            return;
        }

        if ((posID = InputUtils.loginUser(this)) == -1) {
            return;
        }

        Menu menu = new Menu("Update and Delete Menu");

        menu.addItems("Update User Info");
        menu.addItems("Delete User");

        while (true) {
            menu.printMenu("Exit");
            Integer choice = menu.getChoice();

            switch (choice) {
                case 1:
                    update(posID);
                    break;
                case 2:
                    if (delete(posID)) {
                        return;
                    }
                    break;

                default:
                    System.out.println("Menu Exited!");
                    return;
            }
        }

    }

    private void update(Integer posID) {

        Menu updateMenu = new Menu("Update memu");
        updateMenu.add("Update password");
        updateMenu.add("Update First Name");
        updateMenu.add("Update Last Name");
        updateMenu.add("Update email");
        updateMenu.add("Update Phone Number");

        while (true) {
            updateMenu.printMenu("Exit");
            Integer choice = updateMenu.getChoice();
            switch (choice) {
                case 1:
                    String password = InputUtils.inputPassword(true, true);
                    if (MyValidations.isEmptyString(password)) {
                        System.out.println("Password unchanged!");
                    } else {
                        this.get(posID).setPassword(password);
                        System.out.println("Password changed successfully!");
                    }
                    break;
                case 2:
                    String firstName = InputUtils.inputFirstName(true);
                    if (MyValidations.isEmptyString(firstName)) {
                        System.out.println("First Name unchanged!");
                    } else {
                        this.get(posID).setFirstName(firstName);
                        System.out.println("First Name changed successfully!");
                    }
                    break;
                case 3:
                    String lastName = InputUtils.inputLastName(true);
                    if (MyValidations.isEmptyString(lastName)) {
                        System.out.println("Last Name unchanged!");
                    } else {
                        this.get(posID).setLastName(lastName);
                        System.out.println("Last Name changed successfully!");
                    }
                    break;
                case 4:
                    String email = InputUtils.inputEmail(true);
                    if (MyValidations.isEmptyString(email)) {
                        System.out.println("Email unchanged!");
                    } else {
                        this.get(posID).setEmail(email);
                        System.out.println("Email changed successfully!");
                    }
                    break;
                case 5:
                    String phoneNumber = InputUtils.inputPhoneNumber(true);
                    if (MyValidations.isEmptyString(phoneNumber)) {
                        System.out.println("Phone Number unchanged!");
                    } else {
                        this.get(posID).setPhoneNumber(phoneNumber);
                        System.out.println("Phone Number changed successfully!");
                    }
                    break;
                default:
                    System.out.println("Update Exited!");
                    return;

            }
        }
    }

    private boolean delete(Integer posID) {

        Boolean confirm = MyValidations.chooseYN("Do you want to delete this user?");
        if (!confirm) {
            System.out.println("User Remove Cancelled");
            return false;
        }
        this.remove(this.get(posID));
        System.out.println("User Remove Successfully!");
        return true;
    }

    public void checkExistedUser() {

        // if (loadFromFile() == null) {
        //   System.out.println("File Read Error");
        //  return;
        //   }
        // if (loadFromFile().isEmpty()) {
        //    System.out.println("Empty File!");
        //    return;
        //   }
        if (isEmpty()) {
            System.out.println("This List Is Empty");
            return;
        }
        while (true) {
            String username = InputUtils.inputUsername(false);

            if (this.search(username) == -1) {
                System.out.println("Not Found!");
            } else {
                System.out.println("Found!");
            }
            boolean isContinue = MyValidations.chooseYN("Do you want to continue?");
            if (!isContinue) {
                return;
            }
        }
    }

    public void saveFile() {
        writeUser(this);
        System.out.println("Saved");
    }

    public void printFromList() {
        //if (loadFromFile() == null) {
        //  System.out.println("File Read Error");
        //   return;
        //  }

        //  if (loadFromFile().isEmpty()) {
        //      System.out.println("Empty File!");
        //      return;
        //   }
        if (isEmpty()) {
            System.out.println("This List Is Empty");
            return;
        }
        Collections.sort(this);
        PrintUtils.printUserList(this);

    }

    public void findNameFromFile() {

        //  if (loadFromFile() == null) {
        //     System.out.println("File Read Error");
        //      return;
        //   }
        //  if (loadFromFile().isEmpty()) {
        //        System.out.println("Empty File!");
        //       return;
        //    }
        if (isEmpty()) {
            System.out.println("This List Is Empty");
            return;
        }
        while (true) {
            String nameToSearch = InputUtils.inputName(false).toUpperCase().trim();

            Integer count = 0;

            for (User user : this) {
                if (user.getName().toUpperCase().contains(nameToSearch)) {
                    PrintUtils.printUserInfo(user);
                    ++count;
                }
            }

            if (count == 0) {
                System.out.println("No User Found!");
            }

            boolean isContinue = MyValidations.chooseYN("Do you want to continue?");
            if (!isContinue) {
                return;
            }
        }

    }

}
