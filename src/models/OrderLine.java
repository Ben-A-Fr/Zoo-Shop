//Package:
package models;


public class OrderLine
{
    //Attributes:
    private int orderLineId;
    private Product product; 
    private int quantity;
    private double lineTotal;
    
    
    //Properties - Getters
    public int getOrderLineId()
    {
        return orderLineId;
    }
    
    public Product getProduct()
    {
        return product;
    }
    
    public int getQuantity()
    {
        return quantity;
    }
    
    public double getLineTotal()
    {
        return lineTotal;
    }
    
    //Properties - Setters
    public void setOrderLineId(int orderLineIdIn)
    {
        orderLineId = orderLineIdIn;
    }
    
    public void setProduct(Product productIn)
    {
        product = productIn;
    }
    
    public void setQuantity(int quantityIn)
    {
        quantity = quantityIn;
    }
    
    public void setLineTotal(double lineTotalIn)
    {
        lineTotal = lineTotalIn;
    }
    
    
    //Constructors
    public OrderLine(int orderLineIdIn, Product productIn, int quantityIn)
    {
        orderLineId = orderLineIdIn;
        product = productIn;
        quantity = quantityIn;
        lineTotal = (product.getPrice()) * quantity;
    }
    
    public OrderLine(int orderLineIdIn, Product productIn, int quantityIn, double lineTotalIn)
    {
        orderLineId = orderLineIdIn;
        product = productIn;
        quantity = quantityIn;
        lineTotal = lineTotalIn;
    }
}
