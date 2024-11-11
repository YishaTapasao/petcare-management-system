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
        
        System.out.print("Enter Selection: ");        
        int action = -1;

        while (action < 1 || action > 5) {
              System.out.print("Enter Action: ");
        
        if (sc.hasNextInt()) {
           action = sc.nextInt();   
           
        if (action < 1 || action > 5) {        
            System.out.println("Invalid input. Please choose a number between 1 and 5.");
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
  
    public void addPetDetails(){
      Scanner sc = new Scanner(System.in);

      System.out.print("Enter Pet Name: ");
      String pname = sc.next();
      System.out.print("Enter Pet Breed: ");
      String pbreed = sc.next();
      sc.nextLine();
      System.out.print("Enter Pet Age: ");
      String page = sc.nextLine();  

      String qry = "INSERT INTO tbl_pet (p_name, p_breed, p_age) VALUES (?, ?, ?)";
      config conf = new config();
      conf.addRecord(qry, pname, pbreed, page);
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
