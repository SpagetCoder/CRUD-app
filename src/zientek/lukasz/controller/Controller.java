package zientek.lukasz.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import zientek.lukasz.database.Database;
import zientek.lukasz.model.City;
import zientek.lukasz.model.HighSchool;
import zientek.lukasz.view.View;

/**
 * Controller class responsible for taking and sending input to other components
 * @author Lukasz Zientek
 * @version 1.0.0
 */
public class Controller 
{
    /**
     * Used for displaying message to the user
     */
    private View view = new View(); 

    /**
     * Takes data from the user
     */
    private Scanner scanner = new Scanner(System.in);   
    
    /**
     * Used to interact with the database
     */
    private Database database;

    /**
     * Constractor of the controller class
     * @param database used to interact with the database
     */
    public Controller(Database database) 
    {
        this.database = database;
    }
    
    /**
     * Method for adding new high schools into database
     */
    private void addHighSchool()
    {
        do
        {
            HighSchool highSchool = prepareHighSchool();                                   
            database.createHighSchool(highSchool);
            view.print("High school added");         
            view.print("Press (n) to add next high school, anything else to get back to main menu");
            
        }while("n".equalsIgnoreCase(scanner.nextLine()));      
    }
    
     /**
     * Method for adding new cities into database
     */
    private void addCity()
    {
        do
        {  
            City city = prepareCity();                                   
            database.createCity(city);
            view.print("City added");         
            view.print("Press (n) to add next city, anything else to get back to main menu");
            
        }while("n".equalsIgnoreCase(scanner.nextLine()));      
    }
    
     /**
     * Method for updating high schools in the database
     */
    private void updateHighSchool()
    {     
        do
        {
            boolean correctId = false;   
            HighSchool highSchool = new HighSchool();
                
            while(!correctId)
            {
                int id = prepareId();
                highSchool = database.readHighSchool(id);
            
                if(id == 0)
                {
                    view.print("Leaving update menu...");
                    return;
                }
                
                else if(highSchool == null)
                {
                    view.print("No such id found. Please try again");        
                }              
                       
                else
                    correctId = true;
            }
                  
            HighSchool highSchoolUpdated = prepareHighSchool();                                   
            highSchool.setName(highSchoolUpdated.getName());
            highSchool.setFounded(highSchoolUpdated.getFounded());
            highSchool.setStutendsCount(highSchoolUpdated.getStutendsCount());
            highSchool.setPassRate(highSchoolUpdated.getPassRate());
            highSchool.setCity(highSchoolUpdated.getCity());
            database.updateHighSchool(highSchool);                            
            view.print("High school updated");         
            view.print("Press (n) to add update, anything else to get back to main menu");
   
        }while("n".equalsIgnoreCase(scanner.nextLine()));                               
    }
    
     /**
     * Method for updating cities in the database
     */
    private void updateCity()
    {     
        do
        {
            boolean correctId = false;   
            City city = new City();
                
            while(!correctId)
            {
                int id = prepareId();
                city = database.readCity(id);

                if(id == 0)
                {
                    view.print("Leaving update menu...");
                    return;
                }
                                
                else if(city == null)
                {
                    view.print("No such id found. Please try again");        
                }  
                          
                else
                    correctId = true;
            }
           
            City cityUpdated = prepareCity();                                   
            city.setName(cityUpdated.getName());
            city.setFounded(cityUpdated.getFounded());
            city.setPopulation(cityUpdated.getPopulation());
            database.updateCity(city);                            
            view.print("City updated");         
            view.print("Press (n) to update again, anything else to get back to main menu");
   
        }while("n".equalsIgnoreCase(scanner.nextLine()));                               
    }
    
    /**
     * Method for deleting high schools from the database
     */
    private void deleteHighSchool()
    {
        do
        {
            boolean correctId = false;   
            HighSchool highSchool = new HighSchool();
                
            while(!correctId)
            {
                int id = prepareId();
                highSchool = database.readHighSchool(id);

                if (id == 0)
                {
                    view.print("Leaving delete menu...");
                    return;
                }
                
                else if(highSchool == null)
                {
                    view.print("No such id found. Please try again");        
                } 
            
                else
                    correctId = true;
            }
           
            database.deleteHighSchool(highSchool);
            view.print("High school deleted");                      
            view.print("Press (n) to delete next, anything else to get back to main menu");
            
        }while("n".equalsIgnoreCase(scanner.nextLine()));
    }
    
