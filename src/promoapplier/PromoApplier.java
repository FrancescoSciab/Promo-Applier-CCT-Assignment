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
import java.io.FileNotFoundException;
import java.util.Scanner;


public class PromoApplier {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        //reading file and handling successful operation
        try {
            File customers = new File("customers.txt");
            Scanner customersData = new Scanner(new FileReader(customers));
            while (customersData.hasNextLine()) {
                //Read the customer data (4 lines per customer)
                String name = customersData.nextLine();
                String totalPurchaseStr = customersData.nextLine();
                String classStr = customersData.nextLine();
                String lastPurchaseStr = customersData.nextLine();
                
                //Validating each field
                if (isValidName(name) && isValidTotalPurchase(totalPurchaseStr) && isValidClass(classStr) && isValidYear(lastPurchaseStr)) {
                String[] nameParts = name.split(" ");
                String firstName = nameParts[0];
                String secondName = nameParts[1];
                double totalPurchase = Double.parseDouble(totalPurchaseStr);
                int classValue = Integer.parseInt(classStr);
                
                //Calculate final value base on class and most recent purchase
                double discount = calculateDiscount(totalPurchase, classValue, lastPurchaseStr);
                double finalPrice = totalPurchase - discount;
                
                //testing before writing to another file
                System.out.println(firstName + " " + secondName);
                System.out.println("spent a total of: " + totalPurchase);
                System.out.println("in " + lastPurchaseStr);
                System.out.println("with class: " + classValue);
                System.out.println("Your final PRICE WILL BE: " + finalPrice + "\n");
                
                }
        }
            customersData.close();
            //handling potential errors when attempting to read file
        } catch (FileNotFoundException e){
                System.out.println("An error occurred.");
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
    
    private static boolean isValidYear(String lastPurchaseStr) {
        try {
            int yearInt = Integer.parseInt(lastPurchaseStr);
            if (yearInt > 1900 && yearInt <= 2024) {
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
    private static double calculateDiscount(double totalPurchase, int classValue, String lastPurchaseStr) {
        int currentYear = 2024;
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
                if(MostRecentPurchase >= 5) {
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
                if(MostRecentPurchase >= 5) {
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
    


