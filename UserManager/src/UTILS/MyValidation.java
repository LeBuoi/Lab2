/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UTILS;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author HP
 */
public class MyValidations {
     private static final Pattern PHONE_PATTERN = Pattern.compile("^[0-9]{10}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^\\w+[A-Z0-9._%+-]?+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static final String DATE_FORMAT = "dd/MM/yyyy";

    private static Scanner sc = new Scanner(System.in);

    public static boolean isName(String name) {
        char[] chars = name.toCharArray();

        for (char c : chars) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }
    // Validate phone number through RegExp
    public static boolean isPhoneNumberValid(String number) {
        Matcher matcher = PHONE_PATTERN.matcher(number);
        return matcher.matches();
    }

    // Validate email through RegExp
    public static boolean isEmailValid(String email) {
        final Matcher matcher = EMAIL_PATTERN.matcher(email);
        final boolean isValid = matcher.find();
        return isValid;
    }

    // Validate Date through date
    public static boolean isDateValid(String date) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(date);

        } catch (final ParseException e) {
            return false;
        }
        return true;
    }

    // Check null and empty String
    public static boolean isEmptyString(String string) {
        return string == null || string.isEmpty(); 
    }

    

    // Utility for checking returning yes or no with Question dialog
    public static boolean chooseYN(String questionDialog) {
        String userChoice = null;
        boolean choice = false;
        do {
            System.out.print(questionDialog + " (Y/N): ");
            userChoice = sc.nextLine().toUpperCase();
            if (userChoice.equals("N")) {
                choice = false;
            } else if (userChoice.equals("Y")) {
                choice = true;
            } else {
                System.out.println("Must be Y or N");
            }
        } while (!"N".equals(userChoice) && !"Y".equals(userChoice));

        return choice;
    }
    
    public static boolean isPasswordValid(String password){
        if(password.length() < 6 || password.contains(" ")){
            return false;
        }
        return true;       
    }

    public static boolean isUsernameValid(String username){
        if(username.length() < 5 || username.contains(" ")){
            return false;
        }
        return true;
    }

    public static boolean isEncryptedPasswordValid(String password){
        if(password.length() != 64){
            return false;
        } 
        return true;
    }
     public static String sha256(String password) {
        try {
            // Static getInstance method is called with hashing SHA
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            //return array of byte of the password
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            // Convert array of byte digest into hex value
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                // Convert message digest into hex value
                String hex = Integer.toHexString(0xff & b);
                // Pad with leading zeros
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
            }
}

