//Package:
package models;

//Imports:
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

public class DbManager
{
    
    //Initialise drivers
    private final String driver = "net.ucanaccess.jdbc.UcanaccessDriver";
    
    //Initialise db connection path
    private final String connectionString = 
            "jdbc:ucanaccess://C:\\Users\\Ben\\Java Projects\\James\\30154302_shop\\Data\\ShopDB.accdb";
    
    
//  ==  CRUD Products   ========================================================
    
    //CREATE:
    
    /*
    The following method was commented out as it was causing null pointer reference errors when reading from the db
    */
    
//    public void addProduct(Product product)
//    {
//
//        //Initialise and set values for the extra attributes
//        String measurement = "";
//        String size = "";
//        
//        //Determine whether the product to be added is clothing or footwear
//        if(product.getClass().getName().equals("models.Clothing"))
//        {
//            Clothing clothing = (Clothing)product;
//            measurement = clothing.getMeasurement();//Set value of extra attribute to be added
//        }
//        else
//        {
//            Footwear footwear = (Footwear)product;
//            size = String.valueOf(footwear.getSize());//Set value of extra attribute to be added
//        }
//        
//        try
//        {
//            
//            //Establish connection
//            Class.forName(driver);
//            Connection conn = DriverManager.getConnection(connectionString);
//            
//            //Initialise and write statement
//            Statement stmt = conn.createStatement();
//            stmt.executeUpdate
//            ("INSERT INTO Products (ProductName, Price, StockLevel, Measurement, Size) "
//            + "VALUES ('" + product.getProductName() + "', '"
//            + product.getPrice() + "', '"
//            + product.getStockLevel() + "', '"
//            + measurement + "', '"
//            + size + "')");
//            
//            conn.close();//Close connection
//            
//            JOptionPane.showMessageDialog(null, "Product successfully added to the database!");//Confirmation msg
//            
//        }
//        
//        catch(Exception ex)//Error msg
//        {
//            
//            JOptionPane.showMessageDialog
//            (null, "ERROR: Unable to add clothing to database.\n" + ex);
//            
//        }
//        
//    }
    
    public void addClothing(Product product)
    {

        Clothing clothing = (Clothing)product;
        String measurement = clothing.getMeasurement();//Set value of extra attribute to be added
        
        try
        {
            
            //Establish connection
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            
            //Initialise and write statement
            Statement stmt = conn.createStatement();
            stmt.executeUpdate
            ("INSERT INTO Products (ProductName, Price, StockLevel, Measurement) "
            + "VALUES ('" + product.getProductName() + "', '"
            + product.getPrice() + "', '"
            + product.getStockLevel() + "', '"
            + measurement + "')");
            
            conn.close();//Close connection
            
            JOptionPane.showMessageDialog(null, "Clothing successfully added to the database!");//Confirmation msg
            
        }
        
        catch(Exception ex)//Error msg
        {
            
            JOptionPane.showMessageDialog
            (null, "ERROR: Unable to add clothing to database.\n" + ex);
            
        }
        
    }
    
    public void addFootwear(Product product)
    {

        String measurement = "";//Set to blank to avoid null pointer reference error when loading products
        
        Footwear footwear = (Footwear)product;
        int size = footwear.getSize();//Set value of extra attribute to be added
        
        try
        {
            
            //Establish connection
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            
            //Initialise and write statement
            Statement stmt = conn.createStatement();
            stmt.executeUpdate
            ("INSERT INTO Products (ProductName, Price, StockLevel, Measurement, Size) "
            + "VALUES ('" + product.getProductName() + "', '"
            + product.getPrice() + "', '"
            + product.getStockLevel() + "', '"
            + measurement + "', '"//Is blank
            + size + "')");
            
            conn.close();//Close connection
            
            JOptionPane.showMessageDialog(null, "Footwear successfully added to the database!");//Confirmation msg
            
        }
        
        catch(Exception ex)//Error msg
        {
            
            JOptionPane.showMessageDialog
            (null, "ERROR: Unable to add footwear to database.\n" + ex);
            
        }
        
    }
    
