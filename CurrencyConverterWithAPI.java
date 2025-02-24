import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CurrencyConverterWithAPI 
{
    public static double FindCurrency(String currencyToFind)
    {
        double specificRate = 0.0;
        try 
        {
            String apiUrl = "https://api.exchangeratesapi.io/latest?access_key=f267d8fc567d1cbdab37ada9183d673e";

            URL url = new URL(apiUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);

            int status = con.getResponseCode();
            if (status == HttpURLConnection.HTTP_OK) 
            {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder content = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) 
                {
                    content.append(inputLine);
                }

                in.close();

               
                String jsonResponse = content.toString();
                Gson gson = new GsonBuilder().create();
                ExchangeRatesResponse response = gson.fromJson(jsonResponse, ExchangeRatesResponse.class);

                if (response.isSuccess() && response.getRates() != null) 
                {
                    specificRate = getSpecificRate(response, currencyToFind);
                } 
                else 
                {
                    System.out.println("Failed to retrieve rates.");
                }
            } 
            else 
            {
                System.out.println("Error: Unable to get data. Response code: " + status);
            }

            con.disconnect();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return specificRate;
    }  
    
    
    private static double getSpecificRate(ExchangeRatesResponse response, String currency) 
    {
        switch (currency) 
        {
            case "USD":
                return response.getRates().getUSD();
            case "EUR":
                return response.getRates().getEUR();
            case "JPY":
                return response.getRates().getJPY();
            case "AED":
                return response.getRates().getAED();
            case "NOK":
                return response.getRates().getNOK();
            case "AUD":
                return response.getRates().getAUD();
            case "BTC":
                return response.getRates().getBTC();
            case "GBP":
                return response.getRates().getGBP();
            default:
                System.out.println("Currency not supported: " + currency);
                return -1; 
        }
    }
}
