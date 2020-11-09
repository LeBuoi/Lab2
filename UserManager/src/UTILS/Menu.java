/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UTILS;

import java.util.Vector;
import java.util.Scanner;
/**
 *
 * @author HP
 */
public class Menu extends Vector<String> {
    public Menu(){
        super();
    }
     public int getUserChoice(){
        int choice = 0;
        Scanner sc= new Scanner(System.in);
        boolean valid;
        do{
            System.out.print("Choose: ");
            valid=true;
            try{
                choice= Integer.parseInt(sc.nextLine());
            }catch(NumberFormatException e){
                valid= false;
            }
            if(choice<0) valid= false;
            if(!valid) System.out.print("Choose again! ");
        } while(!valid);
        return choice;
    }
}
