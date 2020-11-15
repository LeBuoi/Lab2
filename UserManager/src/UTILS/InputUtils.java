/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UTILS;

import DAO.UserList;
import java.util.Scanner;

/**
 *
 * @author HP
 */
public class InputUtils {
    static Scanner sc = new Scanner(System.in);

    public static String inputUsername(boolean isReturnNullAllowed) {
        while (true) {            
            sc = new Scanner(System.in);
            System.out.print("Input Username:  ");
            String username = sc.nextLine();
            boolean isNull = MyValidations.isEmptyString(username);
            boolean isUsernameValid = MyValidations.isUsernameValid(username);
            if(isReturnNullAllowed && isNull){
                return null;
            }
            if(!isNull && isUsernameValid){
                return username;
            }
            System.out.println("Username cannot be null And Must Above 5 Character And No Space");
        }
    }


    public static String inputEmail(boolean isReturnNullAllowed){
        while(true){
            sc = new Scanner(System.in);
            System.out.print("Enter Email: ");
            String email = sc.nextLine();
            boolean isNull = MyValidations.isEmptyString(email);
            boolean isEmailValid = MyValidations.isEmailValid(email);
            if(isReturnNullAllowed && isNull){
                return null;
            }
            if(!isNull && isEmailValid){
                return email;
            } else{
                System.out.println("Email Format isnt valid!");
            }

        }
    }

    public static String inputPassword(boolean isNullAllowed, boolean isRegister){
        while(true){
            sc = new Scanner(System.in);
            System.out.print("Input password: ");
            String password = sc.nextLine();
            boolean isNull = MyValidations.isEmptyString(password);
            boolean isPasswordValid = MyValidations.isPasswordValid(password);
            if(isNullAllowed && isNull){
                return null;
            }
            if(!isNull && isPasswordValid && isRegister){
                if(confirmPassword(password)){
                    return MyValidations.sha256(password);
                }
            }
            if(!isNull && isPasswordValid){
                return MyValidations.sha256(password);
            }
            System.out.println("Password Format Incorrect! Must be above 6 and no space");
            
        }
    }

    public static boolean confirmPassword(String srcPassword){
        while(true){
            sc = new Scanner(System.in);
            System.out.print("Enter the typed password: ");
            String conPassword = sc.nextLine();          
            if(conPassword.equals(srcPassword)){
                return true;
            } else{
                System.out.println("Try Again!");
            }
            
        }
    }

    public static String inputPhoneNumber(boolean isNullAllowed){
        while(true){
            sc = new Scanner(System.in);
            System.out.print("Enter phone number:  ");
            String phoneNumber = sc.nextLine();
            boolean isNull = MyValidations.isEmptyString(phoneNumber);
            boolean isPhoneNumberValid = MyValidations.isPhoneNumberValid(phoneNumber);
            if(isNullAllowed && isNull){
                return null;
            }
            if(!isNull && isPhoneNumberValid){
                return phoneNumber;
            }
            System.out.println("Phone Number Format Invalid!");
        }
    }


    public static String inputLastName(boolean isNullAllowed){
        while(true){
            sc = new Scanner(System.in);
            System.out.print("Enter Last Name:  ");
            String lastName = sc.nextLine();
            boolean isNull = MyValidations.isEmptyString(lastName);
            if(isNull && isNullAllowed){
                return null;
            }
            if(isNull){
                System.out.println("Last Name cannot be empty!");
            }
            else if(!MyValidations.isName(lastName))
            {
                System.out.println("String must be all Character");
            } else 
            {
                return lastName;
        }
    }
    }

    public static String inputFirstName(boolean isNullAllowed){
        while(true){
            sc = new Scanner(System.in);
            System.out.print("Enter First Name:  ");
            String firstName = sc.nextLine();
            boolean isNull = MyValidations.isEmptyString(firstName);
            if(isNull && isNullAllowed){
                return null;
            }
            if(isNull){
                System.out.println("First Name cannot be empty!");
            }
            else if(!MyValidations.isName(firstName))
            {
                System.out.println("String must be all Character");
            } else 
            {
                return firstName;
        }
        }
    }

    public static String inputName(boolean isNullAllowed){
        while(true){
            sc = new Scanner(System.in);
            System.out.print("Enter Portion of a Name:  ");
            String name = sc.nextLine();
            boolean isNull = MyValidations.isEmptyString(name);
            if(isNull && isNullAllowed){
                return null;
            }
            if(!isNull){
                return name;
            }
            System.out.println("Name cannot be empty!");
        }
    }

    public static Integer loginUser(UserList list){

        sc = new Scanner(System.in);
        System.out.println("Input Username And Password To Perform Update Or Delete Action");
        String username = inputUsername(false);
        String password = inputPassword(false,false);
        Integer posId = list.search(username);
        if(posId == -1){
            System.out.println("No Username Exist Or Incorrect Password!");
            return -1;
        }
        
        if(password.equals(list.get(posId).getPassword())){
            return posId;
        } else{
            System.out.println("No Username Exist Or Incorrect Password!");
            return -1;
        }

        

    }
}
