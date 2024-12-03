package petcare.management.system;

import java.util.Scanner;

public class Pet {
      
        public void pDetails(){
        Scanner sc = new Scanner(System.in);
        String response;
    do{
        System.out.println("\n------------------------------");
        System.out.println("OWNER DETAIL");
        System.out.println("1. ADD PET DETAIL");               
        System.out.println("2. VIEW PET DETAIL");        
        System.out.println("3. UPDATE PET DETAIL");        
        System.out.println("4. DELETE PET DETAIL");        
        System.out.println("5. EXIT");      
        
        int action = -1;

        while (action < 1 || action > 5) {
              System.out.print("Enter Action: ");
        
        if (sc.hasNextInt()) {
           action = sc.nextInt();   
           
        if (action < 1 || action > 5) {        
            System.out.println("Invalid input. Please choose a number from 1 to 5.");
        }
            
        } else {
            System.out.println("Invalid input. Please enter a number.");
            sc.next();
        }
        
  }
        Owner os = new Owner();
        Pet pt = new Pet();
        
        switch(action){
            
            case 1:
                pt.addPetDetails();
                pt.viewPetDetails();
                break;
                
            case 2:
                pt.viewPetDetails();
                break;
                
            case 3:
                os.viewOwnerDetails();
                pt.updatePetDetails();
                pt.viewPetDetails();
                break;
                
            case 4:
                pt.viewPetDetails();
                pt.deletePetDetails();
                pt.viewPetDetails();                
                break;
                
            case 5:
                
                break;
                
                
        }
        
        System.out.println("Do you want to continue? yes/no: ");
        response = sc.next();
        
    }while(response.equalsIgnoreCase("yes"));  
    }
        
    public void addPetDetails() {
      Scanner sc = new Scanner(System.in);
      config conf = new config();

      Owner os = new Owner();
      os.viewOwnerDetails();
      int oId = -1;
    
      while (true) {
        System.out.print("Enter the ID of the Owner: ");
        
      if (sc.hasNextInt()) {
          oId = sc.nextInt();
            
      String osql = "SELECT o_id FROM tbl_owner WHERE o_id = ?";
      if (conf.getSingleValue(osql, oId) != 0) {
          break;
     } else {
     System.out.println("Owner does not exist. Please try again.");
        }
     } else {
     System.out.println("Invalid input. Please enter a valid number for Owner ID.");
            sc.next();
        }
    }
      sc.nextLine(); 
      System.out.print("Enter Pet Name: ");
      String pname = sc.next();
      System.out.print("Enter Pet Breed: ");
      String pbreed = sc.next();
      sc.nextLine(); 
      System.out.print("Enter Pet Age: ");
      String page = sc.nextLine();

      String qry = "INSERT INTO tbl_pet (o_id, p_name, p_breed, p_age, o_id) VALUES (?, ?, ?, ?, ?)";
      conf.addRecord(qry, oId, pname, pbreed, page, oId);
      
    }

    

    public void viewPetDetails(){
        
      String qry = "SELECT * FROM tbl_pet";
      String[]hdrs = {"PET ID", "PET NAME", "PET BREED", "PET AGE"};
      String []clms = {"P_id", "p_name", "p_breed", "p_age"};
      config  conf = new config();
      conf.viewRecords(qry, hdrs, clms);  
    
}

 public void updatePetDetails() {
    Scanner sc = new Scanner(System.in);
    config conf = new config();

    int ownerId = -1;
    while (true) {
        System.out.print("Enter the ID of the Owner: ");
     if (sc.hasNextInt()) {
         ownerId = sc.nextInt();
         String ownerSql = "SELECT o_id FROM tbl_owner WHERE o_id = ?";
     if (conf.getSingleValue(ownerSql, ownerId) != 0) {
         break;
     } else {
             System.out.println("Owner does not exist. Please try again.");
            }
     } else {
            System.out.println("Invalid input. Please enter a valid number for Owner ID.");
            sc.next();
        }
    }

    System.out.println("\nPets of the selected Owner:");
    String petSql = "SELECT p_id, p_name FROM tbl_pet WHERE o_id = " + ownerId;
    String[] columnHeaders = {"Pet ID", "Pet Name"};
    String[] columnNames = {"p_id", "p_name"};
    conf.viewRecords(petSql, columnHeaders, columnNames);

    int petId = -1;
    while (true) {
        System.out.print("Enter the ID of the Pet: ");
        if (sc.hasNextInt()) {
            petId = sc.nextInt();
            String petSqlCheck = "SELECT p_id FROM tbl_pet WHERE p_id = ? AND o_id = ?";
        if (conf.getSingleValue(petSqlCheck, petId, ownerId) != 0) {
            break;
        } else {
                System.out.println("Pet does not belong to the selected owner. Please try again.");
            }
        } else {
            System.out.println("Invalid input. Please enter a valid number for Pet ID.");
            sc.next();
        }
    }

    sc.nextLine();
    System.out.print("Enter new Pet Name: ");
    String pname = sc.nextLine();
    System.out.print("Enter new Pet Breed: ");
    String pbreed = sc.nextLine();
    System.out.print("Enter new Pet Age: ");
    String page = sc.nextLine();

    String updateSql = "UPDATE tbl_pet SET p_name = ?, p_breed = ?, p_age = ? WHERE p_id = ?";
    conf.updateRecord(updateSql, pname, pbreed, page, petId);

    System.out.println("Pet details updated successfully!");
}

    
    private void deletePetDetails(){
    
        Scanner sc = new Scanner(System.in);
        config conf = new config();

        System.out.print("Enter Pet ID to delete: ");
        int id = sc.nextInt();
        
            while(conf.getSingleValue("SELECT p_id FROM tbl_pet WHERE p_id = ?",id)==0){
            System.out.println("Selected ID doesn't exist!");
            System.out.print("Select Pet ID Again: ");
            id = sc.nextInt();     
        }
        
        String qry = "DELETE FROM tbl_pet WHERE p_id = ?";
        conf.deleteRecord(qry, id);      
}

    
    
    
}
