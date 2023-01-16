//Package:
package models;

//Imports:
import java.util.HashMap;
import java.util.Map;


public class Main
{

    public static void main(String[] args)
    {
        
        DbManager db = new DbManager();
        HashMap<String, Staff> admins = db.loadStaff();
        
        for(Map.Entry<String, Staff> staffEntry : admins.entrySet())
        {
            Staff staff = staffEntry.getValue();
            System.out.println("Email:" + staff.getUsername() + 
                    ". Password:" + staff.getPassword());
        }
        
        Staff validStaff = db.staffLogIn("BenF", "staff1");
	if(validStaff != null)
	{
            System.out.println("Valid Staff Logged In");
	}
	else
	{
            System.out.println("Valid Staff NOT Logged In");
	}
        
        Staff invalidStaff = db.staffLogIn("BenF", "staff2");
	if(invalidStaff != null)
	{
            System.out.println("Invalid Staff Logged In");
	}
	else
	{
            System.out.println("Invalid Staff NOT Logged In");
	}
    } 
}
