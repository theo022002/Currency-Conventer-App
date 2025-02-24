import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox; 
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.geometry.Pos;

public class Main extends Application
{
    @Override
    public void start(Stage primaryStage) 
    {
        // Create a label and text field for entering the amount
        Label titleLabel = new Label("Convert Currency");
        titleLabel.setFont(new Font("Arial", 18));  // Set font size and style
        titleLabel.setTextFill(Color.WHITE);        // Set font color
        titleLabel.setStyle("-fx-background-color: #C0392B; -fx-padding: 10px; -fx-font-weight: bold;");

        // Centering the title using HBox
        HBox titleBox = new HBox(titleLabel);
        titleBox.setAlignment(Pos.CENTER);  // Center align the title
        titleBox.setStyle("-fx-background-color: #C0392B;");  // Set background color of the title
        Label label = new Label("Enter the amount: ");
        TextField textField = new TextField();

        // Create a label and choice box for selecting the "From Currency"
        Label label2 = new Label("Enter the currency you are converting from (USD, EUR, AED, JPY, NOK, AUD, BTC, GBP): ");
        ChoiceBox<String> choiceBox = new ChoiceBox<>();

        // Add currency options to the choice box
        choiceBox.getItems().addAll("AED", "EUR", "JPY", "USD", "NOK", "AUD", "BTC", "GBP");
        choiceBox.setValue("From Currency"); // Default value

        // Create a label and choice box for selecting the "To Currency"
        Label label3 = new Label("Enter the currency you are converting to (USD, EUR, AED, JPY, NOK, AUD, BTC, GBP): ");
        ChoiceBox<String> choiceBox2 = new ChoiceBox<>();

        // Add currency options to the second choice box
        choiceBox2.getItems().addAll("AED", "EUR", "JPY", "USD", "NOK", "AUD", "BTC", "GBP");
        choiceBox2.setValue("To Currency"); // Default value

        // Create buttons for submitting, reversing the currencies, and closing the application
        Button button = new Button("Submit");
        Button button_rev = new Button("Reverse");
        Button button_close = new Button("Close");

        Label resultLabel = new Label();
        Label label_line = new Label("\n");
        Label resultLabel2 = new Label();
        HBox hbox_amount = new HBox(10);
        VBox vbox_cur = new VBox(10);
        HBox hbox_button = new HBox(10); 
        HBox resultBox = new HBox(resultLabel);
        VBox vbox = new VBox(10);

        // Add components to the VBox and HBoxes
        vbox.getChildren().addAll(titleBox);
        hbox_amount.getChildren().addAll(label, textField);
        vbox_cur.getChildren().addAll(label2, choiceBox, label3, choiceBox2);
        vbox.getChildren().addAll(hbox_amount, vbox_cur);
        hbox_button.getChildren().addAll(button, button_rev, button_close);
        vbox.getChildren().addAll(hbox_button, label_line, resultBox, resultLabel2);

        // Define the action when the "Submit" button is clicked
        button.setOnAction(e -> 
        {
            try
            { 
                // Get the amount entered by the user
                double amount = Double.parseDouble(textField.getText());
                // Get the selected currencies from the choice boxes
                String FromCurrency = choiceBox.getValue().toUpperCase();
                String ToCurrency = choiceBox2.getValue().toUpperCase();

                // Check if the user has selected a valid currency code
                if(choiceBox.getValue().equals("From Currency") || choiceBox2.getValue().equals("To Currency"))
                {
                    // Show an alert if a valid currency is not selected
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Invalid Input");
                    alert.setHeaderText("Please select a valid currency code.");
                    alert.setContentText("The code currency must be valid.");
                    alert.showAndWait();
                }
                else
                {
                    // Styling the result label
                    resultLabel.setText(String.format("ANSWER"));
                    resultLabel.setFont(new Font("Arial", 15));
                    resultLabel.setTextFill(Color.WHITE);
                    resultLabel.setStyle("-fx-background-color: #C0392B; -fx-padding: 10px; -fx-font-weight: bold;");
                    resultBox.setAlignment(Pos.CENTER);
                    resultBox.setStyle("-fx-background-color: #C0392B;");

                    // Perform the currency conversion and display the result
                    double res = CurrencyConverter.ConvertCurrency(amount, FromCurrency, ToCurrency);
                    // Format and display the result based on the conversion result
                    if(res < 1e-2)
                        resultLabel2.setText(String.format("%.2f %s = %.6f %s", amount, FromCurrency, res, ToCurrency));
                    else
                        resultLabel2.setText(String.format("%.2f %s = %.2f %s", amount, FromCurrency, res, ToCurrency));
                }
            }
            catch (NumberFormatException ex)
            {
                // Show an alert when the entered amount is not a valid number
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText("Please enter a valid number");
                alert.setContentText("The amount must be a valid number.");
                alert.showAndWait();
            }
        });

        // Define the action when the "Close" button is clicked (closes the application)
        button_close.setOnAction(e -> 
        {
            primaryStage.close();
        });
    
        // Define the action when the "Reverse" button is clicked (reverses the From and To currencies)
        button_rev.setOnAction(e -> 
        {
            // Swap the selected currencies
            String temp = choiceBox.getValue(); 
            choiceBox.setValue(choiceBox2.getValue()); 
            choiceBox2.setValue(temp); 
        });

        // Create a scene with the VBox layout and set the stage (window) properties
        Scene scene = new Scene(vbox, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Currency Converter");
        primaryStage.show(); // Display the window
    }

    // The main method to launch the JavaFX application
    public static void main(String[] args) 
    {
        launch(args);
    }
}