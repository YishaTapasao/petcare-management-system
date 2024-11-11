package petcare.management.system;

import java.util.Scanner;

public class Reports {

    public void generateReports() {
        Scanner sc = new Scanner(System.in);
        String response;
        
        do {
            System.out.println("\n------------------------------");
            System.out.println("REPORT GENERATOR");
            System.out.println("1. OWNER REPORT");
            System.out.println("2. PET REPORT");
            System.out.println("3. OWNER WITH PET REPORT");
            System.out.println("4. TODAY'S APPOINTMENT REPORT");
            System.out.println("5. WEEKLY APPOINTMENT REPORT");
            System.out.println("6. MONTHLY APPOINTMENT REPORT");
            System.out.println("7. Exit");
            
            System.out.print("Enter Selection: ");
            
            int choice = sc.nextInt();
            
            switch (choice) {
                case 1:
                    ownerReport();
                    break;
                case 2:
                    petReport();
                    break;
                case 3:
                    ownerWithPetReport();
                    break;
                case 4:
                    todayAppointmentReport();
                    break;
                case 5:
                    weeklyAppointmentReport();
                    break;
                case 6:
                    monthlyAppointmentReport();
                    break;
                case 7:
                    System.out.println("Exiting the report generator...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            
            System.out.println("Do you want to continue? yes/no: ");
            response = sc.next();
            
        } while(response.equalsIgnoreCase("yes"));
    }

    public void ownerReport() {
        String qry = "SELECT * FROM tbl_owner";
        String[] hdrs = {"OWNER ID", "FIRST NAME", "LAST NAME", "CONTACT NUMBER", "EMAIL"};
        String[] clms = {"o_id", "o_fname", "o_lname", "o_contact", "o_email"};
        
        config conf = new config();
        conf.viewRecords(qry, hdrs, clms);
    }

    public void petReport() {
        String qry = "SELECT * FROM tbl_pet";
        String[] hdrs = {"PET ID", "PET NAME", "PET BREED", "PET AGE"};
        String[] clms = {"p_id", "p_name", "p_breed", "p_age"};
        
        config conf = new config();
        conf.viewRecords(qry, hdrs, clms);
    }

    public void ownerWithPetReport() {
        String qry = "SELECT tbl_owner.o_id, tbl_owner.o_fname, tbl_owner.o_lname, tbl_pet.p_id, tbl_pet.p_name " +
                     "FROM tbl_owner " +
                     "LEFT JOIN tbl_pet ON tbl_owner.o_id = tbl_pet.p_id";
        String[] hdrs = {"OWNER ID", "OWNER FIRST NAME", "OWNER LAST NAME", "PET ID", "PET NAME"};
        String[] clms = {"o_id", "o_fname", "o_lname", "p_id", "p_name"};
        
        config conf = new config();
        conf.viewRecords(qry, hdrs, clms);
    }

    public void todayAppointmentReport() {
        String qry = "SELECT tbl_owner.o_id, tbl_owner.o_fname, tbl_owner.o_lname, tbl_pet.p_id, tbl_pet.p_name, " +
                     "tbl_appointment.date, tbl_appointment.time, tbl_appointment.appointment_id " +
                     "FROM tbl_appointment " +
                     "LEFT JOIN tbl_owner ON tbl_owner.o_id = tbl_appointment.o_id " +
                     "LEFT JOIN tbl_pet ON tbl_pet.p_id = tbl_appointment.p_id " +
                     "WHERE tbl_appointment.date = CURDATE()";
        String[] hdrs = {"APPOINTMENT ID", "OWNER ID", "OWNER FIRST NAME", "OWNER LAST NAME", "PET ID", "PET NAME", "APPOINTMENT DATE", "APPOINTMENT TIME"};
        String[] clms = {"appointment_id", "o_id", "o_fname", "o_lname", "p_id", "p_name", "date", "time"};
        
        config conf = new config();
        conf.viewRecords(qry, hdrs, clms);
    }

    public void weeklyAppointmentReport() {
        String qry = "SELECT tbl_owner.o_id, tbl_owner.o_fname, tbl_owner.o_lname, tbl_pet.p_id, tbl_pet.p_name, " +
                     "tbl_appointment.date, tbl_appointment.time, tbl_appointment.appointment_id " +
                     "FROM tbl_appointment " +
                     "LEFT JOIN tbl_owner ON tbl_owner.o_id = tbl_appointment.o_id " +
                     "LEFT JOIN tbl_pet ON tbl_pet.p_id = tbl_appointment.p_id " +
                     "WHERE tbl_appointment.date BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL 7 DAY)";
        String[] hdrs = {"APPOINTMENT ID", "OWNER ID", "OWNER FIRST NAME", "OWNER LAST NAME", "PET ID", "PET NAME", "APPOINTMENT DATE", "APPOINTMENT TIME"};
        String[] clms = {"appointment_id", "o_id", "o_fname", "o_lname", "p_id", "p_name", "date", "time"};
        
        config conf = new config();
        conf.viewRecords(qry, hdrs, clms);
    }

    public void monthlyAppointmentReport() {
        String qry = "SELECT tbl_owner.o_id, tbl_owner.o_fname, tbl_owner.o_lname, tbl_pet.p_id, tbl_pet.p_name, " +
                     "tbl_appointment.date, tbl_appointment.time, tbl_appointment.appointment_id " +
                     "FROM tbl_appointment " +
                     "LEFT JOIN tbl_owner ON tbl_owner.o_id = tbl_appointment.o_id " +
                     "LEFT JOIN tbl_pet ON tbl_pet.p_id = tbl_appointment.p_id " +
                     "WHERE tbl_appointment.date BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL 1 MONTH)";
        String[] hdrs = {"APPOINTMENT ID", "OWNER ID", "OWNER FIRST NAME", "OWNER LAST NAME", "PET ID", "PET NAME", "APPOINTMENT DATE", "APPOINTMENT TIME"};
        String[] clms = {"appointment_id", "o_id", "o_fname", "o_lname", "p_id", "p_name", "date", "time"};
        
        config conf = new config();
        conf.viewRecords(qry, hdrs, clms);
    }
}

