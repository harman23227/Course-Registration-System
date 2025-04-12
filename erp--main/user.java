import java.util.Scanner;

abstract class user  implements useraction {
    protected String email;
    private Course course;
    public String name;
    public String id;
    public user(String id,String name,String email) {
        this.name = name;
        this.id = id;
        this.email=email;
    }

    public abstract void displayinfo();
    public abstract void displayMenu();
    public void viewcoursedetail() {
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter course code: ");
        String coursecode=scanner.nextLine();
        course=database.getinstance().getcourse(coursecode);
        if(course!=null) {
            course.displayDetails();
        } else {
            System.out.println("No course found with code "+coursecode+".");
        }
    }
}