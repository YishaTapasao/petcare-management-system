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
                pt.viewPetDetails();
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
  
//    public void addPetDetails(){
//      Scanner sc = new Scanner(System.in);
//      
//      do{
//      System.out.print("Enter Owner ID: ");
//      String oid = sc.next();
//      String osql = "SELECT o_id FROM tbl_owner WHERE o_id = ?";
//      
//      }while(conf.getSingleValue(osql, oId)==0);
// 
//         System.out.println("Owner does not exist, Select Again: ");
//         oId = sc.nextInt();  
//   
//      System.out.print("Enter Pet Name: ");
//      String pname = sc.next();
//      System.out.print("Enter Pet Breed: ");
//      String pbreed = sc.next();
//      sc.nextLine();
//      System.out.print("Enter Pet Age: ");
//      String page = sc.nextLine();  
//
//      String qry = "INSERT INTO tbl_pet (p_name, p_breed, p_age) VALUES (?, ?, ?)";
//      config conf = new config();
//      conf.addRecord(qry, pname, pbreed, page);
//}
        
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

    private void updatePetDetails(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.println("Enter Pet ID to update: ");
        int id = sc.nextInt();
        
     while(conf.getSingleValue("SELECT p_id FROM tbl_pet WHERE p_id = ?",id)==0){
        System.out.println("Selected ID doesn't exist!");
        System.out.println("Select Pet ID Again: ");
        id = sc.nextInt();     
        }
        
        System.out.print("New Pet Name: ");
        String pname = sc.next();
        System.out.print("New Pet Breed: ");
        String pbreed = sc.next();
        sc.nextLine();
        System.out.print("New Pet age: ");
        String page = sc.nextLine();
   
       
        String qry = "UPDATE tbl_pet SET p_name = ?, p_breed = ?, p_age = ? WHERE p_id = ?";
        conf.updateRecord(qry, pname, pbreed, page, id);
        
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
