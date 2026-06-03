package model;

public class Customer {
    // customer attributes
    private  int customerId;
    private  String customerName;
    private  String customerPhoneNumber;

    // customer constructor
    public Customer(int customerId, String customerName,String customerPhoneNumber){
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerPhoneNumber = customerPhoneNumber;
    }

    // getters for customer id
    public int getCustomerId() {
        return customerId;
    }
    // getters for customer name
    public String getCustomerName() {
        return customerName;
    }
    // getters for phone number
    public String getPhoneNumber() {
        return customerPhoneNumber;
    }
    // display method
    public  void  displayCustomer(){
        System.out.println("customer ID: "+customerId);
        System.out.println("Customer name: "+customerName);
        System.out.println("phone number: "+customerPhoneNumber);
    }



}
