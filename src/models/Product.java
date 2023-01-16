//Package:
package models;

public class Product
{
    //Attributes
    private int productId;
    private String productName;
    private double price;
    private int stockLevel;
    
    //Properties - Getters
    public int getProductId()
    {
        return productId;
    }
    
    public String getProductName()
    {
        return productName;
    }
    
    public double getPrice()
    {
        return price;
    }
    
    public int getStockLevel()
    {
        return stockLevel;
    }
    
    //Properties - Setters
    public void setProductId(int productIdIn)
    {
        productId = productIdIn;
    }
    
    public void setProductName(String productNameIn)
    {
        productName = productNameIn;
    }
    
    public void setPrice(double priceIn)
    {
        price = priceIn;
    }
    
    public void setStockLevel(int stockLevelIn)
    {
        stockLevel = stockLevelIn;
    }
    
    //Constructors
    public Product() // 0 Parameters
    {
        productId = 0;
        productName = "";
        price = 0.0;
        stockLevel = 0;
    }
    
    public Product(String productNameIn, double priceIn, int stockLevelIn) // 3 Parameters
    {
        productId = 0;
        productName = productNameIn;
        price = priceIn;
        stockLevel = stockLevelIn;
    }
    
    public Product
    (int productIdIn, String productNameIn, double priceIn, int stockLevelIn) // 4 Parameters
    {
        productId = productIdIn;
        productName = productNameIn;
        price = priceIn;
        stockLevel = stockLevelIn;
    }
    
    //Overridden method to display products
    @Override
    public String toString()//replaces default toString method
    {
        String display = productName + " ..... £" + price;
        return display;
    }
}