    /**
     * Method for deleting cities form the database
     */
    private void deleteCity()
    {
        do
        {
            boolean correctId = false;   
            City city = new City();
                
            while(!correctId)
            {
                int id = prepareId();
                city = database.readCity(id);
                    
                if(id == 0)
                {
                    view.print("Leaving delete menu...");
                    return;
                }
                
                else if(city == null)
                {
                    view.print("No such id found. Please try again");        
                }
            
                else
                    correctId = true;
            }
           
            database.deleteCity(city);
            view.print("City deleted");                      
            view.print("Press (n) to delete next element, anything else to get back to main menu");
            
        }while("n".equalsIgnoreCase(scanner.nextLine()));
    }
         
    /**
     * Method used to take input form the user and check 
     * if provided input is a number
     * @return id
     */
    private int prepareId()
    {
        boolean correctId = false;
        int id = 0;
       
        while(!correctId)
        {
            try
            {
                view.print("Provide id (0 to get back to main menu)");
                id = Integer.parseInt(scanner.nextLine());
                correctId = true;
            }
            catch(NumberFormatException ex)
            {
                view.print("Cannot parse given input into number. Please try again");
                continue;
            }  
        }
        
        return id;
    }
    
    /**
     * Method used to prepare high school before adding it into database
     * @return highschool class
     */
    private HighSchool prepareHighSchool()
    {
        HighSchool highschool = new HighSchool(); 
        boolean rightInput= false;
            
        view.print("Provide name");
        highschool.setName(scanner.nextLine());
                
        rightInput = false;
        while(!rightInput)
        {
            view.print("Provide foundation date (dd.mm.yyyy)");
            try
            {
                 highschool.setFounded(new SimpleDateFormat("dd.mm.yyyy").parse(scanner.nextLine()));
                 
                 if(highschool.getFounded().after(new Date()))
                 {
                     view.print("Foundation date cannot be in the future. Please try again");
                     continue;
                 }
                     
                  rightInput = true;
            }
            catch(ParseException ex)
            {    
                view.print("Couldn't parse given input into date. Please try again");
                continue;           
            }                 
        } 
        
        rightInput = false;
        while(!rightInput)
        {
            view.print("Provide students number");
            try
            {
                highschool.setStutendsCount(Long.parseLong(scanner.nextLine()));
                
               if(highschool.getStutendsCount() < 0)
               {
                   view.print("Number of studnets cannot be negative. Please try again");
                   continue;
               }
                             
                rightInput = true;
            }
            catch(IllegalArgumentException ex)
            {    
                view.print("Couldn't parse given input into number. Please try again");
                continue;         
            }  
        }  
        
        rightInput = false;
        while(!rightInput)
        {
            view.print("Provide pass rate (without % sign)");
            try
            {                
               highschool.setPassRate(Double.parseDouble(scanner.nextLine()));
               
               if(highschool.getPassRate() > 100 || highschool.getPassRate() < 0)
               {
                   view.print("Pass rate cannot be less than 0 or more than 100");
                   continue;
               }
               
               rightInput = true;
            }
             
            catch(IllegalArgumentException ex)
            {               
                view.print("Couldn't parse given input into number. Please try again");
                continue;
            }  
        }
        
        rightInput = false;       
        while(!rightInput)
        {
            view.print("Provide City id (leave empty if u dont know the id)");            
            try
            {        
               String input = scanner.nextLine();
                
               if(!input.isEmpty())
               {
                   int id = Integer.parseInt(input);
                   City city = new City();
                   city = database.readCity(id);
                   
                    if(city == null)
                    {
                        view.print("No such id found. Please try again");  
                        continue;
                    }     

                    else
                        highschool.setCity(database.readCity(id));
               }
                    
               rightInput = true;
            }
             
            catch(IllegalArgumentException ex)
            {               
                view.print("Couldn't parse given input into number. Please try again");
                continue;
            }  
        }
            
       return highschool;   
    }
    
