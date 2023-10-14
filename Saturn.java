public class Saturn extends Currency
{
    //no exchange fee on saturn, but still created a constant in case someone wants to manipulate it in the code
    private static final double FEE_PERCENT = 0.00;

    //constructor (using super) in order to use currency parameters when creating new Saturn objects 
    //flexible in asking for totalFunds parameter, but setting currencyName automatically to SaturnSilver
    public Saturn(double totalFunds) 
    {
    super(totalFunds, "SaturnSilver");
    }

    //overriding abstract conversion methods with saturn conversion rates, and returning the new amount
    @Override
    public double toEarthDollars(double amount) 
    {
        return amount / EARTH_TO_SATURN;
    }

    @Override
    public double fromEarthDollars(double earthDollars, Currency other) 
    { 
        //determine the correct conversion rate based on the target currency
        if (other instanceof Mars) 
        {
            return earthDollars * EARTH_TO_MARS;
        } 
        else if (other instanceof Neptune) 
        {
            return earthDollars * EARTH_TO_NEPTUNE;
        } 
        else
        {
            //set as default, converting it back to original if no arg is given
            return earthDollars * EARTH_TO_SATURN;
        }
    }

    //exchange method, pertaining to saturn
    @Override
    public void exchange(Currency other, double amount) 
    {

        //declare variables to be used: calculated exchange fee, earth dollar amount, target planet amount, and total cost for the calling planet
        double exchangeFee, totalCost, earthAmount, targetAmount;

        //calculating and converting
        exchangeFee = amount * FEE_PERCENT;            //exchange fee is how much the calling planet has to pay when transacting - will be zero in this case, as intended, because the exchange fee is 0.00, but the equation is kept in case someone wants to change saturn's exchange fee
        totalCost = exchangeFee + amount;              //cost for the calling planet
        earthAmount = toEarthDollars(amount);          //earth dollar amount

        //if statement to catch if the calling planet doesn't have sufficient funds to make the transaction
        if (totalCost <= 0 || totalCost > totalFunds) 
        {
            System.out.printf("Uh oh - Saturn only has an available balance of %.2f, which is less than %.2f!\n", totalFunds, totalCost);
            System.out.println("");             //Just to add an extra line of space for organization
        }
        else
        {
            targetAmount = other.fromEarthDollars(earthAmount, other);      //how much the target planet will receive
            other.totalFunds += targetAmount;                               //adding this amount to the funds of the other planet 
            totalFunds -= totalCost;                                        //subtracting cost from the calling planet's funds
    
            //output that will be printed when exchange method is called
            System.out.printf("Converting from SaturnSilver to %s and initiating transfer…\n", other.getCurrencyName());
            System.out.printf("%.2f SaturnSilver = %.2f EarthDollars = %.2f %s\n", amount, earthAmount, targetAmount, other.getCurrencyName());
            System.out.printf("Saturn exchange fee is %.2f SaturnSilver\n", exchangeFee);
            System.out.printf("Saturn has a total of %.2f SaturnSilver\n", totalFunds);
            System.out.printf("%s has a total of %.2f %s\n", other.getPlanetName(), other.getTotalFunds(), other.getCurrencyName());
            System.out.println("");             //Just to add an extra line of space for organization
        }
        
    }
}
