public interface Exchangeable 
{
    //conversion rates from earth dollars to respective planet currencies, implemented as constants
    //stored as static, final? 
    static final double EARTH_TO_MARS = 1.30;
    static final double EARTH_TO_NEPTUNE = 2.00;
    static final double EARTH_TO_SATURN = 0.87;

    //outlining exchange method - implicitly abstract, so no need to specify abstract... same with public
    public void exchange(Currency other, double amount);
}

