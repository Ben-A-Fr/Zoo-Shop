//Package:
package models;

//Imports:
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;


public class Order
{
    
    //Attributes:
    private int orderId;
    private Date orderDate;
    private int orderQuantity;
    private double orderTotal;
    private String status;
    private HashMap<Integer, OrderLine> orderLines;
    
    
    //Properties - Getters
    public int getOrderId()
    {
        return orderId;
    }
    
    public Date getOrderDate()
    {
        return orderDate;
    }
    
    public int getOrderQuantity()
    {
        return orderQuantity;
    }
    
    public double getOrderTotal()
    {
        return orderTotal;
    }
    
    public String getStatus()
    {
        return status;
    }
    
    public HashMap<Integer, OrderLine> getOrderLines()
    {
        return orderLines;
    }
    
    //Properties - Setters
    public void setOrderId(int orderIdIn)
    {
        orderId = orderIdIn;
    }
    
    public void setOrderDate(Date orderDateIn)
    {
        orderDate = orderDateIn;
    }
    
    public void setOrderQuantity(int orderQuantityIn)
    {
        orderQuantity = orderQuantityIn;
    }
    
    public void setOrderTotal(double orderTotalIn)
    {
        orderTotal = orderTotalIn;
    }
    
    public void setStatus(String statusIn)
    {
        status = statusIn;
    }
    
    public void setOrderLines(HashMap<Integer, OrderLine> orderLinesIn)
    {
        orderLines = orderLinesIn;
    }
    
    
    //Constructors
    public Order()
    {
        orderId = 0;
        orderDate = new Date();
        orderTotal = 0;
        status = "In Progress";
        orderLines = new HashMap();
    }
    
    public Order(int orderIdIn, Date orderDateIn, double orderTotalIn, String statusIn)
    {
        orderId = orderIdIn;
        orderDate = orderDateIn;
        orderTotal = orderTotalIn;
        status = statusIn;
        orderLines = new HashMap();        
    }
    
    
    //Methods & Functions:
    public void completeOrder()
    {
        status = "Complete";//Make status setter Complete for use in the completeOrder() method in DbManager
        
        DbManager db = new DbManager();
        db.completeOrder(orderId);//Mark order with following ID as complete
        
        for(Map.Entry<Integer, OrderLine> olEntry : orderLines.entrySet())
        {
            OrderLine ol = olEntry.getValue();
            
            db.updateProductAvailability//Run method to update availability of purchased product(s)
            ((ol.getProduct().getProductId()), (ol.getProduct().getStockLevel()), ol.getQuantity());
        }
    }
    
    public void removeOrderLine(int productId)
    {
        Iterator<Map.Entry<Integer, OrderLine>> iter = orderLines.entrySet().iterator();
        
        while(iter.hasNext())
        {
            Map.Entry<Integer, OrderLine> olEntry = iter.next();
            OrderLine ol = olEntry.getValue();
            
            if(ol.getProduct().getProductId() == productId)//Delete the order line with the matching ID
            {
                iter.remove();
                
                DbManager db = new DbManager();
                db.deleteOrderLine(ol.getOrderLineId());
                
                calculateOrderTotal();//Recalculate the order total
            }
        }
    }
    
    public Optional<OrderLine> findProductInBasket(int productId)
    {
        Optional<OrderLine> orderLineWithProduct = Optional.empty();
        
        for(Map.Entry<Integer, OrderLine> olEntry : orderLines.entrySet())
        {
            OrderLine orderLine = olEntry.getValue();
            
            if(orderLine.getProduct().getProductId() == productId)//If a match is found on product being searched for
            {
               orderLineWithProduct =  Optional.of(orderLine);
            }
        }
        
        return orderLineWithProduct;
    }
    
    public void addOrderLine(OrderLine ol)
    {
        DbManager db = new DbManager();
        int orderLineId = db.addOrderLine(ol, orderId);//Add order line to db

        ol.setOrderLineId(orderLineId);//Set order line ID after one has been created in the addOrderLine method previously
        orderLines.put(orderLineId, ol);
        
        calculateOrderTotal();
    }      
      
    public void calculateOrderTotal()//Calculates the total price of an order
    {
        orderTotal = 0;//Initialise order total
        
        for(Map.Entry<Integer, OrderLine> olEntry : orderLines.entrySet())
        {
            OrderLine orderLine = olEntry.getValue();
            orderTotal = orderTotal + orderLine.getLineTotal();
        }
        
        DbManager db = new DbManager();
        db.updateOrderTotal(orderId, orderTotal);
    }
    
}
