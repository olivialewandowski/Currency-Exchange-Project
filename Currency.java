public abstract class Currency implements Exchangeable
{
    //declare currency name and total funds variables, which we can generalize to the characteristics of the planets
    protected String currencyName;
    protected double totalFunds;

    //outlining conversion methods
    public abstract double toEarthDollars(double amount);               //takes amount and converts to Earth dollars
    public abstract double fromEarthDollars(double EarthDollars, Currency other);       //takes Earth dollars and converts to other amount - needed to add another parameter for reasons specified later

    //exchange method implemented by exchangeable - no need to specify again

    //currency constructor - will need later when creating planet objects
    public Currency(double totalFunds, String currencyName) 
    {
        this.totalFunds = totalFunds;
        this.currencyName = currencyName;
    }

    //need getters and setters for the two parameters as well
    public double getTotalFunds()
    {
        return totalFunds;
    }

    public void setTotalFunds(double totalFunds)
    {
        this.totalFunds = totalFunds;
    }

    public String getCurrencyName()
    {
        return currencyName;
    }

    /* we will not need to set currency name...
    because it is already specified when the respective planet object is created
    and will not be changed again... at least within the realm of this program */

    //had an issue with printing the planet name of the target planet (in the exchange method), so created a method to return the name of the planet based on the type and not the memory address
    public String getPlanetName() 
    {
        if (this instanceof Mars) 
        {
            return "Mars";
        } 
        else if (this instanceof Neptune) 
        {
            return "Neptune";
        } 
        else if (this instanceof Saturn) 
        {
            return "Saturn";
        } 
        else 
        {
            return "Unknown Planet";
        }
    }
}
