public class ExchangeRatesResponse 
{
    private boolean success;
    private String base;
    private Rates rates;

    public boolean isSuccess() 
    { 
        return success; 
    }
    public void setSuccess(boolean success) 
    { 
        this.success = success; 
    }
    public String getBase() 
    { 
        return base; 
    }
    public void setBase(String base) 
    { 
        this.base = base; 
    }
    public Rates getRates() 
    { 
        return rates; 
    }
    public void setRates(Rates rates) 
    { 
        this.rates = rates; 
    }
}