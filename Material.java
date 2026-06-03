package model;

public class Material {
    //Attributes
    private int materialId;
    private  String materialName;
    private  String unitOfMeasure;
    private  String category;
    private int quantity;
    private  int minimumStockLevel;
    private  double unitPrice;
    private  int supplierId;

    //constructor
    public Material(int materialId,String materialName,String category,int quantity,double unitPrice, String unitOfMeasure,int minimumStockLevel,int supplierId){

        this.materialId = materialId;
        this. materialName = materialName;
        this.unitOfMeasure = unitOfMeasure;
        this. category = category;
        this. quantity = quantity;
        this.minimumStockLevel =minimumStockLevel;
        this. unitPrice= unitPrice;
        this.supplierId = supplierId;
     }
         // Display method
    public void displayMaterial(){
        System.out.println("Material ID:"+materialId);
        System.out.println("Material Name:"+materialName);
        System.out.println("Unit of measure:"+unitOfMeasure);
        System.out.println("Category:"+category);
        System.out.println("Quantity:"+quantity);
        System.out.println("low stock level count:"+minimumStockLevel);
        System.out.println("Unit Price:"+unitPrice);
    }
    //Getters for material id
    public int getMaterialId() {
        return materialId;
    }
    //Getters for material name
    public String getMaterialName() {
        return materialName;
    }
    //Getters for unit of measure
    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }
    //Getters for category
    public String getCategory() {
        return category;
    }
    //Getter for Quantity
    public int getQuantity() {
        return quantity;
    }
    // Getters for minimum stock
    public int getMinimumStockLevel() {
        return minimumStockLevel;
    }
    //Getter for Unit Price
    public double getUnitPrice() {
        return unitPrice;
    }
    //setters for material id
    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }
    //getter for supplier ID

    public int getSupplierId() {
        return supplierId;
    }

    // setters for material name
    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }
    // Setters for  unit of measure
    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }
    //Setters for category
    public void setCategory(String category) {
        this.category = category;
    }
    //setters for quantity
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    //setter for minimum stock

    public void setMinimumStockLevel(int minimumStockLevel) {
        this.minimumStockLevel = minimumStockLevel;
    }
    // setter for unit price
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