    //READ:
    public HashMap<Integer, Product> loadProducts() //Retrieve product data from db
    {
        HashMap<Integer, Product> products = new HashMap();
       
        try
        {
            //Establish connection:
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            
            //Initialise and write statement:
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Products");
           
            while(rs.next())
            {
                //Retieve data from db
                int productId = rs.getInt("ProductID");
                String productName = rs.getString("ProductName");
                double price = rs.getDouble("Price");
                int stockLevel = rs.getInt("StockLevel");
                String measurement = rs.getString("Measurement");
                int size = rs.getInt("Size");
                
                //Determine whether product is clothing or footwear:
                
                if(measurement.equals("NULL"))//Is Footwear
                {
                    Footwear fwear = new Footwear(productId, productName, price, stockLevel, size);//Make instance of Footwear object
                    
                    products.put(productId, fwear);//Store in Hash Map "products"
                }
                else if(measurement.equals(""))//Is Footwear still
                {
                    Footwear fwear = new Footwear(productId, productName, price, stockLevel, size);//Make instance of Footwear object
                    
                    products.put(productId, fwear);//Store in Hash Map "products"
                }
                else if(measurement == null)//Is Footwear still
                {
                    Footwear fwear = new Footwear(productId, productName, price, stockLevel, size);//Make instance of Footwear object
                    
                    products.put(productId, fwear);//Store in Hash Map "products"
                }
                else//Is Clothing
                {
                    Clothing clo = new Clothing(productId, productName, price, stockLevel, measurement);//Make instance of Clothing object
                    
                    products.put(productId, clo);//Store in Hash Map "products"
                }
            }
            
            conn.close();//Close connection
        }
       
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog
            (null, "ERROR: Unable to load products.\n" + ex.getMessage());
        }
        
        finally
        {
            return products;
        }
        
    }
    
    //UPDATE:
    public void editProduct(Product product)
    {
        //Initialising variables to add the appropriate attribute to database
        String measurement = "NULL"; //Clothing
        String size = "NULL"; //Footwear
        
        //Clothing or Footwear?
        if(product.getClass().getName().equals("models.Footwear"))
        {
            Footwear footwear = (Footwear)product;
            size = String.valueOf(footwear.getSize());//Set value of extra attribute to be added
        }
        else
        {
            Clothing clothing = (Clothing)product;
            measurement = clothing.getMeasurement();
        }
        
        try
        {    
            
            //Establish connection to database
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            
            //Initialise SQL Statement
            Statement stmt = conn.createStatement();
            
            //SQL Statement...
            stmt.executeUpdate // Use same formatting (quotation marks etc) as INSERT statement
                    
            //"UPDATE Products SET ProductName = 'NAME', Price = 'PRICE', StockLevel = 'STOCK', Measurement = 'measurement', Size = 'size' WHERE ProductId = 'PRODUCT_ID'
                    
            ("UPDATE Products SET ProductName = '" + product.getProductName() + "', "
                    + "Price = '" + product.getPrice() + "', "
                    + "StockLevel = '" + product.getStockLevel() + "', "
                    + "Measurement = '" + measurement + "', "   // < One must be null and the other must have a value depending on type of product
                    + "Size = " + size                          // <
                    + " WHERE ProductId = '" + product.getProductId() + "'");
            
            conn.close();//Close connection
            
            JOptionPane.showMessageDialog(null, "Product successfully updated.");//Confirmation msg
            
        }
        
        catch(Exception ex)//Error msg
        {
            JOptionPane.showMessageDialog(null,"ERROR: Could not edit product.\n" + ex);
        }
        
    }
    
    //DELETE:
    public void deleteProduct(int productId)
    {
        
        try
        {
            
            //Establish connection
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            
            //Initialise and write statement
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM Products WHERE ProductId = '" + productId + "'");
            
            conn.close();//Close connection
            
        }
        
        catch(Exception ex)//Error msg
        {
            
            JOptionPane.showMessageDialog
            (null, "ERROR: Unable to delete product from the database.\n" + ex);
            
        }
    }
    

