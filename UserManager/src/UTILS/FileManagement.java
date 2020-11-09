/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UTILS;
import DTO.User;
/**
 *
 * @author HP
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.File;

public class FileManagement {
    private static final String filename = "E:\\FALL_2020\\LAB_OOP\\UserManager\\users.txt";

    // read file to return list of user
    public static ArrayList<User> loadUser() {
        FileReader f = null;
        BufferedReader rf = null;
        ArrayList<User> list = new ArrayList<>();
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

            while (rf.ready()) {
                String s = rf.readLine();
                String[] arr = s.split(",");

                User temp = new User(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5]);
                list.add(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("The file is not existed! Please save the data to file or create \"users.txt\" in project folder!");
        } finally {
            try {
                if (f != null)
                    f.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    // save to file
    public static void writeUser(ArrayList<User> list) {
        // check null list
        if (list == null || list.isEmpty()) return;

        PrintWriter w = null;

        try {
            w = new PrintWriter(filename);
            for (User user : list) {
                w.println(user);
                w.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("The file is not existed! Please save the data to file or create \"users.txt\" in project folder!");
        } finally {
            try {
                if (w != null)
                    w.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
