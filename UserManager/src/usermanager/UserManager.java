/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserManager;
import DAO.UserList;
import UTILS.Menu;
/**
 *
 * @author HP
 */
public class UserManager {
    
    public static void main( String[] args )
    {
        UserList mainList  = UserList.loadFromFile();
        Menu menu = new Menu("User Manager");
        
        menu.addItems("Create New User");
        menu.addItems("Check Exist User");
        menu.addItems("Search User By Name");
        menu.addItems("Update Or Delete User");
        menu.addItems("Save all Accounts To List");
        menu.addItems("Print List user From List");

        while(true){
            menu.printMenu("Exit");
            Integer userChoice = menu.getChoice();
            switch(userChoice){
                case 1:
                    mainList.add();
                    break;
                case 2:
                    mainList.checkExistedUser();
                    break;
                case 3:
                    mainList.findNameFromFile();
                    break;
                case 4:
                    mainList.updateMenu();
                    break;
                case 5:
                    mainList.saveFile();
                    break;
                case 6:
                    mainList.printFromList();
                    break;
                default:
                    System.out.println("Exiting...");
                    return;

            }
        }
    }
}
