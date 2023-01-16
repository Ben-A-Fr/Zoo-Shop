
package models;

public class Footwear extends Product
{
    //Attributes
    private int size;
    
    //Properties - Getters
    public int getSize()
    {
        return size;
    }
    
    //Properties - Setters
    public void setSize(int sizeIn)
    {
        size = sizeIn;
    }
    
    //Constructors
    public Footwear
    (String productNameIn, double priceIn, int stockLevelIn, int sizeIn)
    {
        super(productNameIn, priceIn, stockLevelIn);
        
        size = sizeIn;
    }
    
    public Footwear
    (int productIdIn, String productNameIn, double priceIn, int stockLevelIn, int sizeIn)
    {
        super(productIdIn, productNameIn, priceIn, stockLevelIn);
        
        size = sizeIn;
    }
}
