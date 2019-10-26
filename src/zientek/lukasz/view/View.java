/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zientek.lukasz.view;

import java.util.List;
import zientek.lukasz.model.City;
import zientek.lukasz.model.HighSchool;

/**
 *
 * @author Luke
 */
public class View 
{   
    public void printHighSchool(List <HighSchool> hs)
    {
        System.out.println(hs);
    }
    
    public void printHighSchoolId(HighSchool hs)
    {
        System.out.println(hs);
    }
    
    public void printCity(List <City> city)
    {
        System.out.println(city);
    }
    
    public void printCityId(City city)
    {
        System.out.println(city);
    }
    
    public void print(String message)
    {
        System.out.println(message);
    }     
}