//  ==  CRUD Customers  ========================================================
    
    //CREATE:
    public boolean registerCustomer(Customer customer)//Save a new customer to the db
    {
        
        try
        {    
            
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);//Open connection
            
            Statement stmt = conn.createStatement();//Initialise SQL statement
            
            stmt.executeUpdate//Write SQL statement
            ("INSERT INTO Customers (Username, Password, FirstName, LastName, AddressLine1, AddressLine2, Town, Postcode)"
                    + "VALUES ('" + customer.getUsername() + "', '"
                    + customer.getPassword() + "', '"
                    + customer.getFirstName() + "', '"
                    + customer.getLastName() + "', '"
                    + customer.getAddressLine1() + "', '"
                    + customer.getAddressLine2() + "', '"
                    + customer.getTown() + "', '"
                    + customer.getPostcode() + "')");
                    
            conn.close();//Close connection
            
            return true;//Return customer registration as successful
            
        }
        
        catch(Exception ex)
        {
            
            JOptionPane.showMessageDialog
            (null, "ERROR: Unable to save new customer to the database.\n" + ex);//Error msg
            
            return false;//Return customer registration as failed
            
        }
        
    }
    
    //READ:
    public HashMap<String, Customer> loadCustomers() //Retrieve customer data from db
    {
        
        HashMap<String, Customer> customers = new HashMap();
       
        try
        {
            
            //Establish connection
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            
            //Initialise and write statement
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Customers");
           
            while(rs.next())
            {
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String addressLine1 = rs.getString("AddressLine1");
                String addressLine2 = rs.getString("AddressLine2");
                String town = rs.getString("Town");
                String postcode = rs.getString("Postcode");
               
                Customer customer = new Customer(username, password, firstName, lastName, addressLine1, addressLine2, town, postcode);
               
                customers.put(username, customer);
            }
            
            conn.close();//Close connection
        }
       
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, "ERROR: Unable to load customer details from the database.\n" + ex.getMessage());
        }
        
        finally
        {
            customers = loadCustomerOrders(customers);
            customers = loadCustomerOrderLines(customers);
            
            return customers;
        }
        
    }
    
    //UPDATE:
    public void editCustomerDetails(Customer customer)
    {
        
        try
        {
            
            //Establish database connection:
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            
            //Initialise SQL statement:
            Statement stmt = conn.createStatement();
            
            //SQL Statement:
            stmt.executeUpdate("UPDATE Customers SET FirstName = '" + customer.getFirstName() + "', "
                    + "LastName = '" + customer.getLastName() + "', "
                    + "Password = '" + customer.getPassword() + "', "
                    + "AddressLine1 = '" + customer.getAddressLine1() + "', "
                    + "AddressLine2 = '" + customer.getAddressLine2() + "', "
                    + "Town = '" + customer.getTown() + "', "
                    + "Postcode = '" + customer.getPostcode() + "'"
                    + "WHERE Username = '" + customer.getUsername() + "'");

            conn.close(); //Close connection after successful update of the database
            
            JOptionPane.showMessageDialog(null, "Details successfully updated!");//Confirmation msg
            
        }
        
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog
            (null, "ERROR: Unable to update database!\n" + ex);//Error msg
        }
        
    }
    
    //DELETE:
    public void deleteCustomer(String customerUsername)
    {
        try
        {
            
            //Establish connection
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            
            //Initialise and write statement
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM Customers WHERE Username = '" + customerUsername + "'");
            
            conn.close();//Close connection
            
        }
        
        catch(Exception ex)//Error msg
        {
            JOptionPane.showMessageDialog(null, "ERROR: Unable to delete customer from database.\n" + ex);
        }
    }
 
    
