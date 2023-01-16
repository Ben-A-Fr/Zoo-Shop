//Package:
package models;

import java.util.HashMap;
import java.util.Map;


public class Customer extends User
{
    //Attributes:
    private String addressLine1;
    private String addressLine2;
    private String town;
    private String postcode;
    private boolean isRegistered;
    private HashMap<Integer, Order> orders;
    
    //Properties - Getters
    public String getAddressLine1()
    {
        return addressLine1;
    }
    
    public String getAddressLine2()
    {
        return addressLine2;
    }
    
    public String getTown()
    {
        return town;
    }
    
    public String getPostcode()
    {
        return postcode;
    }
    
    public boolean getIsRegistered()
    {
        return isRegistered;
    }
    
    public HashMap<Integer, Order> getOrders()
    {
        return orders;
    }
    
    //Properties - Setters
    public void setAddressLine1(String addressLine1In)
    {
        addressLine1 = addressLine1In;
    }
    
    public void setAddressLine2(String addressLine2In)
    {
        addressLine2 = addressLine2In;
    }
    
    public void setTown(String townIn)
    {
        town = townIn;
    }
    
    public void setPostcode(String postcodeIn)
    {
        postcode = postcodeIn;
    }
    
    public void setIsRegistered(boolean isRegisteredIn)
    {
        isRegistered = isRegisteredIn;
    }
    
    public void setOrders(HashMap<Integer, Order> ordersIn)
    {
        orders = ordersIn;
    }
    
    //Constructors
    public Customer()
    {
        super();
        
        addressLine1 = "";
        addressLine2 = "";
        town = "";
        postcode = "";
        isRegistered = false;
        orders = new HashMap();
    }
    
    public Customer
    (String usernameIn, String passwordIn, String firstNameIn, String lastNameIn,
            String addressLine1In, String addressLine2In, String townIn, String postcodeIn)
    {
        super(usernameIn, passwordIn, firstNameIn, lastNameIn);
        
        addressLine1 = addressLine1In;
        addressLine2 = addressLine2In;
        town = townIn;
        postcode = postcodeIn;
        isRegistered = true;
        orders = new HashMap();
    }
    
    public Customer
    (String usernameIn, String passwordIn, String firstNameIn, String lastNameIn,
            String addressLine1In, String addressLine2In, String townIn,
            String postcodeIn, boolean isRegisteredIn)
    {
        super(usernameIn, passwordIn, firstNameIn, lastNameIn);
        
        addressLine1 = addressLine1In;
        addressLine2 = addressLine2In;
        town = townIn;
        postcode = postcodeIn;
        isRegistered = isRegisteredIn;
        orders = new HashMap();
    }
    
    //Order Methods
    public void addOrder(Order o)
    {
        DbManager db = new DbManager();
        int orderId = db.addOrder(o, this.getUsername());//Add order to database returning the added orders ID

        o.setOrderId(orderId);       
        orders.put(orderId, o);
    }
    
    public Order findLatestOrder()
    {
        Order currentOrder = new Order();
        
        if(orders.isEmpty())//No other orders made
        {
            addOrder(currentOrder);
        }
        
        else
        {
            currentOrder = orders.entrySet().iterator().next().getValue();
            
            for(Map.Entry<Integer, Order> orderEntry : orders.entrySet())
            {
                Order order = orderEntry.getValue();
                
                if(order.getOrderDate().after(currentOrder.getOrderDate()))
                {
                    currentOrder = order;
                }
            }
            
            if(currentOrder.getStatus().equals("Complete"))
            {
                currentOrder = new Order();
                addOrder(currentOrder);
            }          
        }
        
        return currentOrder;       
    }
}