    /**
     * Method used to prepare city class before adding it into database 
     * @return city class
     * @throws InvalidInputException 
     */
    private City prepareCity()
    {
        City city = new City(); 
        boolean rightInput= false;
            
        view.print("Provide name");
        city.setName(scanner.nextLine());
                
        rightInput = false;
        while(!rightInput)
        {
            view.print("Provide foundation date (dd.mm.yyyy)");
            try
            {
                 city.setFounded(new SimpleDateFormat("dd.mm.yyyy").parse(scanner.nextLine()));
                 
                 if(city.getFounded().after(new Date()))
                 {
                     view.print("Foundation date cannot be in the future. Please try again");
                     continue;
                 }
                     
                  rightInput = true;
            }
            catch(ParseException ex)
            {    
                view.print("Couldn't parse given input into date. Please try again");
                continue;           
            }                 
        } 
         
        rightInput = false;
        while(!rightInput)
        {
            view.print("Provide population");
            try
            {
                city.setPopulation(Long.parseLong(scanner.nextLine()));
                
               if(city.getPopulation() < 0)
               {
                   view.print("Population cannot be negative. Please try again");
                   continue;
               }
                             
                rightInput = true;
            }
            catch(IllegalArgumentException ex)
            {    
                view.print("Couldn't parse given input into number. Please try again");
                continue;         
            }                 
        }  
                   
       return city;   
    } 
       
    /**
     * Method used to display all high schools which are in database
     */
    private void showAllHighSchools()
    {
        view.printHighSchool(database.readAllHighSchools());
    }
    
    /**
     * Method used to display high school with given id
     */
    private void showHighScholId()
    {
        boolean correctId = false;   
        HighSchool highSchool = new HighSchool();
        int id=0;
                
        while(!correctId)
        {
            id = prepareId();
            highSchool = database.readHighSchool(id);
            
            if(id == 0)
            {
                view.print("Leaving read menu...");
                return;
            }  
            
            else if(highSchool == null)
            {
                view.print("No such id found. Please try again");        
            }  
            
            else
                correctId = true;
            }
        
        view.printHighSchoolId(database.readHighSchool(id));
    }
     
    /**
     * Method used to display all cities which are in database
     */
    private void showAllCities()
    {
        view.printCity(database.readAllCities());
    }
    
    /**
     * Method used to display city with given id
     */
    private void showCityId()
    {
        boolean correctId = false;   
        City city = new City();
        int id=0;
                
        while(!correctId)
        {
            id = prepareId();
            city = database.readCity(id);
            
            if(id == 0)
            {
                view.print("Leaving read menu...");
                return;
            }  
            
            else if(city == null)
            {
                view.print("No such id found. Please try again");        
            }  
            
            else
                correctId = true;
            }
        
        view.printCityId(database.readCity(id));
    }
    
    /**
     * Method used to display cities by query
     */
    private void showCityQuery()
    {
        view.print("Provide query parameters (to skip leave empty)");
        view.print("Name: ");
        String name = scanner.nextLine();
        
        view.print("Founded on or after (dd.mm.yyy): ");
        String foundedString = scanner.nextLine();
        Date founded = null;
        if(!foundedString.isEmpty()) 
        {
            try
            {
               founded = new SimpleDateFormat("dd.MM.yyyy").parse(foundedString); 
            }
            
            catch(ParseException ex)
            {
                view.print("Couldn't parse given input into date. Leaving query...");
                return;
            }     
        }
        
        view.print("Population equal or greater than: ");
        String populationString = scanner.nextLine();
        Long population = null;
        if(!populationString.isEmpty()) 
        {
            try
            {
               population = Long.parseLong(populationString); 
               
               if(population < 0)
               {
                   System.out.println("Population cannot be negative. Leaving query...");
                   return;
               }
            }
            
            catch(NumberFormatException ex)
            {
                view.print("Couldn't parse given input into number. Leaving query...");
                return;
            }     
        }
                    
        view.printCity(database.readCitiesQuery(name.isEmpty() ? null : name, founded, population));
        
    }
    