//  ==  CRUD Staff  ============================================================
    
    //CREATE:
    public boolean registerStaff(Staff staff)//Save a new customer to the db
    {
        
        try
        {    
            
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);//Open connection
            
            Statement stmt = conn.createStatement();//Initialise SQL statement
            
            stmt.executeUpdate//Write SQL statement
            ("INSERT INTO Staff (Username, Password, FirstName, LastName, Position, Salary)"
                    + "VALUES ('" + staff.getUsername() + "', '"
                    + staff.getPassword() + "', '"
                    + staff.getFirstName() + "', '"
                    + staff.getLastName() + "', '"
                    + staff.getPosition() + "', '"
                    + staff.getSalary() + "')");
                    
            conn.close();//Close connection
            
            return true;//Return staff registration as successful
            
        }
        
        catch(Exception ex)//Error msg
        {
            
            JOptionPane.showMessageDialog(null, "ERROR: Unable to save new staff member to the database.\n" + ex);//Error msg
            
            return false;//Return customer registration as failed
            
        }
        
    }
    
    //READ:
    public HashMap<String, Staff> loadStaff() //Retrieve staff data from db
    {
        
        HashMap<String, Staff> staffMembers = new HashMap();
       
        try
        {
            
            //Establish connection
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            
            //Initialise statement
            Statement stmt = conn.createStatement();
            
            //Write statement
            ResultSet rs = stmt.executeQuery("SELECT * FROM Staff");
           
            while(rs.next())
            {
                //Retrieve staff data
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String position = rs.getString("Position");
                Double salary = rs.getDouble("Salary");
                
                Staff staff = new Staff(username, password, firstName, lastName, position, salary);
               
                staffMembers.put(username, staff);
            }
            
            //Close connection
            conn.close();
        }
       
        catch(Exception ex)
        {
            
            JOptionPane.showMessageDialog
            (null, "ERROR: Unable to retrieve staff details from the database.\n" + ex.getMessage());
        }
        finally
        {
            return staffMembers;
        }
    }
    
    //UPDATE:
    public void editStaffDetails(Staff staff)
    {
        
        try
        {
            
            //Establish database connection:
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            
            //Initialise SQL statement:
            Statement stmt = conn.createStatement();
            
            //SQL Statement:
            stmt.executeUpdate
            ("UPDATE Staff SET Username = '" + staff.getUsername() + "', "
                    + "Password = '" + staff.getPassword() + "', "
                    + "FirstName = '" + staff.getFirstName() + "', "
                    + "LastName = '" + staff.getLastName() + "' "
                    + "Position = '" + staff.getPosition() + "' "
                    + "Salary = '" + staff.getSalary() + "'");

            conn.close(); //Close connection after successful update of the database
            
        }
        
        catch(Exception ex)//Error msg
        {
            
            JOptionPane.showMessageDialog
            (null, "ERROR: Unable to update database!\n" + ex);
            
        }
        
    }
    
    //DELETE:
    public void deleteStaff(String staffUsername)
    {
        
        try
        {
            
            //Establish connection
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            
            //Initialise and write statement
            Statement stmt = conn.createStatement();
            stmt.executeUpdate
            ("DELETE FROM Staff WHERE Username = '" + staffUsername + "'");
            
            conn.close();//Close connection
            
        }
        
        catch(Exception ex)//Error msg
        {
            
            JOptionPane.showMessageDialog
            (null, "ERROR: Unable to delete staff member from database.\n" + ex);
        }
    }
    
    
//  ==  Logins  ================================================================
    
    //CUSTOMER:
    public Customer customerLogIn(String username, String password)
    {
        HashMap<String , Customer> customers = loadCustomers();
        
        if(customers.containsKey(username))
        {
            Customer customer = customers.get(username);
            
            if(customer.getPassword().equals(password))
            {
                return customer;
            }
            else
            {
                return null;
            }
        }
        else
        {
            return null;
        }
    }
    
    //STAFF:
    public Staff staffLogIn(String username, String password)
    {
        HashMap<String , Staff> staffMembers = loadStaff();
        
        if(staffMembers.containsKey(username))
        {
            Staff staff = staffMembers.get(username);
            
            if(staff.getPassword().equals(password))
            {
                return staff;
            }
            else
            {
                return null;
            }
        }
        else
        {
            return null;
        }
    }
    
