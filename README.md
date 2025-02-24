# Currency Converter Application

This is a Java-based currency converter application that allows users to convert amounts between different currencies using real-time exchange rates fetched from an external API. The application is built using **JavaFX** for the graphical user interface (GUI) and **Gson** for parsing JSON responses from the API.

---

## Installation and Usage

### Prerequisites
To run the application, you will need the following:
- **Java Development Kit (JDK)** (Version 11 or higher recommended).
- **JavaFX SDK:**
  1. Go to the official [Gluon JavaFX website](https://gluonhq.com/products/javafx/).
  2. Scroll down and download the JavaFX SDK for your operating system.
  3. Extract the downloaded ZIP file to a location on your computer.

## Files Overview

### 1. **CurrencyConverter.java**
This file contains the core logic for currency conversion. It defines a method `ConvertCurrency` that takes an amount, a source currency, and a target currency, and returns the converted amount. The conversion logic handles three scenarios:
- Converting from **EUR** to another currency.
- Converting to **EUR** from another currency.
- Converting between two non-EUR currencies by first converting to **EUR** as an intermediate step.

### 2. **CurrencyConverterWithAPI.java**
This file is responsible for fetching real-time exchange rates from an external API. It uses the `FindCurrency` method to retrieve the exchange rate for a specific currency. The API response is parsed using **Gson**, and the relevant exchange rate is extracted based on the currency code provided. The file handles HTTP requests and responses and includes error handling for failed API requests.

### 3. **ExchangeRatesResponse.java**
This file defines the `ExchangeRatesResponse` class, which is used to map the JSON response from the exchange rate API. It includes fields for the success status, base currency, and exchange rates. The class provides getter and setter methods for accessing and modifying these fields.

### 4. **Main.java**
This file contains the main application logic and the JavaFX GUI setup. It defines the `Main` class, which extends `Application` and overrides the `start` method to create the GUI. The GUI includes:
- Input fields for the amount and currency selection.
- Buttons for submitting the conversion, reversing the currencies, and closing the application.
- Display of the conversion result.

### 5. **Rates.java**
This file defines the `Rates` class, which represents the exchange rates for various currencies. It includes fields for different currencies (USD, EUR, JPY, etc.) and provides getter and setter methods for accessing and modifying these rates. This class is used in conjunction with `ExchangeRatesResponse` to store and retrieve exchange rate data.

---

## How to Run the Application

Open a terminal/command prompt and navigate to the directory where the Java files are stored. 
Use the following commands to compile the Java files and to run the GUI-based distance converter application:
```bash
javac --module-path ./<path_to_javaFX_directory>/lib --add-modules javafx.controls -cp ".;gson-2.10.1.jar" -Xlint:deprecation Main.java CurrencyConverter.java CurrencyConverterWithAPI.java Rates.java ExchangeRatesResponse.java
java --module-path ./<path_to_javaFX_directory>/lib -cp ".;gson-2.10.1.jar"  --add-modules javafx.controls Main
