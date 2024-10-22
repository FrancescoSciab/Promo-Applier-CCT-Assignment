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

    //Link to GitHub repository: https://github.com/FrancescoSciab/Promo-Applier-CCT-Assignment/tree/main
    
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
                
                //getting year through library
                int currentYear = LocalDate.now().getYear();
                
                //Validating each field
                if (isValidName(name) && isValidTotalPurchase(totalPurchaseStr) && isValidClass(classStr) && isValidYear(lastPurchaseStr, currentYear)) {
                String[] nameParts = name.split(" ");
                String firstName = nameParts[0];
                String secondName = nameParts[1];
                double totalPurchase = Double.parseDouble(totalPurchaseStr);
                int classValue = Integer.parseInt(classStr);
                
                //Calculate final price base on class and most recent purchase
                double discount = calculateDiscount(totalPurchase, classValue, lastPurchaseStr, currentYear);
                double finalPrice = totalPurchase - discount;
                
                //Write valid result to customerdiscount.txt
                writer.write(firstName + " " + secondName + "\n");
                writer.write("Final Price: " + finalPrice + "\n");
                
                
                //testing before writing to another file
//                System.out.println(firstName + " " + secondName);
//                System.out.println("spent a total of: " + totalPurchase);
//                System.out.println("in " + lastPurchaseStr);
//                System.out.println("with class: " + classValue);
//                System.out.println("Your final PRICE WILL BE: " + finalPrice + "\n");
                
                } else {
                    System.out.println("Invalid data for customer: " + name);
                    writer.write("Invalid data for customer: " + name + "\n");
                }
        }
            customersData.close();
            writer.close();
            //handling potential errors when attempting to read file
        } catch (FileNotFoundException e){
                System.out.println("An error occurred.");
        } catch (IOException e) {
            System.out.println("An error occurred: IO error.");
        }
    }
   
    
    //Validation Methods
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
        Double.parseDouble(totalPurchaseStr);
        return true;
    } catch (NumberFormatException e) {
        System.out.println("The field value of total purchase must be a decimal number");
        return false;
    }
    }
    
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
        
        int lastYearPurchase = Integer.parseInt(lastPurchaseStr);
        int MostRecentPurchase = currentYear - lastYearPurchase;
        double discount = 0.0;
        
        switch (classValue) {
            case 1: 
                if(MostRecentPurchase == 0) {
                    discount = totalPurchase * 0.30;    
                }
                if(MostRecentPurchase <= 5) {
                    discount = totalPurchase * 0.20;
                }
                if(MostRecentPurchase > 5) {
                    discount = totalPurchase * 0.10;
                }
                break;
            case 2: 
                if(MostRecentPurchase == 0) {
                    discount = totalPurchase * 0.15;    
                }
                if(MostRecentPurchase <= 5) {
                    discount = totalPurchase * 0.13;
                }
                if(MostRecentPurchase > 5) {
                    discount = totalPurchase * 0.05;
                }
                break;
            case 3:
                if(MostRecentPurchase == 0) {
                    discount = totalPurchase * 0.03;    
                }
                break;
        }
        return discount;
    }
}
    


