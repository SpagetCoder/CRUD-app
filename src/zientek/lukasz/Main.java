/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zientek.lukasz;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import zientek.lukasz.controller.Controller;
import zientek.lukasz.database.Database;

/**
 *
 * @author Luke
 */
public class Main 
{
    
    public static void main(String[] args) 
    {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LabPU");        
        Database db = new Database(emf.createEntityManager());
        
        Controller controller = new Controller(db);
        controller.mainMenu();
        
        db.clear();
        emf.close();
                   
    }
}