    /**
     * Method used to display high schools by query
     */
    private void showHighSchoolQuery()
    {
        view.print("Provide query parameters (to skip leave empty)");
        view.print("Name: ");
        String name = scanner.nextLine();
        
        view.print("Founded on or after (dd.mm.yyy): ");
        String foundedString = scanner.nextLine();
        Date founded = null;
        if(!foundedString.isEmpty()) 
        {
            try
            {
               founded = new SimpleDateFormat("dd.MM.yyyy").parse(foundedString); 
            }
            
            catch(ParseException ex)
            {
                view.print("Couldn't parse given input into date. Leaving query...");
                return;
            }     
        }
        
        view.print("Number of students equal or greater than: ");
        String studentsCountString = scanner.nextLine();
        Long studentsCount = null;
        if(!studentsCountString.isEmpty()) 
        {
            try
            {
               studentsCount = Long.parseLong(studentsCountString); 
               
               if(studentsCount < 0)
               {
                   System.out.println("Population cannot be negative. Leaving query...");
                   return;
               }
            }
            
            catch(NumberFormatException ex)
            {
                view.print("Couldn't parse given input into number. Leaving query...");
                return;
            }     
        }
        
        view.print("Pass rate equal or greater than: ");
        String passRateString = scanner.nextLine();
        Double passRate = null;
        if(!passRateString.isEmpty()) 
        {
            try
            {
               passRate = Double.parseDouble(passRateString); 
               
               if(passRate < 0 || passRate > 100)
               {
                   System.out.println("Pass rate has to be in range 0-100. Leaving query...");
                   return;
               }
            }
            
            catch(NumberFormatException ex)
            {
                view.print("Couldn't parse given input into number. Leaving query...");
                return;
            }     
        }

        view.print("City id: ");
        String cityIdString = scanner.nextLine();
        Integer cityId = null;
        if(!cityIdString.isEmpty()) 
        {
            try
            {
               cityId = Integer.parseInt(cityIdString);             
            }
            
            catch(NumberFormatException ex)
            {
                view.print("Couldn't parse given input into number. Leaving query...");
                return;
            }     
        }
                    
        view.printHighSchool(database.readHighSchoolsQuery(name.isEmpty() ? null : name, founded,passRate,studentsCount,cityId));        
    }
    
    /**
     * Method which allows user to execute CRUD 
     */
    public void mainMenu()
    {      
        boolean endOfProgram = false;
        
        while(!endOfProgram)
        {    
            showMainMenu();
            String input = scanner.nextLine();
            
            switch(input)
            {
                case "1":
                    addHighSchool();
                    break;  
                case "2":
                    updateHighSchool();
                    break;
                case "3":
                    deleteHighSchool();
                    break;
                case "4":
                    addCity();
                    break;
                case "5":
                    updateCity();
                    break;
                case "6":
                    deleteCity();
                    break;
                case "7":
                    showAllHighSchools();
                    break;
                case "8":
                    showHighScholId();
                    break;
                case "9":
                    showHighSchoolQuery();
                    break;
                case "10":
                    showAllCities();
                    break;
                case "11":
                    showCityId();
                    break;
                case "12":
                    showCityQuery();
                    break;
                case "0":
                    endOfProgram = true;
                    break;                   
                default:
                    view.print("Incorrect input. Please try again");                                      
            }                               
        }
    }
    
    /**
     * Prints information about available commands
     */
    private void showMainMenu()
    {
        view.print("CRUD app menu");
        view.print("1. Add high school");
        view.print("2. Update high school");
        view.print("3. Delete high school");
        view.print("4. Add city");
        view.print("5. Update city");
        view.print("6. Delete city");
        view.print("7. Read all high schools");
        view.print("8. Read high school by id");
        view.print("9. Read high school by query");
        view.print("10. Read all cities");
        view.print("11. Read city by id");
        view.print("12. Read city by query");    
        view.print("0. Exit the program");  
    } 
}
