import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class student extends user {
    private String academicstanding;
    private List<String> enrolledcourses;

    public student(String name,String academicstanding,String email) {
        super(generatestudentid(),name,email);
        this.academicstanding=academicstanding;
        this.enrolledcourses=new ArrayList<>();
    }

    private static String generatestudentid() {
        return "s"+System.currentTimeMillis(); // Generate a unique ID based on current timestamp
    }

    public void setacademicstanding(String academicstanding) {
        this.academicstanding=academicstanding;
    }

    public void addcourse(String coursecode) throws coursefullexception, dropdeadlinepassedexception {
        Course course=database.getinstance().getcourse(coursecode);
        if(course!=null) {
            if(LocalDate.now().isAfter(course.getAddDropDeadline())) {
                throw new dropdeadlinepassedexception("Add/Drop deadline has passed for course "+coursecode);
            }
            if(course.getEnrolledStudents()>=course.getMaxCapacity()) {
                throw new coursefullexception("Course "+coursecode+" is already full");
            }
            enrolledcourses.add(coursecode);
            course.addEnrolledStudent();
            System.out.println("Added course "+coursecode+".");
        } else {
            System.out.println("Course not found.");
        }
    }

    public void dropcourse(String coursecode) throws dropdeadlinepassedexception {
        Course course=database.getinstance().getcourse(coursecode);
        if(course!=null) {
            if(LocalDate.now().isAfter(course.getAddDropDeadline())) {
                throw new dropdeadlinepassedexception("Add/Drop deadline has passed for course "+coursecode);
            }
            if(enrolledcourses.remove(coursecode)) {
                course.removeEnrolledStudent();
                System.out.println("Dropped course "+coursecode+".");
            } else {
                System.out.println("Course not found in enrolled courses.");
            }
        } else {
            System.out.println("Course not found.");
        }
    }

    @Override
    public void displayMenu() {
        Scanner scanner=new Scanner(System.in);
        while(true) {
            System.out.println("SM");
            System.out.println("1. View Course Details");
            System.out.println("2. Submit Complaint");
            System.out.println("3. View Result");
            System.out.println("4. Add/Drop Course");
            System.out.println("5. Provide Feedback");
            System.out.println("6. Exit");

            System.out.print("Enter choice: ");
            int choice=scanner.nextInt();
            scanner.nextLine();

            switch(choice) {
                case 1:
                    viewcoursedetail();
                    break;
                case 2:
                    submitcomplaint();
                    break;
                case 3:
                    viewresults();
                    break;
                case 4:
                    addordropcourse();
                    break;
                case 5:
                    providefeedback();
                    break;
                case 6:
                    System.out.println("Exiting Student Menu.");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void addordropcourse() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("1. Add Course");
        System.out.println("2. Drop Course");
        System.out.print("Enter choice: ");
        int choice=scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter course code: ");
        String coursecode=scanner.nextLine();

        try {
            switch(choice) {
                case 1:
                    addcourse(coursecode);
                    break;
                case 2:
                    dropcourse(coursecode);
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } catch(coursefullexception | dropdeadlinepassedexception e) {
            System.out.println("Error: "+e.getMessage());
        }
    }

    private void submitcomplaint() {
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter course code: ");
        String coursecode=scanner.nextLine();
        System.out.print("Enter complaint: ");
        String description=scanner.nextLine();

        complaint complaint=new complaint(description);
        database.getinstance().handlecomplaint(coursecode,complaint);
        System.out.println("Complaint submitted for: "+coursecode+".");
    }

    private void viewresults() {
        List<Result> results=database.getinstance().getstudentresults(this.email);
        if(results.isEmpty()) {
            System.out.println("No results available.");
        } else {
            System.out.println("Your results:");
            for(Result result : results) {
                System.out.println(result);
            }
        }
    }

    private void providefeedback() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("1: Rating system\n2: Comment feedback");
        System.out.print("Enter your choice: ");
        int choice=scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter the course code: ");
        String coursecode=scanner.nextLine();

        if(choice==1) {
            System.out.print("Enter the rating for this course (1-5): ");
            int rating=scanner.nextInt();
            feedback(coursecode,rating);
        } else if(choice==2) {
            System.out.print("Enter your comment for this course: ");
            String comment=scanner.nextLine();
            feedback(coursecode,comment);
        } else {
            System.out.println("Invalid choice.");
        }
    }

    private <T> void feedback(String coursecode,T feedbackdata) {
        database.getinstance().addfeedback(coursecode,feedbackdata);
        System.out.println("Feedback submitted successfully for course "+coursecode);
    }

    @Override
    public void displayinfo() {
        System.out.println("Student Information:");
        System.out.println("Name: "+getName());
        System.out.println("Email: "+email);
        System.out.println("Academic Standing: "+academicstanding);
        System.out.println("Enrolled Courses: "+String.join(", ",enrolledcourses));
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}