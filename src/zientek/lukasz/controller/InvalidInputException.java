/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zientek.lukasz.controller;

/**
 *
 * @author Luke
 */
class InvalidInputException extends Exception 
{
    public InvalidInputException(String errorMessage) 
    {
        super(errorMessage);
    }
     
}
