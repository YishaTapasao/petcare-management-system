package petcare.management.system;

import java.util.Scanner;

public class Owner {
    
    public void oDetails(){
   
        Scanner sc = new Scanner(System.in);
        String response;
        
    do{
        System.out.println("\n------------------------------");
        System.out.println("OWNER DETAIL");
        System.out.println("1. ADD OWNER DETAIL");               
        System.out.println("2. VIEW OWNER DETAIL");        
        System.out.println("3. UPDATE OWNER DETAIL");        
        System.out.println("4. DELETE OWNER DETAIL");        
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
        
        
        Owner os = new Owner();
        
        switch(action){
            
            case 1:
                os.addOwnerDetails();
                os.viewOwnerDetails();
                break;
                
            case 2:
                os.viewOwnerDetails();
                break;
                
            case 3:
                os.viewOwnerDetails();
                os.updateOwnerDetails();
                os.viewOwnerDetails();
                break;
                
            case 4:
                os.viewOwnerDetails();
                os.deleteOwnerDetails();
                os.viewOwnerDetails();               
                break;
                
            case 5:
                
                break;
                
                
        }
        
        System.out.println("Do you want to continue? yes/no: ");
        response = sc.next();
        
    }while(response.equalsIgnoreCase("yes"));
    
}

    public void addOwnerDetails(){
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter First Name: ");
        String fname = sc.nextLine();
        System.out.print("Enter last Name: ");
        String lname = sc.nextLine();
        System.out.print("Enter Address: ");
        String address = sc.nextLine();
        System.out.print("Enter Contact Number: ");
        String contact = sc.nextLine();
        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        String qry = "INSERT INTO tbl_owner(o_fname, o_lname, o_address, o_contact, o_email)VALUES(?,?,?,?,?)";
        config conf = new config();
        
        conf.addRecord(qry, fname, lname, address, contact, email);
    }
    
    public void viewOwnerDetails(){
      String qry = "SELECT * FROM tbl_owner";
      String[]hdrs = {"OWNER ID", "FIRST NAME", "LAST NAME", "ADDRESS", "CONTACT", "EMAIL"};
      String []clms = {"o_id", "o_fname", "o_lname", "o_address", "o_contact", "o_email"};
      config  conf = new config();
      conf.viewRecords(qry, hdrs, clms);               
    }
    
    
    private void updateOwnerDetails(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();
        System.out.println("Enter Owner ID to update: ");
        int id = sc.nextInt();
        
        while(conf.getSingleValue("SELECT o_id FROM tbl_owner WHERE o_id = ?",id)==0){
            System.out.println("Selected ID doesn't exist!");
            System.out.println("Select Owner ID Again: ");
            id = sc.nextInt();     
        }
        
        sc.nextLine();
        System.out.print("New First Name: ");
        String fname = sc.nextLine();
        System.out.print("New last Name: ");
        String lname = sc.nextLine();
        System.out.print("New Address: ");
        String address = sc.nextLine();
        System.out.print("New Contact Number: ");
        String contact = sc.nextLine();
        System.out.print("New Email: ");
        String email = sc.next();
       
        String qry = "UPDATE tbl_owner SET o_fname = ?, o_lname = ?, o_address = ?, o_contact = ?, o_email = ? WHERE o_id = ?";
        conf.updateRecord(qry,fname, lname, address, contact, email, id);
    }
    
    
    public void deleteOwnerDetails(){
        Scanner sc = new Scanner(System.in);
        config conf = new config();

        System.out.println("Enter Owner ID to delete: ");
        int id = sc.nextInt();
        
            while(conf.getSingleValue("SELECT o_id FROM tbl_owner WHERE o_id = ?",id)==0){
            System.out.println("Selected ID doesn't exist!");
            System.out.println("Select Owner ID Again: ");
            id = sc.nextInt();     
        }
        
        String qry = "DELETE FROM tbl_owner WHERE o_id = ?";
        conf.deleteRecord(qry, id);       


}
    
}
