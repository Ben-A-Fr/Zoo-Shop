package models;

public class User
{
    //Attributes
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    
    //Properties - Getters
    public String getUsername()
    {
        return username;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public String getFirstName()
    {
        return firstName;
    }
    
    public String getLastName()
    {
        return lastName;
    }
    
    //Properties - Setters
    public void setUsername(String usernameIn)
    {
        username = usernameIn;
    }
    
    public void setPassword(String passwordIn)
    {
        password = passwordIn;
    }
    
    public void setFirstName(String firstNameIn)
    {
        firstName = firstNameIn;
    }
    
    public void setLastName(String lastNameIn)
    {
        lastName = lastNameIn;
    }
    
    //Constructors
    public User() // 0 Parameters
    {
        username = "";
        password = "";
        firstName = "";
        lastName = "";
    }
    
    public User
    (String usernameIn, String passwordIn, String firstNameIn, String lastNameIn) // 4 Parameters
    {
        username = usernameIn;
        password = passwordIn;
        firstName = firstNameIn;
        lastName = lastNameIn;
    }
}
