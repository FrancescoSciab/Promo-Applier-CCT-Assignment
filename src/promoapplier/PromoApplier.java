/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package promoapplier;

/**
 *
 * @author francescosciabbarrasi
 */
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDate;


public class PromoApplier {

    //Link to GitHub repository: https://github.com/FrancescoSciab/Promo-Applier-CCT-Assignment/tree/master
    
    public static void main(String[] args) {
        //reading file and handling successful operation
        try {
            File customers = new File("customers.txt");
            Scanner customersData = new Scanner(new FileReader(customers));
            FileWriter writer = new FileWriter("customerdiscount.txt");
            
            while (customersData.hasNextLine()) {
                //Read the customer data from file(4 lines per customer)
                String name = customersData.nextLine();
                String totalPurchaseStr = customersData.nextLine();
                String classStr = customersData.nextLine();
                String lastPurchaseStr = customersData.nextLine();
                
                // Check for incomplete data/blank field. Skipping to next cst if data missing
                if (name.isEmpty() || totalPurchaseStr.isEmpty() || classStr.isEmpty() || lastPurchaseStr.isEmpty()) {
                    writer.write("Missing data for customer: " + name + "\n");
                    continue;
                }
                
                //getting current year using library
                int currentYear = LocalDate.now().getYear();
                
                //Validating each field running below methods
                if (isValidName(name) && isValidTotalPurchase(totalPurchaseStr) && isValidClass(classStr) && isValidYear(lastPurchaseStr, currentYear)) {
                    String[] nameParts = name.split(" ");
                    String firstName = nameParts[0];
                    String secondName = nameParts[1];
                    double totalPurchase = Double.parseDouble(totalPurchaseStr);
                    int classValue = Integer.parseInt(classStr);
                    
                    //Calculating final price based on class and last purchase using below method
                    double discountedPrice = calculateDiscount(totalPurchase, classValue, lastPurchaseStr, currentYear);
                    double finalPrice = totalPurchase - discountedPrice;
                    
                    //Write valid result to customerdiscount.txt
                    writer.write(firstName + " " + secondName + "\n");
                    writer.write("Final Price: " + finalPrice + "\n");
                } else {
                    writer.write("Invalid data for customer: " + name + "\n");
                }
            }
            customersData.close();
            writer.close();
            //handling potential errors when attempting to read file
        } catch (FileNotFoundException e){
                System.out.println("An error occurred while reading from file");
            //handling potential errors when attempting to write on file
        } catch (IOException e) {
            System.out.println("An error occurred while writing on a new file: IO error.");
        }
    }
   
    
    //Validation Methods
    //first name must be letters only and second name can be letters and/or numbers and must be separated from the first name by a single space
    private static boolean isValidName(String name) {
        String[] parts = name.split(" ");
        if (parts.length != 2) {
        System.out.println("Name or surname missing");
        return false;
        }
        if (!parts[0].matches("[a-zA-Z]+")) {
        System.out.println("The field first name must be letters only");
        return false;
        }
        if (!parts[1].matches("[a-zA-Z0-9]+")) {
        System.out.println("The second name can be letters and/or numbers and must be separated from the first name by a single space");
        return false;
        }
        return true;
    }   
    
    private static boolean isValidTotalPurchase(String totalPurchaseStr) {
    try {
        double totalPurchase = Double.parseDouble(totalPurchaseStr);
        if (totalPurchase <= 0) {
            System.out.println("No purchases.");
            return false;
        }
        return true;
    } catch (NumberFormatException e) {
        System.out.println("The field value of total purchase must be a decimal number");
        return false;
    }
    }
    
    //Only classes available are: 1, 2 or 3.
    private static boolean isValidClass(String classStr) {
        try {
            int classInt = Integer.parseInt(classStr);
            if (classInt < 1 || classInt > 3) {
                System.out.println("The field Class must be a integer between 1 to 3.");
                return false;
            }
            return true;
            
        } catch (NumberFormatException e) {
            System.out.println("The field class must be a valid integer.");
            return false;
        }
    }
    //using general rules when validating year(from 1900 to current year).
    private static boolean isValidYear(String lastPurchaseStr, int currentYear) {
        
        try {
            int yearInt = Integer.parseInt(lastPurchaseStr);
            if (yearInt > 1900 && yearInt <= currentYear) {
                return true;
            } else {
                System.out.println("Year must be a valid year.");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Last purchase year must be a number.");
            return false;
        }
    }
    
    //Discount Calculation Method
    private static double calculateDiscount(double totalPurchase, int classValue, String lastPurchaseStr, int currentYear) {
        // No discount applied if the total purchase is zero
        if (totalPurchase == 0) {
            return 0; 
        }
        
        int lastYearPurchase = Integer.parseInt(lastPurchaseStr);
        int yearsSinceLastPurchase = currentYear - lastYearPurchase;
        double discountPercentage = 0.0;
        
        //discount applier logic
        switch (classValue) {
            case 1: 
                discountPercentage = (yearsSinceLastPurchase == 0) ? 0.30 :
                                     (yearsSinceLastPurchase <= 5) ? 0.20 : 0.10;
                break;
            case 2: 
                discountPercentage = (yearsSinceLastPurchase == 0) ? 0.15 :
                                     (yearsSinceLastPurchase <= 5) ? 0.13 : 0.05;
                break;
            case 3:
                discountPercentage = (yearsSinceLastPurchase == 0) ? 0.03 : 0.00;
                break;
        }
        return totalPurchase * discountPercentage;
    }
}
    


