/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usermanager;
import DAO.UserList;
import UTILS.Menu;
import UTILS.FileManagement;
/**
 *
 * @author HP
 */
public class UserManager {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        UserList list = new UserList();
        FileManagement fm = new FileManagement();
        Menu menu = new Menu();
        menu.add("1. Create user account");
        menu.add("2. Check exist user");
        menu.add("3. Search user information by name");
        menu.add("4. Update user");
        menu.add("5. Delete user");
        menu.add("6. Save account to file");
        menu.add("7. Print list user from file");
        menu.add("8. Quit");
        menu.add("Enter your choice : ");
        int userChoice;
        do {
            System.out.println("\nUSER MANAGEMENT");
            for (Object str : menu) {
                System.out.println(str);
            }
            userChoice = menu.getUserChoice();
            switch (userChoice) {
                case 1 : {
                    list.createAccount();
                    System.out.println();
                    break;
                }
                
                case 2 : {
                    list.checkExistedUser();
                    System.out.println();
                    break;
                }
                case 3 :{
                    list.searchUser();
                    System.out.println();
                    break;
                }
                case 4 : {
                    System.out.println("    ");
                    list.updateUser();
                    System.out.println();
                    break;
                }
                case 5 :{
                    list.deleteUser();
                    System.out.println();
                    break;
                }
                case 6 : {
                    list.saveFile();
                    System.out.println();
                    break;
                }
                case 7 : {
                    list.printFile();
                    System.out.println();
                    break;
                }
                case 8 : {
                    list.saveFile();
                    System.out.println("Thank you for using our service!");
                    return;
                    
                }
                default : System.out.println("No supported. Enter choice again");
            }
        } while (userChoice > 0 && userChoice != menu.size());
    }
    }
        
    

