public class Neptune extends Currency
{
    //setting exchange fee for neptune, which is 5 neptune nuggets
    private static final double FEE = 5;

    //constructor (using super) in order to use currency parameters when creating new Neptune objects 
    //flexible in asking for totalFunds parameter, but setting currencyName automatically to NeptuneNuggets
    public Neptune(double totalFunds) 
    {
    super(totalFunds, "NeptuneNuggets");
    }

    //overriding abstract conversion methods with neptune conversion rates, and returning the new amount
    @Override
    public double toEarthDollars(double amount) 
    {
        return amount / EARTH_TO_NEPTUNE;
    }

    @Override
    //added a paremeter in order to identify the target currency and convert to it
    public double fromEarthDollars(double earthDollars, Currency other) 
    { 
        //determine the correct conversion rate based on the target currency
        if (other instanceof Mars) 
        {
            return earthDollars * EARTH_TO_MARS;
        } 
        else if (other instanceof Saturn) 
        {
            return earthDollars * EARTH_TO_SATURN;
        } 
        else
        {
            //set as default, converting it back to original if no arg is given
            return earthDollars * EARTH_TO_NEPTUNE;
        }
    }
    
   //exchange method, pertaining to neptune
   @Override
   public void exchange(Currency other, double amount) 
   {

       //declare variables to be used: calculated exchange fee, earth dollar amount, target planet amount, and total cost for the calling planet
       double exchangeFee, totalCost, earthAmount, targetAmount;

       //calculating and converting
       exchangeFee = FEE;                             //exchange fee is how much the calling planet has to pay when transacting - formula is changed to just the fee in this case because it is a set number, not a percentage which needs to be multiplied by the amount
       totalCost = exchangeFee + amount;              //cost for the calling planet
       earthAmount = toEarthDollars(amount);          //earth dollar amount

       //if statement to catch if the calling planet doesn't have sufficient funds to make the transaction
       if (totalCost <= 0 || totalCost > totalFunds) 
       {
           System.out.printf("Uh oh - Neptune only has an available balance of %.2f, which is less than %.2f!\n", totalFunds, totalCost);
           System.out.println("");             //Just to add an extra line of space for organization
       }
       else
       {
            targetAmount = other.fromEarthDollars(earthAmount, other);      //how much the target planet will receive
            other.totalFunds += targetAmount;                               //adding this amount to the funds of the other planet 
            totalFunds -= totalCost;                                        //subtracting cost from the calling planet's funds
 
            //output that will be printed when exchange method is called
            System.out.printf("Converting from NeptuneNuggets to %s and initiating transfer…\n", other.getCurrencyName());
            System.out.printf("%.2f NeptuneNuggets = %.2f EarthDollars = %.2f %s\n", amount, earthAmount, targetAmount, other.getCurrencyName());
            System.out.printf("Neptune exchange fee is %.2f NeptuneNuggets\n", exchangeFee);
            System.out.printf("Neptune has a total of %.2f NeptuneNuggets\n", totalFunds);
            System.out.printf("%s has a total of %.2f %s\n", other.getPlanetName(), other.getTotalFunds(), other.getCurrencyName());
            System.out.println("");             //Just to add an extra line of space for organization
       }
       
   }
}
