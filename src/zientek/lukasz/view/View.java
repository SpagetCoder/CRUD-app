package zientek.lukasz.view;

import java.util.List;
import zientek.lukasz.model.City;
import zientek.lukasz.model.HighSchool;

/**
 * View class responsible for viewing output
 * @author Lukasz Zientek
 * @version 1.0.0
 */
public class View 
{   
    /**
     * Method used to print list of high schools
     * @param hs High schools to print
     */
    public void printHighSchool(List <HighSchool> hs)
    {
        if(hs.size() == 0)
            System.out.println("none");
        else
            System.out.println(hs);
    }
    
    /**
     * Method used to print high shcool with specific id 
     * @param hs High school to print
     */
    public void printHighSchoolId(HighSchool hs)
    {
        System.out.println(hs);
    }
    
    /**
     * Method used to print list of cities
     * @param city Cites to print 
     */
    public void printCity(List <City> city)
    {
        if(city.size() == 0)
            System.out.println("none");
        else
            System.out.println(city);
    }
    
    /**
     * Method used to print city with specific id 
     * @param city City to print
     */
    public void printCityId(City city)
    {
        System.out.println(city);
    }
    
    /**
     * Method used to display message 
     * @param message Message to display
     */
    public void print(String message)
    {
        System.out.println(message);
    }     
}
