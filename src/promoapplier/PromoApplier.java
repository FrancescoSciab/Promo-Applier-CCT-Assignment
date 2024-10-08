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
        // TODO code application logic here
        try {
            File customers = new File("customers.txt");
            Scanner customersData = new Scanner(new FileReader(customers));
            while (customersData.hasNextLine()) {
                String data = customersData.nextLine();
                System.out.println(data);
        }
        } catch (FileNotFoundException e){
                System.out.println("An error occurred.");
        }
        }
                
    }
    

