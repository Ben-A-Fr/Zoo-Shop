//Package:
package models;

public class Clothing extends Product
{
    //Attributes
    private String measurement;
    
    //Properties - Getters
    public String getMeasurement()
    {
        return measurement;
    }
    
    //Properties - Setters
    public void setMeasurement(String measurementIn)
    {
        measurement = measurementIn;
    }
    
    //Constructors
    public Clothing
    (String productNameIn, double priceIn, int stockLevelIn, String measurementIn)
    {
        super(productNameIn, priceIn, stockLevelIn);
        
        measurement = measurementIn;
    }
    
    public Clothing
    (int productIdIn, String productNameIn, double priceIn, int stockLevelIn, String measurementIn)
    {
        super(productIdIn, productNameIn, priceIn, stockLevelIn);
        
        measurement = measurementIn;
    }
}
