import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

class admin {
    public void displayMenu() {
        Scanner scanner=new Scanner(System.in);
        while(true) {
            System.out.println("\nAM");
            System.out.println("1. Add Course");
            System.out.println("2. Remove Course");
            System.out.println("3. View All Courses");
            System.out.println("4. View All Users");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");
            int choice=scanner.nextInt();
            scanner.nextLine();

            switch(choice) {
                case 1:
                    addcourse();
                    break;
                case 2:
                    removecourse();
                    break;
                case 3:
                    viewallcourses();
                    break;
                case 4:
                    viewallusers();
                    break;
                case 5:
                    System.out.println("Exiting Admin Menu.");
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private void addcourse() {
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter course code: ");
        String code=scanner.nextLine();
        System.out.print("Enter course title: ");
        String title=scanner.nextLine();
        System.out.print("Enter professor email: ");
        String professoremail=scanner.nextLine();
        System.out.print("Enter prerequisites: ");
        String prerequisites=scanner.nextLine();
        System.out.print("Enter timings: ");
        String timings=scanner.nextLine();
        System.out.print("Enter venue: ");
        String venue=scanner.nextLine();
        System.out.print("Enter credits: ");
        int credits=scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter add/drop deadline (YYYY-MM-DD): ");
        LocalDate deadline=LocalDate.parse(scanner.nextLine());
        System.out.print("Enter maximum capacity: ");
        int maxcapacity=scanner.nextInt();
        scanner.nextLine();

        Course course=new Course(code,title,professoremail,prerequisites,timings,venue,credits,deadline,maxcapacity);
        database.getinstance().addcourse(course);
        System.out.println("Course added successfully.");
    }

    private void removecourse() {
        Scanner scanner=new Scanner(System.in);
        System.out.print("remove course code : ");
        String code=scanner.nextLine();
        if(database.getinstance().removecourse(code)) {
            System.out.println("Course removed ");
        } else {
            System.out.println("Course not found.");
        }
    }

    private void viewallcourses() {
        List<Course> courses=database.getinstance().getallcourses();
        if(courses.isEmpty()) {
            System.out.println("No courses available.");
        } else {
            System.out.println("All Courses:");
            for(Course course : courses) {
                course.displayDetails();
                System.out.println();
            }
        }
    }

    private void viewallusers() {
        List<user> users=database.getinstance().getallusers();
        if(users.isEmpty()) {
            System.out.println("No users available.");
        } else {
            System.out.println("All Users:");
            for(user user : users) {
                user.displayinfo();
                System.out.println();
            }
        }
    }
}