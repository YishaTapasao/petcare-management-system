package petcare.management.system;

import java.util.Scanner;

public class Appointment {

    public void appointmentDetails() {
        Scanner sc = new Scanner(System.in);
        String response;
    do{
        System.out.println("\n------------------------------");
        System.out.println("APPOINMENT DETAIL");
        System.out.println("1. ADD APPOINMENT DETAIL");               
        System.out.println("2. VIEW APPOINTMENT DETAIL");        
        System.out.println("3. UPDATE APPOINMENT DETAIL");        
        System.out.println("4. DELETE APPOINTMENT DETAIL");        
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
        
        Appointment appt = new Appointment();
        
        
        switch(action){
            
            case 1:
                appt.addAppointmentDetails();
                appt.viewAppointmentDetails();

                break;
                
            case 2:
                appt.viewAppointmentDetails();
                break;
                
            case 3:
                appt.viewAppointmentDetails();
                appt.updateAppointmentDetails();
                appt.viewAppointmentDetails();
               break;
                
            case 4:
                appt.viewAppointmentDetails();
                appt.deleteAppointmentDetails();
                appt.viewAppointmentDetails();

                break;
                
            case 5:
                
                break;
                
                
        }
        
        System.out.println("Do you want to continue? yes/no: ");
        response = sc.next();
        
    }while(response.equalsIgnoreCase("yes"));  
    }
    
 public void addAppointmentDetails() {
    Scanner sc = new Scanner(System.in);
    config conf = new config();
    Owner os = new Owner();
    os.viewOwnerDetails();
    
    System.out.print("Enter the ID of the Owner: ");
    int oId = sc.nextInt();

    String osql = "SELECT o_id FROM tbl_owner WHERE o_id = ?";
    while (conf.getSingleValue(osql, oId) == 0) {
          System.out.println("Owner does not exist, Select Again: ");
          oId = sc.nextInt();
    }

    System.out.println("\nPets of the selected Owner:");
    String petsql = "SELECT p_id, p_name FROM tbl_pet WHERE o_id = " + oId;
    String[] columnHeaders = {"Pet ID", "Pet Name"};
    String[] columnNames = {"p_id", "p_name"};
    conf.viewRecords(petsql, columnHeaders, columnNames);

    System.out.print("Enter the ID of the Pet: ");
    int pId = sc.nextInt();

    String psql = "SELECT p_id FROM tbl_pet WHERE p_id = ? AND o_id = ?";
    while (conf.getSingleValue(psql, pId, oId) == 0) {
        System.out.println("Pet does not belong to the selected owner. Select Again: ");
        pId = sc.nextInt();
    }

    sc.nextLine();
    System.out.print("Enter Date (yyyy-mm-dd): ");
    String date = sc.nextLine();
    System.out.print("Enter Time: ");
    String time = sc.nextLine();
    System.out.print("Enter Reason: ");
    String reason = sc.nextLine();

    String qry = "INSERT INTO tbl_appointment (o_id, p_id, date, time, reason) VALUES (?, ?, ?, ?, ?)";
    conf.addRecord(qry, oId, pId, date, time, reason);
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


 public void updateAppointmentDetails() {
    Scanner sc = new Scanner(System.in);
    config conf = new config();

    int id = -1;

    while (true) {
        System.out.print("Enter Appointment ID to update: ");
        
        if (sc.hasNextInt()) {
            id = sc.nextInt();

            String asql = "SELECT appointment_id FROM tbl_appointment WHERE appointment_id = ?";
            if (conf.getSingleValue(asql, id) != 0) {
                break;
            } else {
                System.out.println("Appointment does not exist. Please try again.");
            }
        } else {
            System.out.println("Invalid input. Please enter a valid number for Appointment ID.");
            sc.next();
        }
    }

    sc.nextLine();
    System.out.print("Enter new Appointment Date (yyyy-mm-dd): ");
    String newDate = sc.nextLine();
    
    System.out.print("Enter new Appointment Time: ");
    String newTime = sc.nextLine();
    
    System.out.print("Enter new Reason for Appointment: ");
    String newReason = sc.nextLine();

    String qry = "UPDATE tbl_appointment SET date = ?, time = ?, reason = ? WHERE appointment_id = ?";
    conf.updateRecord(qry, newDate, newTime, newReason, id);

    System.out.println("Appointment updated successfully.");
}


  private void deleteAppointmentDetails() {
    Scanner sc = new Scanner(System.in);
    config conf = new config();

    System.out.print("Enter Appointment ID to delete: ");
    int id = sc.nextInt();
    
    while (conf.getSingleValue("SELECT appointment_id FROM tbl_appointment WHERE appointment_id = ?", id) == 0) {
        System.out.println("Selected Appointment ID doesn't exist!");
        System.out.print("Select Appointment ID Again: ");
        id = sc.nextInt();
    }
    
    String qry = "DELETE FROM tbl_appointment WHERE appointment_id = ?";
    conf.deleteRecord(qry, id); 
    
    System.out.println("Appointment deleted successfully.");
}

    
}