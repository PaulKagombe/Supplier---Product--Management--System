package model;

public class Supplier {
    // Atributes
    private  int supplierId;
    private String supplierName;
    private String phoneNumber;
    private String Email;
    private String location;

    // construct
    public Supplier(int supplierId, String supplierName, String phoneNumber, String Email,String location){
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.phoneNumber = phoneNumber;
        this.Email = Email;
        this.location = location;
    }
    // getters for supplier Id

    public int getSupplierId() {
        return supplierId;
    }
    //getters for Supplier name

    public String getSupplierName() {
        return supplierName;
    }
    //getters for phone Number

    public String getPhoneNumber() {
        return phoneNumber;
    }
    //getters for Email

    public String getEmail() {
        return Email;
    }
    //getters for location

    public String getLocation() {
        return location;
    }
    // display method
    public  void   displaySupplier(){
        System.out.println("Supplier ID :"+ supplierId);
        System.out.println("Supplier's Name:"+supplierName);
        System.out.println("PhoneNumber:"+phoneNumber);
        System.out.println("Email:"+Email);
        System.out.println("location:"+location);
    }

}