//  ==  ORDERS  ================================================================
    
    //CREATE:
    public int addOrder(Order order, String customerUsername)
    {
        int orderId = 0;
        
        try
        {
            
            //Establish connection
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            
            //Initialise statement
            Statement stmt = conn.createStatement();
            
            //Write statement
            stmt.executeUpdate("INSERT INTO Orders (OrderDate, Username, OrderTotal, Status) "
            + "VALUES ("
            + "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(order.getOrderDate()) + "',"
            + "'" + customerUsername + "',"
            + "'" + order.getOrderTotal() + "',"
            + "'" + order.getStatus() + "')");
            
            ResultSet rs = stmt.getGeneratedKeys();
            
            if(rs.next())
            {
                orderId = rs.getInt(1);//Find order ID
            }
            
            conn.close();//Close connection
        }
        catch(Exception ex)
        {
            
            JOptionPane.showMessageDialog
            (null, "ERROR: Unable to write order to db.\n" + ex.getMessage());  
        }
        
        return orderId;
    }
    
    //UPDATE (TOTAL):
    public void updateOrderTotal(int orderId, double newTotal)
    {
        try
        {
            
            //Establish connection
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            
            //Initialise & write statement
            Statement stmt = conn.createStatement();  
            stmt.executeUpdate
            ("UPDATE Orders SET OrderTotal = '" + newTotal + "' "
                    + "WHERE OrderId = '" + orderId + "'");
            
            conn.close();//Close connection
        }
        catch(Exception ex)
        {
            
            JOptionPane.showMessageDialog(null, "ERROR: Unable to update order total to db.\n" + ex.getMessage());              
        }        
    }
    
    //UPDATE (STATUS):
    public void completeOrder(int orderId)
    {
        try
        {
            
            //Establish connection
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            
            //Initialise & write statement
            Statement stmt = conn.createStatement();  
            stmt.executeUpdate
            ("UPDATE Orders SET Status = 'Complete' "
                    + "WHERE OrderId = '" + orderId + "'");
            
            conn.close();//Close connection
        }
        catch(Exception ex)
        {
            
            JOptionPane.showMessageDialog
            (null, "ERROR: Unable to complete order in db.\n" + ex.getMessage());              
        }          
    }
    
//  ==  ORDER LINES ============================================================
    
    //CREATE:
    public int addOrderLine(OrderLine orderLine, int orderId)
    {
        int orderLineId = 0;
        
        try
        {
            
            //Establish connection
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            
            //Initialise & write SQL statement
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO OrderLines (ProductID, Quantity, LineTotal, OrderId) "
            + "VALUES ("
            + "'" + orderLine.getProduct().getProductId() + "',"
            + "'" + orderLine.getQuantity() + "',"
            + "'" + orderLine.getLineTotal() + "',"
            + "'" + orderId + "')");
            
            ResultSet rs = stmt.getGeneratedKeys();
            
            if(rs.next())
            {
                orderLineId = rs.getInt(1);
            }          
            
            conn.close();//Close connection
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog
            (null, "ERROR: Unable to write orderLine to db.\n" + ex.getMessage());  
        }
        
        return orderLineId;
    }
    
    //UPDATE:
    public void editOrderLine(OrderLine ol)
    {
        try
        {
            
            //Establish connection
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            
            //Initialise and write statement
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE OrderLines SET Quantity = '" + ol.getQuantity() + "', "
                    + "LineTotal = '" + ol.getLineTotal() + "' "
                    + "WHERE OrderLineId = '" + ol.getOrderLineId() + "'");
            
            conn.close();//Close connection
        }
        
        catch(Exception ex)
        {
            
            JOptionPane.showMessageDialog
            (null, "ERROR: Unable to update order line to db.\n" + ex.getMessage());              
        }            
    }
    
    //DELETE:
    public void deleteOrderLine(int orderLineId)
    {
        try
        {
            
            //Establish connection
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            
            //Initialise and write statement
            Statement stmt = conn.createStatement();  
            stmt.executeUpdate
            ("DELETE FROM OrderLines WHERE OrderLineId = '" + orderLineId + "'");
            
            //Close connection
            conn.close();
        }
        catch(Exception ex)
        {
            
            JOptionPane.showMessageDialog
            (null, "ERROR: Unable to delete order line from db.\n" + ex.getMessage());              
        }        
    }
    
    
