package petcare.management.system;

import java.util.Scanner;

public class PetcareManagementSystem {

 public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        boolean exit = true;
    
        do{    
            System.out.println("\n------------------------------");
            System.out.println("WELCOME TO THE PETCARE CLINIC");
            System.out.println("1. OWNER");        
            System.out.println("2. PET");
            System.out.println("3. APPOINTMENT");
            System.out.println("4. REPORTS");
            System.out.println("5. EXIT");

            System.out.print("Enter Action: ");
            int action = -1;

            while (true) {
                if (sc.hasNextInt()) {
                    action = sc.nextInt();

                if (action < 1 || action > 5) {
                    System.out.println("You have input an incorrect number. Please choose numbers from 1-5.");
                    System.out.print("Enter Action: ");                                      
                } else {                    
                        break;
                    }                
                
                
                } else {
                    System.out.println("Invalid input. Please enter a number from 1 to 5.");
                    sc.next();
                    System.out.print("Enter Action: ");
                }
            }
            
            
            switch(action){
                
                case 1:
                    Owner os = new Owner();
                    os.oDetails();              
                    break;
                 
                case 2:
                    Pet pt = new Pet();
                    pt.pDetails();
                    break;
                
                case 3:
                    Appointment appt = new Appointment();
                    appt.appointmentDetails(); 
                    break;

                case 4:
                    Reports reports = new Reports();
                    reports.generateReports();                                      
                    break;
                    
                case 5:
                    System.out.println("Exit Selected...type 'yes' to continue: ");
                    String resp = sc.next();
                    if(resp.equalsIgnoreCase("yes")){
                        exit = false;
                    }
                    break;
                
            }
            System.out.println("THANK YOU!!!");
            
        } while(exit);


    }
    
}
