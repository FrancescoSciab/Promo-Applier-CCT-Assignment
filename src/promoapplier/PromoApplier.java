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
                if (isValidName(name)) {
                String[] nameParts = name.split(" ");
                String firstName = nameParts[0];
                String secondName = nameParts[1];
                
                System.out.println(firstName + " " + secondName);
                
                }

                
//                System.out.println(totalPurchaseStr);
//                System.out.println(classStr);
//                System.out.println(lastPurchaseStr);
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
}
    