//  ==  VARIOUS METHODS RELATING TO ORDERS  ====================================
    
    public HashMap<String, Customer> loadCustomerOrders(HashMap<String, Customer> customers)
    {        
        
        try
        {
            //Establish connection
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            
            //Initialise and write statement
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Orders");
            
            while(rs.next())
            {
                int orderId = rs.getInt("OrderId");
                Date orderDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("OrderDate"));
                String customerUsername = rs.getString("Username");
                double orderTotal = rs.getDouble("OrderTotal");
                String status = rs.getString("Status");
                
                //public Order(int orderIdIn, Date orderDateIn, double orderTotalIn, String statusIn)                               
                Order o = new Order(orderId, orderDate, orderTotal, status);
                
                if(customers.containsKey(customerUsername))
                {
                    Customer customer = customers.get(customerUsername);
                    customer.getOrders().put(orderId, o);
                }               
            }
            
            conn.close();//Close connection
        }
        
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog
            (null, "ERROR: Unable to load orders from db.\n" + ex.getMessage());
        }
        
        finally
        {
            return customers;
        }
        
    }
    
    public HashMap<String, Customer> loadCustomerOrderLines(HashMap<String, Customer> customers)
    {      
        
        HashMap<Integer, Product> products = loadProducts();
        
        try
        {
            
            //Establish connection
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            
            //Initialise and write statement
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM OrderLines");
            while(rs.next())
            {
                int orderLineId = rs.getInt("OrderLineId");
                int productId = rs.getInt("ProductId");
                int quantity = rs.getInt("Quantity");
                double lineTotal = rs.getDouble("LineTotal");
                int orderId = rs.getInt("OrderId");
                
                if(products.containsKey(productId))//If you find the product being searched for...
                {
                    Product product = products.get(productId);
                    
                    OrderLine ol = new OrderLine(orderLineId, product, quantity, lineTotal);
                    
                    for(Map.Entry<String, Customer> custEntry : customers.entrySet())
                    {
                        Customer cust = custEntry.getValue();
                        
                        if(cust.getOrders().containsKey(orderId))
                        {
                            Order order = cust.getOrders().get(orderId);
                            order.getOrderLines().put(orderLineId, ol);
                        }
                    }                   
                }              
            }
            
            conn.close();//Close connection
        }
        
        catch(Exception ex)//Error msg
        {
            JOptionPane.showMessageDialog
            (null, "ERROR: Unable to load orderlines from db.\n" + ex.getMessage());
        }
        
        finally
        {
            return customers;
        }
    }
    
    public void updateProductAvailability(int productId, int stockLevelIn, int quantityPurchased)
    {   
        
        try
        {
                        
            //Retrieve order stats
            int stockLevel = stockLevelIn;//Quantity of total stock
            int olQuantity = quantityPurchased;//Quantity of products ordered in total
            
            //Establish connection
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(connectionString);
            
            //Initialise and write statement
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE Products SET StockLevel = '" + (stockLevel - olQuantity) + "'"
                    + " WHERE ProductID = '" + productId + "'");
            
            conn.close();//Close connection
        }
        
        catch(Exception ex)//Error msg
        {
            JOptionPane.showMessageDialog
            (null, "ERROR: Unable to update product availability in db.\n" + ex.getMessage());              
        }             
    }
    
}