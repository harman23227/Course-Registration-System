import java.util.*;
import java.time.LocalDate;
class coursefullexception extends Exception {
    public coursefullexception(String message) {
        super(message);
    }
}

class InvalidLoginException extends Exception {
    public InvalidLoginException(String message) {
        super(message);
    }
}

class dropdeadlinepassedexception extends Exception {
    public dropdeadlinepassedexception(String message) {
        super(message);
    }
}

// Interface for common user actions
interface UserAction {
    void displayMenu();
    void viewCourseDetail();
}
abstract class StudentProfessor {
    private String id;
    private String name;

    public StudentProfessor(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public abstract void displayInfo();

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

// Result class to store student grades
class Result {
    private String studentEmail;
    private String courseCode;
    private String grade;

    public Result(String studentEmail, String courseCode, String grade) {
        this.studentEmail = studentEmail;
        this.courseCode = courseCode;
        this.grade = grade;
    }

    // Getters and setters
    public String getStudentEmail() { return studentEmail; }
    public String getCourseCode() { return courseCode; }
    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    @Override
    public String toString() {
        return "Course: " + courseCode + ", Grade: " + grade;
    }
}

// TA class, extends Student (assuming Student class exists)



// Login class
class Login {
    private String email;
    private String password;

    public Login(String email, String password) throws InvalidLoginException {
        this.email = email;
        this.password = password;
        authenticateUser();
    }

    public Login(String password) throws InvalidLoginException {
        this.password = password;
        if (database.getinstance().isadmin(password)) {
            adminLogin();
        } else {
            throw new InvalidLoginException("Invalid admin password");
        }
    }

    private void authenticateUser() throws InvalidLoginException {
            database.getinstance();
        if (database.validatestudent(email, password)) {
            studentLogin();
        } else if (database.validateprofessor(email, password)) {
            professorLogin();
        } else {
            throw new InvalidLoginException("Invalid credentials");
        }
    }

    private void studentLogin() {
        System.out.println("Student login successful");
        user student = database.getinstance().getstudent(email);
        if (student instanceof TA) {
            ((TA)student).displayMenu();
        } else {
            student.displayMenu();
        }
    }

    private void professorLogin() {
        System.out.println("Professor login successful");
        database.getinstance().getprofessor(email).displayMenu();
    }

    private void adminLogin() {
        System.out.println("Admin login successful");
        admin admin = new admin();
        admin.displayMenu();
    }
}

// Signup class
class Signup {
    private Scanner scanner = new Scanner(System.in);

    public void signupUser() {
        System.out.println("1. Student Signup");
        System.out.println("2. Professor Signup");
        System.out.println("3. TA Signup");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1: signupStudent(); break;
            case 2: signupProfessor(); break;
            case 3: signupTA(); break;
            default: System.out.println("Invalid choice");
        }
    }

    private void signupStudent() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter academic year: ");
        String year = scanner.nextLine();
        System.out.print("Enter contact details: ");
        String contactDetails = scanner.nextLine();

        database.getinstance().addstudent(name, email, year, contactDetails, password);
        System.out.println("Student signup successful");
    }

    private void signupProfessor() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter expertise: ");
        String expertise = scanner.nextLine();

        database.getinstance().addprofessor(name, expertise, email, password);
        System.out.println("Professor signup successful");
    }

    private void signupTA() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter expertise: ");
        String expertise = scanner.nextLine();

        database.getinstance().addta(name, email, expertise, password);
        System.out.println("TA signup successful");
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nLogin setup");
            System.out.println("1. Login");
            System.out.println("2. Signup");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: handleLogin(); break;
                case 2: new Signup().signupUser(); break;
                case 3:
                    System.out.println("Thank you for using the Course Management System. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void handleLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Student/Professor Login");
        System.out.println("2. Admin Login");
        System.out.print("Enter choice: ");
        int loginChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            if (loginChoice == 1) {
                System.out.print("Enter email: ");
                String email = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                new Login(email, password);
            } else if (loginChoice == 2) {
                System.out.print("Enter admin password: ");
                String adminPassword = scanner.nextLine();
                new Login(adminPassword);
            } else {
                System.out.println("Invalid choice.");
            }
        } catch (InvalidLoginException e) {
            System.out.println("Login failed: " + e.getMessage());
        }
    }
}