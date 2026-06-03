package ui;
import java.util.ArrayList;
import java.util.Scanner;
import model.Material;
import model.Supplier;
import model.Customer;
import model.Sale;

public class Main {
    public static void main(String[] args) {

        // accepting user input
        Scanner input = new Scanner(System.in);
        ArrayList<Material> inventory = new ArrayList<>();
        ArrayList <Supplier> suppliers = new ArrayList<>();
        ArrayList <Customer> customers = new ArrayList<>();
        ArrayList <Sale> sales =new ArrayList<>();
        int choice;
        boolean running = true;

        while (running) {
            System.out.println("\n===== WAREHOUSE MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Material");
            System.out.println("2. View Materials");
            System.out.println("3. Search Material");
            System.out.println("4. Receive Material");
            System.out.println("5. Issue Material");
            System.out.println("6. Low stock alert ");
            System.out.println("7. Remove an Item from Existing stock");
            System.out.println("8. Add Supplier");
            System.out.println("9. View Suppliers");
            System.out.println("10. Add customer");
            System.out.println("11. View customers");
            System.out.println("12. view sale reports");
            System.out.println("13. Exit");

            // --- MENU SELECTION LOOP: Traps errors cleanly without repeating the giant banner ---
            boolean validChoice = false;
            choice = -1;

            while (!validChoice) {
                System.out.println("Enter your choice (1-13):");
                try {
                    choice = input.nextInt();
                    input.nextLine(); // Clear buffer

                    if (choice >= 1 && choice <= 13) {
                        validChoice = true; // Input is perfect, escape validation loop
                    } else {
                        System.out.println("Error: Selection must be a number between 1 and 13.");
                    }
                } catch (Exception e) {
                    System.out.println("Error: Invalid entry! Please enter a valid number.");
                    input.nextLine(); // Discard the bad input text from scanner memory
                }
            }

            //switch case
            switch (choice) {
                case 1:
                    // Add materials
                    int numberOfMaterials = -1;
                    while (numberOfMaterials <= 0) {
                        try {
                            System.out.println("How many items do you want to add?");
                            numberOfMaterials = input.nextInt();
                            input.nextLine();
                            if (numberOfMaterials <= 0) {
                                System.out.println("Quantity must be greater than zero. Please try again.");
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid input! Please enter a valid whole number.");
                            input.nextLine(); // Clear buffer
                        }
                    }

                    for (int i = 0; i < numberOfMaterials; i++) {
                        String materialName = "";
                        while (materialName.isEmpty()) {
                            System.out.print("\nEnter Material Name:");
                            materialName = input.nextLine().trim();
                            if (materialName.isEmpty()) {
                                System.out.println("Material name cannot be left blank.");
                            }
                        }
                        // Check if this material name already exists in our ArrayList
                        Material existingMaterial = null;
                        for (Material m : inventory) {
                            if (m.getMaterialName().equalsIgnoreCase(materialName)) {
                                existingMaterial = m;
                                break;
                            }
                        }
                        // If it exists, redirect to "Receive Stock" logic
                        if (existingMaterial != null) {
                            System.out.println("-> This material already exists in inventory.");
                            System.out.println("Current Stock: " + existingMaterial.getQuantity());

                            int incomingQuantity = -1;
                            while (incomingQuantity <= 0) {
                                try {
                                    System.out.print("Enter quantity to add to this existing stock: ");
                                    incomingQuantity = input.nextInt();
                                    input.nextLine();

                                    if (incomingQuantity <= 0) {
                                        System.out.println("Error: Added quantity must be greater than zero.");
                                    } else {
                                        existingMaterial.setQuantity(existingMaterial.getQuantity() + incomingQuantity);
                                        System.out.println("Merged successfully! New Total Stock: " + existingMaterial.getQuantity());
                                    }
                                } catch (Exception e) {
                                    System.out.println("Invalid input. Please enter a valid whole number.");
                                    input.nextLine();
                                }
                            }
                        }
                        // If it is brand new, ask for the remaining details and create it
                        else {
                            int materialId = -1;
                            while (materialId <= 0) {
                                try {
                                    System.out.print("Enter Material ID (Positive Number): ");
                                    materialId = input.nextInt();
                                    input.nextLine();
                                    if (materialId <= 0) {
                                        System.out.println("ID must be greater than zero.");
                                    }
                                } catch (Exception e) {
                                    System.out.println("Invalid ID format. Please enter an integer.");
                                    input.nextLine();
                                }
                            }
                            System.out.println("Enter Unit of Measure (e.g. bags, litres, pieces):");
                            String unit = input.nextLine().trim();

                            int minStock = -1;
                            while (minStock < 0) {
                                System.out.println("Enter minimum stock alert level for this material:");
                                minStock = input.nextInt();
                                input.nextLine();
                            }


                            System.out.print("Enter Category:");
                            String category = input.nextLine().trim();
                            int quantity = -1;
                            while (quantity < 0) {
                                try {
                                    System.out.print("Enter Quantity:");
                                    quantity = input.nextInt();
                                    input.nextLine();
                                    if (quantity < 0) {
                                        System.out.print("Quantity cannot be negative.");
                                    }
                                } catch (Exception e) {
                                    System.out.println("Invalid entry. Please enter a whole number.");
                                    input.nextLine();
                                }
                            }

                            double unitPrice = -1.0;
                            while (unitPrice < 0) {
                                try {
                                    System.out.print("Enter Unit Price:");
                                    unitPrice = input.nextDouble();
                                    input.nextLine();
                                    if (unitPrice < 0) {
                                        System.out.println("Price cannot be negative.");
                                    }
                                } catch (Exception e) {
                                    System.out.println("Invalid pricing format. Use numbers (e.g., 12.50).");
                                    input.nextLine();
                                }
                            }

                            int supplierId;
                            boolean supplierFound;

                            if (suppliers.isEmpty()) {
                                System.out.println("No suppliers registered.");
                                System.out.println("Please add a supplier first.");
                                break;
                            }

                            do {
                                System.out.print("Enter supplier ID: ");
                                supplierId = input.nextInt();
                                input.nextLine();

                                supplierFound = false;

                                for (Supplier s : suppliers) {
                                    if (s.getSupplierId() == supplierId) {
                                        supplierFound = true;
                                        break;
                                    }
                                }

                                if (!supplierFound) {
                                    System.out.println("Supplier does not exist. Please enter a valid supplier ID.");
                                }

                            } while (!supplierFound);
                            Material material = new Material(materialId, materialName, category,
                                    quantity, unitPrice, unit, minStock,supplierId);

                            inventory.add(material);
                            System.out.println("New material registered successfully.");
                        }
                    }
                    break;

                case 2://display all materials
                    System.out.println("\n===== Materials In Inventory =====");
                    if (inventory.isEmpty()) {
                        System.out.println("Inventory registry is completely empty.");
                    } else {
                        for (Material material : inventory) {
                            material.displayMaterial();
                            System.out.println("-----------");
                        }
                    }
                    break;

                case 3://search feature
                    System.out.print("Enter material name to search: ");
                    String searchName = input.nextLine().trim();
                    boolean found = false;

                    for (Material material : inventory) {
                        if (material.getMaterialName().equalsIgnoreCase(searchName)) {
                            System.out.println("\nMaterial found:");
                            material.displayMaterial();
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println("Material not found.");
                    }
                    break;

                case 4:
                    // ===== RECEIVE MATERIAL FEATURE =====
                    System.out.print("Enter material to update/receive: ");
                    String updateName = input.nextLine().trim();

                    boolean updated = false;
                    for (Material material : inventory) {
                        if (material.getMaterialName().equalsIgnoreCase(updateName)) {
                            System.out.println("Current Quantity: " + material.getQuantity());

                            int receivedQuantity = -1;
                            while (receivedQuantity <= 0) {
                                try {
                                    System.out.print("Enter the Quantity received:");
                                    receivedQuantity = input.nextInt();
                                    input.nextLine(); // Clear buffer

                                    if (receivedQuantity <= 0) {
                                        System.out.println(" Error: Received quantity must be greater than zero.");
                                    } else {
                                        int totalNewQuantity = material.getQuantity() + receivedQuantity;
                                        material.setQuantity(totalNewQuantity);

                                        System.out.println(" Quantity updated Successfully.");
                                        System.out.print("New stock: " + totalNewQuantity);
                                    }
                                } catch (Exception e) {
                                    System.out.println(" Error: Quantity entry must be a valid whole number.");
                                    input.nextLine(); // Clear broken buffer
                                }
                            }
                            updated = true;
                            break; // Stop searching once found and updated
                        }
                    }
                    if (!updated) {
                        System.out.println("Material not found.");
                    }
                    break;

                case 5:

                {if (customers.isEmpty()) {
                        System.out.println("No customers registered.");
                        System.out.println("Please add a customer first.");
                        break;
                    }

                    System.out.print("Enter Customer ID: ");
                    int customerId = input.nextInt();
                    input.nextLine();

                    Customer selectedCustomer = null;

                    for (Customer c : customers) {
                        if (c.getCustomerId() == customerId) {
                            selectedCustomer = c;
                            break;
                        }
                    }

                    if (selectedCustomer == null) {
                        System.out.println("Customer not found.");
                        break;
                    }

                    System.out.print("Enter material name to issue: ");
                    String issueName = input.nextLine().trim();

                    boolean issued = false;

                    for (Material material : inventory) {

                        if (material.getMaterialName().equalsIgnoreCase(issueName)) {

                            System.out.println("Available Stock Balance: " + material.getQuantity());

                            int issueQuantity = -1;

                            while (issueQuantity <= 0) {

                                try {

                                    System.out.print("Enter quantity to issue: ");
                                    issueQuantity = input.nextInt();
                                    input.nextLine();

                                    if (issueQuantity <= 0) {

                                        System.out.println("Quantity must be greater than zero.");

                                    } else if (issueQuantity > material.getQuantity()) {

                                        System.out.println(
                                                "Insufficient stock. Available stock is "
                                                        + material.getQuantity());

                                        issueQuantity = -1;

                                    } else {

                                        int newQuantity =
                                                material.getQuantity() - issueQuantity;

                                        material.setQuantity(newQuantity);

                                        Sale sale = new Sale(
                                                sales.size() + 1,
                                                selectedCustomer.getCustomerId(),
                                                material.getMaterialId(),
                                                material.getMaterialName(),
                                                issueQuantity,
                                                material.getUnitPrice()
                                        );

                                        sales.add(sale);

                                        System.out.println("\n===== SALE RECORDED =====");
                                        System.out.println("Customer: "
                                                + selectedCustomer.getCustomerName());

                                        System.out.println("Material: "
                                                + material.getMaterialName());

                                        System.out.println("Quantity: "
                                                + issueQuantity);

                                        System.out.println("Unit Price: "
                                                + material.getUnitPrice());

                                        System.out.println("Total Amount: "
                                                + sale.getTotalAmount());

                                        System.out.println("Remaining Stock: "
                                                + newQuantity);

                                        issued = true;
                                    }

                                } catch (Exception e) {

                                    System.out.println(
                                            "Invalid quantity. Please enter a whole number.");

                                    input.nextLine();
                                }
                            }

                            break;
                        }
                    }

                    if (!issued) {
                        System.out.println("Material not found.");
                    }

                    break;}
                case 6:
                    // ===== LOW STOCK ALERT FEATURE =====
                    System.out.println("\n===== LOW STOCK ALERTS =====");

                    boolean lowStockFound = false;
                    for (Material material : inventory) {
                        if (material.getQuantity() < material.getMinimumStockLevel()) {
                            lowStockFound =true;
                            System.out.println("LOW STOCK: " + material.getMaterialName());
                            System.out.println("Unit: " + material.getUnitOfMeasure());
                            System.out.println("Current Stock: " + material.getQuantity());
                            System.out.println("Minimum Required: " + material.getMinimumStockLevel());
                        }
                    }
                    if (!lowStockFound) {
                        System.out.println("All materials are sufficiently stocked.");
                    }
                    break;
                case 7:
                    // ===== DELETE MATERIAL FEATURE =====
                    System.out.println("\n===== DELETE MATERIAL FROM INVENTORY =====");
                    if (inventory.isEmpty()) {
                        System.out.println("Inventory registry is completely empty. Nothing to delete.");
                        break;
                    }

                    System.out.print("Enter the name of the material to delete: ");
                    String deleteName = input.nextLine().trim();

                    boolean removed = false;
                    // Loop through the ArrayList to find a matching name
                    for (int j = 0; j < inventory.size(); j++) {
                        if (inventory.get(j).getMaterialName().equalsIgnoreCase(deleteName)) {

                            // Confirmation loop to prevent accidental deletions
                            String confirmation = "";
                            while (!confirmation.equals("Y") && !confirmation.equals("N")) {
                                System.out.print("Are you sure you want to permanently delete '" +
                                        inventory.get(j).getMaterialName() + "'? (Y/N): ");
                                confirmation = input.nextLine().trim().toUpperCase();

                                if (!confirmation.equals("Y") && !confirmation.equals("N")) {
                                    System.out.println("Invalid choice. Please enter Y for Yes or N for No.");
                                }
                            }

                            if (confirmation.equals("Y")) {
                                inventory.remove(j); // Delete the item from the ArrayList
                                System.out.println("Material deleted successfully!");
                            } else {
                                System.out.println("Deletion canceled.");
                            }

                            removed = true;
                            break; // Stop looking since we found the item
                        }
                    }

                    if (!removed) {
                        System.out.println("Material not found.");
                    }
                    break;
                case 8:

                    int supplierId = -1;

                    while (supplierId <= 0) {
                        try {
                            System.out.print("Enter Supplier ID: ");
                            supplierId = input.nextInt();
                            input.nextLine();

                            if (supplierId <= 0) {
                                System.out.println("Supplier ID must be greater than zero.");
                            }

                        } catch (Exception e) {
                            System.out.println("Invalid input! Please enter a valid number.");
                            input.nextLine();
                        }
                    }

                    String supplierName = "";

                    while (supplierName.isEmpty()) {
                        System.out.print("Enter Supplier Name: ");
                        supplierName = input.nextLine().trim();

                        if (supplierName.isEmpty()) {
                            System.out.println("Supplier name cannot be blank.");
                        }
                    }

                    String phoneNumber = "";

                    while (phoneNumber.isEmpty()) {
                        System.out.print("Enter Phone Number: ");
                        phoneNumber = input.nextLine().trim();

                        if (phoneNumber.isEmpty()) {
                            System.out.println("Phone number cannot be blank.");
                        }
                    }

                    String email = "";

                    while (email.isEmpty()) {
                        System.out.print("Enter Email: ");
                        email = input.nextLine().trim();

                        if (!email.contains("@")) {
                            System.out.println("Please enter a valid email address.");
                            email = "";
                        }
                    }

                    String location = "";

                    while (location.isEmpty()) {
                        System.out.print("Enter Location: ");
                        location = input.nextLine().trim();

                        if (location.isEmpty()) {
                            System.out.println("Location cannot be blank.");
                        }
                    }

                    Supplier supplier = new Supplier(
                            supplierId,
                            supplierName,
                            phoneNumber,
                            email,
                            location
                    );

                    suppliers.add(supplier);

                    System.out.println("Supplier added successfully!");

                    break;
                case 9:

                    System.out.println("\n===== REGISTERED SUPPLIERS =====");

                    if (suppliers.isEmpty()) {

                        System.out.println("No suppliers registered.");

                    } else {

                        for (Supplier s   : suppliers) {

                            s.displaySupplier();

                            System.out.println("----------------------------");
                        }
                    }

                    break;
                case 10:

                {int customerId = -1;

                    while (customerId <= 0) {
                        try {
                            System.out.print("Enter Customer ID: ");
                            customerId = input.nextInt();
                            input.nextLine();

                            if (customerId <= 0) {
                                System.out.println("Customer ID must be greater than zero.");
                            }

                        } catch (Exception e) {
                            System.out.println("Invalid input! Please enter a valid number.");
                            input.nextLine();
                        }
                    }

                    String customerName = "";

                    while (customerName.isEmpty()) {
                        System.out.print("Enter Customer Name: ");
                        customerName = input.nextLine().trim();

                        if (customerName.isEmpty()) {
                            System.out.println("Customer name cannot be blank.");
                        }
                    }

                    String customerPhoneNumber = "";

                    while (customerPhoneNumber.isEmpty()) {
                        System.out.print("Enter Phone Number: ");
                        customerPhoneNumber = input.nextLine().trim();

                        if (customerPhoneNumber.isEmpty()) {
                            System.out.println("Phone number cannot be blank.");
                        }
                    }

                    Customer customer = new Customer(
                            customerId,
                            customerName,
                            customerPhoneNumber
                    );

                    customers.add(customer);

                    System.out.println("Customer added successfully!");

                    break;}
                case 11:

                    System.out.println("\n===== REGISTERED CUSTOMERS =====");

                    if (customers.isEmpty()) {

                        System.out.println("No customers registered.");

                    } else {

                        for (Customer c : customers) {

                            c.displayCustomer();

                            System.out.println("----------------------------");
                        }
                    }

                    break;

                case 12:

                    System.out.println("\n===== SALES REPORT =====");

                    if (sales.isEmpty()) {

                        System.out.println("No sales transactions recorded.");

                    } else {
                        double totalRevenue = 0;
                        int totalTransactions = sales.size();

                        for (Sale sale : sales) {
                            totalRevenue += sale.getTotalAmount();
                            sale.displaySale();

                            System.out.println("--------------------------------");
                        }
                        System.out.println("================================");
                        System.out.println("Total Transactions: " + totalTransactions);
                        System.out.println("Total Revenue: KES " + totalRevenue);
                    }

                    break;
                case 13:
                    // EXIT FEATURE
                    System.out.println("Exiting Warehouse Management System. Goodbye!");
                    running = false;
                    break;
            }
        }
        input.close();
    }
}

