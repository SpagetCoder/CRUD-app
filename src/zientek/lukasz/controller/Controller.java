/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author Luke
 */
public class Controller 
{
    private View view = new View();   
    private Scanner scanner = new Scanner(System.in);   
    private Database database;

    public Controller(Database database) 
    {
        this.database = database;
    }
    
    private void addHighSchool()
    {
        do
        {
            try
            {          
                HighSchool highSchool = prepareHighSchool();                                   
                database.createHighSchool(highSchool);
                view.print("High school added");         
            }
            catch(InvalidInputException ex)
            {
                view.print("Error while adding highschool");
            }
           
            view.print("Press (n) to add next high school, anything else to get back to main menu");
            
        }while("n".equalsIgnoreCase(scanner.nextLine()));      
    }
    
    private void addCity()
    {
        do
        {
            try
            {          
                City city = prepareCity();                                   
                database.createCity(city);
                view.print("City added");         
            }
            catch(InvalidInputException ex)
            {
                view.print("Error while adding city");
            }
           
            view.print("Press (n) to add next city, anything else to get back to main menu");
            
        }while("n".equalsIgnoreCase(scanner.nextLine()));      
    }
    
    private void updateHighSchool()
    {     
        do
        {
            boolean correctId = false;   
            HighSchool highSchool = new HighSchool();
                
            while(!correctId)
            {
                view.print("Provide id");
                int id = prepareId();
                highSchool = database.readHighSchool(id);
            
                if(highSchool == null)
                {
                    view.print("No such id found. Please try again");        
                }  
            
                else
                    correctId = true;
            }
           
            try
            {          
                HighSchool highSchoolUpdated = prepareHighSchool();                                   
                highSchool.setName(highSchoolUpdated.getName());
                highSchool.setFounded(highSchoolUpdated.getFounded());
                highSchool.setStutendsCount(highSchoolUpdated.getStutendsCount());
                highSchool.setPassRate(highSchoolUpdated.getPassRate());
                highSchool.setCity(highSchoolUpdated.getCity());
                database.updateHighSchool(highSchool);                            
                view.print("High school updated");         
            }
            catch(InvalidInputException ex)
            {
                view.print("Error while adding highschool");
            }  
            
            view.print("Press (n) to add update, anything else to get back to main menu");
   
        }while("n".equalsIgnoreCase(scanner.nextLine()));                               
    }
    
    private void updateCity()
    {     
        do
        {
            boolean correctId = false;   
            City city = new City();
                
            while(!correctId)
            {
                view.print("Provide id");
                int id = prepareId();
                city = database.readCity(id);
            
                if(city == null)
                {
                    view.print("No such id found. Please try again");        
                }  
            
                else
                    correctId = true;
            }
           
            try
            {          
                City cityUpdated = prepareCity();                                   
                city.setName(cityUpdated.getName());
                city.setFounded(cityUpdated.getFounded());
                city.setPopulation(cityUpdated.getPopulation());
                database.updateCity(city);                            
                view.print("City updated");         
            }
            catch(InvalidInputException ex)
            {
                view.print("Error while adding highschool");
            }  
            
            view.print("Press (n) to update again, anything else to get back to main menu");
   
        }while("n".equalsIgnoreCase(scanner.nextLine()));                               
    }
    
    private void deleteHighSchool()
    {
        do
        {
            boolean correctId = false;   
            HighSchool highSchool = new HighSchool();
                
            while(!correctId)
            {
                view.print("Provide id");
                int id = prepareId();
                highSchool = database.readHighSchool(id);
            
                if(highSchool == null)
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
    
        private void deleteCity()
    {
        do
        {
            boolean correctId = false;   
            City city = new City();
                
            while(!correctId)
            {
                view.print("Provide id");
                int id = prepareId();
                city = database.readCity(id);
            
                if(city == null)
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
          
    private int prepareId()
    {
        boolean correctId = false;
        int id = 0;
       
        while(!correctId)
        {
            try
            {
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
    
    private HighSchool prepareHighSchool() throws InvalidInputException
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
    
    private City prepareCity() throws InvalidInputException
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
       
    private void showAllHighSchools()
    {
        view.printHighSchool(database.readAllHighSchools());
    }
    
    private void showHighScholId()
    {
        boolean correctId = false;   
        HighSchool highSchool = new HighSchool();
        int id=0;
                
        while(!correctId)
        {
            view.print("Provide id");
            id = prepareId();
            highSchool = database.readHighSchool(id);
            
            if(highSchool == null)
            {
                view.print("No such id found. Please try again");        
            }  
            
            else
                correctId = true;
            }
        
        view.printHighSchoolId(database.readHighSchool(id));
    }
     
    private void showAllCities()
    {
        view.printCity(database.readAllCities());
    }
    
    private void showCityId()
    {
        boolean correctId = false;   
        City city = new City();
        int id=0;
                
        while(!correctId)
        {
            view.print("Provide id");
            id = prepareId();
            city = database.readCity(id);
            
            if(city == null)
            {
                view.print("No such id found. Please try again");        
            }  
            
            else
                correctId = true;
            }
        
        view.printCityId(database.readCity(id));
    }
    
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
    
    private void showMainMenu()
    {
        clearScreen();
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
    
    public static void clearScreen() 
    {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }  
    
}
