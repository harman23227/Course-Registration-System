import java.util.List;
import java.util.Random;
import java.util.Scanner;

class professor extends user {
    private String expertise;

    public professor(String id,String name,String email,String expertise) {
        super(id,name,email);
        this.expertise=expertise;
    }

    @Override
    public void displayMenu() {
        Scanner scanner=new Scanner(System.in);
        while(true) {
            System.out.println("\nPM");
            System.out.println("1. View Course Details");
            System.out.println("2. View Complaints");
            System.out.println("3. View Enrolled Student Count");
            System.out.println("4. View Feedback");
            System.out.println("5. Manage Results");
            System.out.println("6. Exit");

            System.out.print("Enter your choice (1-6): ");
            int choice=scanner.nextInt();
            scanner.nextLine();

            switch(choice) {
                case 1:
                    viewcoursedetail();
                    break;
                case 2:
                    viewcomplaints();
                    break;
                case 3:
                    System.out.println("Enrolled students: "+enrolledstudentcount());
                    break;
                case 4:
                    viewfeedback();
                    break;
                case 5:
                    manageresults();
                    break;
                case 6:
                    System.out.println("Exiting Professor Menu.");
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private void viewcomplaints() {
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter course code: ");
        String coursecode=scanner.nextLine();

        List<complaint> complaints=database.getinstance().getcoursecomplaints(coursecode);
        if(!complaints.isEmpty()) {
            for(complaint complaint : complaints) {
                System.out.println("Complaint ID: "+complaint.getid());
                System.out.println("Description: "+complaint.getdescription());
                System.out.println("Status: "+complaint.getstatus());
                System.out.println();
            }
        } else {
            System.out.println("No complaints for course "+coursecode+".");
        }
    }

    private int enrolledstudentcount() {
        return new Random().nextInt(200); // Placeholder implementation
    }

    private void viewfeedback() {
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter course code: ");
        String coursecode=scanner.nextLine();

        List<database.feedbackitem> feedback=database.getinstance().getfeedbackforcourse(coursecode);
        if(feedback.isEmpty()) {
            System.out.println("No feedback available for course "+coursecode);
        } else {
            System.out.println("Feedback for course "+coursecode+":");
            for(database.feedbackitem item : feedback) {
                System.out.println(item);
            }
        }
    }

    private void manageresults() {
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter student email: ");
        String studentemail=scanner.nextLine();
        System.out.print("Enter course code: ");
        String coursecode=scanner.nextLine();
        System.out.print("Enter grade: ");
        String grade=scanner.nextLine();
        database.getinstance().addresult(studentemail,coursecode,grade);
        System.out.println("Result added successfully.");
    }

    @Override
    public void displayinfo() {
        System.out.println("Professor Information:");
        System.out.println("Email: "+email);
        System.out.println("Expertise: "+expertise);
    }
}
