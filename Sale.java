package model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Sale {
    // sales attributes
    private int salesId;
    private int customerId;
    private int materialId;
    private String materialName;
    private int quantity;
    private double unitPrice;
    private double totalAmount;
    private LocalDateTime saleDateTime;

    //constructor sales
     public Sale( int salesId,
                  int customerId,
                  int materialId,
                  String materialName,
                  int quantity,
                  double unitPrice
     )   {
         this.salesId = salesId;
         this.customerId = customerId;
         this.materialId = materialId;
         this.materialName = materialName;
         this.quantity = quantity;
         this.unitPrice = unitPrice;
         this.totalAmount = quantity * unitPrice;
         this.saleDateTime = LocalDateTime.now();
     }
     //getters for sale ID

    public int getSalesId() {
        return salesId;
    }
    // getters for customer Id

    public int getCustomerId() {
        return customerId;
    }
    // getters for material Id

    public int getMaterialId() {
        return materialId;
    }
    //getters for material name

    public String getMaterialName() {
        return materialName;
    }
    //getters for quantity

    public int getQuantity() {
        return quantity;
    }
    //getters for unit price

    public double getUnitPrice() {
        return unitPrice;
    }
    //getters for total amount

    public double getTotalAmount() {
        return totalAmount;
    }
    //getters for local date and time

    public LocalDateTime getSaleDateTime() {
        return saleDateTime;
    }
    //display method
    public void displaySale(){
         System.out.println("sale ID: "+ salesId);
         System.out.println("Customer ID: "+customerId);
         System.out.println("Material ID: " +materialId);
         System.out.println("Material Name: "+materialName);
         System.out.println("Quantity: "+quantity);
         System.out.println("Unit price: "+unitPrice);
         System.out.println("Total Amount: "+totalAmount);
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        System.out.println(
                "Sale Date and Time: "
                        + saleDateTime.format(formatter)
        );
    }
}
