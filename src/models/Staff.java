/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Ben
 */
public class Staff extends User
{
    //Attributes
    private String position;
    private double salary;
    
    //Properties - Getters
    public String getPosition()
    {
        return position;
    }
    
    public double getSalary()
    {
        return salary;
    }
    
    //Properties - Setters
    public void setPosition(String positionIn)
    {
        position = positionIn;
    }
    
    public void setSalary(double salaryIn)
    {
        salary = salaryIn;
    }
    
    //Constructors
    public Staff()
    {
        super();
        
        position = "";
        salary = 0.00;
    }
    
    public Staff(String usernameIn, String passwordIn, String firstNameIn, String lastNameIn, String positionIn, double salaryIn)
    {
        super(usernameIn, passwordIn, firstNameIn, lastNameIn);
        
        position = positionIn;
        salary = salaryIn;
    }
    
    //Other methods
    public String dispGreeting()
    {
        User user = new User();//UNSURE HOW TO GET LOGGED IN USER DETAILS........
        
        String greeting = "<html>Welcome, "; //+ user.firstName() + " " + user.lastName() + "!";
        
        return greeting;
    }
}
