import java.util.Scanner;

public class CurrencyConverter 
{
    // Method to convert currency from one type to another
    public static double ConvertCurrency(double amount, String FromCurrency, String ToCurrency)    
    {
        // If the source currency is EUR (Euro)
        if(FromCurrency.equals("EUR"))
        {
            // Get the conversion rate from EUR to the target currency (ToCurrency)
            double rate = CurrencyConverterWithAPI.FindCurrency(ToCurrency);
            // Convert the amount based on the exchange rate and return the result
            return amount * rate;
        }
        // If the target currency is EUR (Euro)
        else if(ToCurrency.equals("EUR"))
        {
            // Get the conversion rate from the source currency (FromCurrency) to EUR
            double rate = CurrencyConverterWithAPI.FindCurrency(FromCurrency);
            // Since the rate is for converting FromCurrency to EUR, we need to take its inverse to get EUR to FromCurrency rate
            rate = 1 / rate;
            // Convert the amount to EUR using the inverse rate and return the result
            return amount * rate;
        }
        else
        {
            // If neither the source nor the target currency is EUR, convert the amount to EUR first
            double ratetoeuro = CurrencyConverterWithAPI.FindCurrency(FromCurrency);
            double toeuro = amount / ratetoeuro;  // Convert FromCurrency to EUR
            // Then convert from EUR to the target currency
            double ToToCurrency = CurrencyConverterWithAPI.FindCurrency(ToCurrency);
            // Return the converted amount in the target currency
            return toeuro * ToToCurrency;
        }
    }
}