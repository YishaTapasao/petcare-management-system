package petcare.management.system;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Scanner;


public class Reports {

        public void generateReports(){
        Scanner sc = new Scanner(System.in);
        String response;
    do{
        System.out.println("\n------------------------------");
        System.out.println("OWNER DETAIL");
        System.out.println("1. OWNER REPORT");               
        System.out.println("2. PET REPORT");        
        System.out.println("3. OWNER WITH PET REPORT");        
        System.out.println("4. APPOINTMENT REPORT(ALL)");        
        System.out.println("5. APPOINTMENT REPORT (TODAY)");      
        System.out.println("6. APPOINTMENT REPORT (WEEK)");      
        System.out.println("7. APPOINTMENT REPORT (MONTH)");    
        System.out.println("8. EXIT");
        
        int action = -1;

        while (action < 1 || action > 8) {
              System.out.print("Enter Action: ");
        
        if (sc.hasNextInt()) {
           action = sc.nextInt();   
           
        if (action < 1 || action > 8) {        
            System.out.println("Invalid input. Please choose a number from 1 to 8.");
        }
            
        } else {
            System.out.println("Invalid input. Please enter a number.");
            sc.next();
        }
        
  }
        
        Pet pt = new Pet();
        
        switch(action){
            
            case 1:
                viewOwnerDetails();
                break;
                
            case 2:
                viewPetDetails();
                break;
                
            case 3:
                Owner os = new Owner();
                os.viewOwnerDetails();
                viewOwnerWithPetDetails();
                break;
                
            case 4:
                viewAppointmentDetails(); 
                break;
                
            case 5:
                viewTodayAppointments();

                break;
                
            case 6:
                viewThisWeekAppointments();
                break;
                
            case 7:
                viewThisMonthAppointments();
                break;
                
            case 8:
                break;
                
        }
        
        System.out.println("Do you want to continue? yes/no: ");
        response = sc.next();
        
    }while(response.equalsIgnoreCase("yes"));  
    }
    
    public void viewOwnerDetails(){
      String qry = "SELECT * FROM tbl_owner";
      String[]hdrs = {"OWNER ID", "FIRST NAME", "LAST NAME", "ADDRESS", "CONTACT", "EMAIL"};
      String []clms = {"o_id", "o_fname", "o_lname", "o_address", "o_contact", "o_email"};
      config  conf = new config();
      conf.viewRecords(qry, hdrs, clms);               
}

    public void viewPetDetails(){        
      String qry = "SELECT * FROM tbl_pet";
      String[]hdrs = {"PET ID", "PET NAME", "PET BREED", "PET AGE"};
      String []clms = {"P_id", "p_name", "p_breed", "p_age"};
      config  conf = new config();
      conf.viewRecords(qry, hdrs, clms);  
    
}
    
//    public void viewOwnerWithPetDetails() {
//      String qry = "SELECT tbl_owner.o_id, tbl_owner.o_fname, tbl_owner.o_lname, tbl_pet.p_id, tbl_pet.p_name, tbl_pet.p_breed " +
//                   "FROM tbl_owner " +
//                   "LEFT JOIN tbl_pet ON tbl_owner.o_id = tbl_pet.p_id";
//      String[] hdrs = {"OWNER ID", "OWNER FIRST NAME", "OWNER LAST NAME", "PET ID", "PET NAME", "PET BREED"};
//      String[] clms = {"o_id", "o_fname", "o_lname", "p_id", "p_name", "p_breed"};
//      config conf = new config();
//      conf.viewRecords(qry, hdrs, clms);
//
//}
// public void viewOwnerWithPetDetails() {
//    String qry = "SELECT tbl_owner.o_id, tbl_owner.o_fname, tbl_owner.o_lname, tbl_pet.p_id, tbl_pet.p_name, tbl_pet.p_breed " +
//                 "FROM tbl_owner " +
//                 "LEFT JOIN tbl_pet ON tbl_owner.o_id = tbl_pet.o_id"; 
//    String[] hdrs = {"OWNER ID", "OWNER FIRST NAME", "OWNER LAST NAME", "PET ID", "PET NAME", "PET BREED"};
//    String[] clms = {"o_id", "o_fname", "o_lname", "p_id", "p_name", "p_breed"};
//    config conf = new config();
//    conf.viewRecords(qry, hdrs, clms);
//}
       
    
    public void viewOwnerWithPetDetails() {
    Scanner sc = new Scanner(System.in);
    int ownerId;

    while (true) {
        System.out.print("Enter the Owner ID: ");
        if (sc.hasNextInt()) {
            ownerId = sc.nextInt();   
       
        if (ownerId > 0) {
                break;
        } else {
                System.out.println("You have input an incorrect number. Please enter a positive Owner ID.");
            }
        } else {
            System.out.println("Invalid input. Please enter a valid Owner ID.");
            sc.next();
        }
    }

      String qry = "SELECT tbl_owner.o_id, tbl_owner.o_fname, tbl_owner.o_lname, tbl_pet.p_id, tbl_pet.p_name, tbl_pet.p_breed " +
                 "FROM tbl_owner " +
                 "LEFT JOIN tbl_pet ON tbl_owner.o_id = tbl_pet.o_id " +
                 "WHERE tbl_owner.o_id = " + ownerId;
    
      String[] hdrs = {"OWNER ID", "OWNER FIRST NAME", "OWNER LAST NAME", "PET ID", "PET NAME", "PET BREED"};
      String[] clms = {"o_id", "o_fname", "o_lname", "p_id", "p_name", "p_breed"};   
      config conf = new config();
      conf.viewRecords(qry, hdrs, clms);
}

    

        
    
