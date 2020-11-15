/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UTILS;

import DAO.UserList;
import DTO.User;

/**
 *
 * @author HP
 */
public interface PrintUtils {
     public static void printUserInfo(User user){
        System.out.println("------------------------------------------------------------------------");
        System.out.println(String.format("Username: %s",user.getUsername()));
        System.out.println(String.format("Password: Hidden"));
        System.out.println(String.format("Name: %s",user.getName()));        
        System.out.println(String.format("Email: %s",user.getEmail()));
        System.out.println(String.format("Phone Number: %s",user.getPhoneNumber()));
        System.out.println("------------------------------------------------------------------------");
    }

    public static void printUserList(UserList list){
        if(list.isEmpty()){
            System.out.println("Empty List!");
        }

        System.out.println("User List In List");
        System.out.println(String.format("| %-20s | %-64s | %-40s | %-20s | %-20s","Username","Password","Name","Email","Phone number"));
        for(User user : list){
            System.out.println(String.format("| %-20s | %-64s | %-40s | %-20s | %-20s",user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getPhoneNumber()));
        }
    }
}
