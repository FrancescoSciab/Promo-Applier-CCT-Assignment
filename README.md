# Overview

The Promo Applier is a Java-based application designed to read customer data from a file, validate the data, and apply discounts based on specific rules. The program processes each customer, validates the data, calculates the final price after applying a discount, and writes the results to a new output file.

The discount is determined by the customer's class and the time since their last purchase, according to predefined rules. The application provides feedback on invalid or incomplete customer data.

## File Structure

1. Input File (customers.txt): The input file should follow this format for each customer:
```
Full Name (e.g., John Doe)
Total Purchase Amount (e.g., 250.50)
Customer Class (1, 2, or 3)
Last Purchase Year (e.g., 2020)
```
Output File (customerdiscount.txt): The output file will contain:
* The customer's first and second name.
* The final price after the discount is applied.
* If the data is invalid or missing, a message indicating the issue will be written instead.

## Methods Overview

### Main Methods:
```main```: Reads the input file, processes each customer's data, validates the fields, calculates the discount, and writes the results to the output file.

### Validation Methods:
* ```isValidName(String name)```: Validates the customer's name. First name must contain only letters, while the second name can contain both letters and numbers.
* ```isValidTotalPurchase(String totalPurchaseStr)```: Checks if the total purchase is a valid decimal number and greater than zero.
* ```isValidClass(String classStr)```: Ensures that the customer's class is an integer between 1 and 3.
* ```isValidYear(String lastPurchaseStr, int currentYear)```: Verifies that the year of the last purchase is between 1900 and the current year.
### Discount Calculation:
* ```calculateDiscount(double totalPurchase, int classValue, String lastPurchaseStr, int currentYear)```: Calculates the discount based on customer class and the years since their last purchase.

## License

This project is available under the MIT License.

## Contact

If you have any questions or need further assistance, feel free to reach out to:

* **Author**: Francesco Sciabbarrasi
* **GitHub**: [FrancescoSciab](https://github.com/FrancescoSciab)
