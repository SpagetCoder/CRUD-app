
package zientek.lukasz;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import zientek.lukasz.controller.Controller;
import zientek.lukasz.database.Database;

/**
 * Contains main function of the program
 * @author Lukasz Zientek
 * @version 1.0.0
 */
public class Main 
{
    /**
     * Main function of the program
     * @param args Arguments given in command line.
     */
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