    public void viewAppointmentDetails() {
      String qry = "SELECT tbl_owner.o_id, tbl_owner.o_fname, tbl_owner.o_lname, tbl_pet.p_id, tbl_pet.p_name, tbl_appointment.date,tbl_appointment.appointment_id, tbl_appointment.time " +
                 "FROM tbl_appointment " +
                 "LEFT JOIN tbl_owner ON tbl_owner.o_id = tbl_appointment.o_id " +
                 "LEFT JOIN tbl_pet ON tbl_pet.p_id = tbl_appointment.p_id";
    
      String[] hdrs = {"APPOINTMENT ID", "OWNER ID", "OWNER FIRST NAME", "OWNER LAST NAME0", "PET ID", "PET NAME", "APPOINTMENT DATE", "APPOINTMENT TIME"};
      String[] clms = {"appointment_id","o_id", "o_fname", "o_lname", "p_id", "p_name", "date", "time"};
      config conf = new config();
      conf.viewRecords(qry, hdrs, clms);
    
}
    
    public void viewTodayAppointments() {
      LocalDate today = LocalDate.now();
      String qry = "SELECT appointment_id, tbl_owner.o_id, tbl_owner.o_fname, tbl_owner.o_lname, tbl_pet.p_id, tbl_pet.p_name, tbl_appointment.date, tbl_appointment.time " +
             "FROM tbl_appointment " +
             "LEFT JOIN tbl_owner ON tbl_appointment.o_id = tbl_owner.o_id " +
             "LEFT JOIN tbl_pet ON tbl_appointment.p_id = tbl_pet.p_id " +
             "WHERE tbl_appointment.date = '" + today + "'";
      String[] hdrs = {"APPOINTMENT ID", "OWNER ID", "OWNER FIRST NAME", "OWNER LAST NAME", "PET ID", "PET NAME", "DATE", "TIME"};
      String[] clms = {"appointment_id", "o_id", "o_fname", "o_lname", "p_id", "p_name", "date", "time"};
      config conf = new config();
      conf.viewRecords(qry, hdrs, clms);
    }  
  
    public void viewThisWeekAppointments() {
      LocalDate today = LocalDate.now();
      LocalDate startOfWeek = today.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY));
      LocalDate endOfWeek = today.with(TemporalAdjusters.nextOrSame(java.time.DayOfWeek.SUNDAY));
     String qry = "SELECT tbl_appointment.appointment_id, tbl_owner.o_id, tbl_owner.o_fname, tbl_owner.o_lname, tbl_pet.p_id, tbl_pet.p_name, tbl_appointment.date, tbl_appointment.time " +
             "FROM tbl_appointment " +
             "LEFT JOIN tbl_owner ON tbl_appointment.o_id = tbl_owner.o_id " +
             "LEFT JOIN tbl_pet ON tbl_appointment.p_id = tbl_pet.p_id " +
             "WHERE tbl_appointment.date BETWEEN '" + startOfWeek + "' AND '" + endOfWeek + "'";
      String[] hdrs = {"APPOINTMENT ID", "OWNER ID", "OWNER FIRST NAME", "OWNER LAST NAME", "PET ID", "PET NAME", "DATE", "TIME"};
      String[] clms = {"appointment_id", "o_id", "o_fname", "o_lname", "p_id", "p_name", "date", "time"};
      config conf = new config();
      conf.viewRecords(qry, hdrs, clms);
    }
    
    public void viewThisMonthAppointments() {
      LocalDate today = LocalDate.now();
      LocalDate startOfMonth = today.with(TemporalAdjusters.firstDayOfMonth());
      LocalDate endOfMonth = today.with(TemporalAdjusters.lastDayOfMonth());
      String qry = "SELECT tbl_appointment.appointment_id, tbl_owner.o_id, tbl_owner.o_fname, tbl_owner.o_lname, tbl_pet.p_id, tbl_pet.p_name, tbl_appointment.date, tbl_appointment.time " +
             "FROM tbl_appointment " +
             "LEFT JOIN tbl_owner ON tbl_appointment.o_id = tbl_owner.o_id " +
             "LEFT JOIN tbl_pet ON tbl_appointment.p_id = tbl_pet.p_id " +
             "WHERE tbl_appointment.date BETWEEN '" + startOfMonth + "' AND '" + endOfMonth + "'";
      String[] hdrs = {"APPOINTMENT ID", "OWNER ID", "OWNER FIRST NAME", "OWNER LAST NAME", "PET ID", "PET NAME", "DATE", "TIME"};
      String[] clms = {"appointment_id", "o_id", "o_fname", "o_lname", "p_id", "p_name", "date", "time"};
      config conf = new config();
      conf.viewRecords(qry, hdrs, clms);
    }    
    
    
}