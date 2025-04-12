import java.util.Scanner;

class TA extends student {
    private String expertise;

    public TA(String name, String email, String expertise) {
        super(name, "Good Standing", email);
        this.expertise = expertise;
    }

    @Override
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nTA Menu");
            System.out.println("1. View TA Expertise");
            System.out.println("2. View Result");
            System.out.println("3. Manipulate Result");
            System.out.println("4. Access Student Menu");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: viewExpertise(); break;
                case 2: viewResults(); break;
                case 3: manipulateResult(); break;
                case 4: accessStudentMenu(); break;
                case 5:
                    System.out.println("Exiting TA Menu.");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void viewResults() {
    }

    private void viewExpertise() {
        System.out.println("TA Expertise: " + expertise);
    }

    private void manipulateResult() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student email: ");
        String studentEmail = scanner.nextLine();
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        System.out.print("Enter new grade: ");
        String newGrade = scanner.nextLine();

        database.getinstance().updateresult(studentEmail, courseCode, newGrade);
        System.out.println("Result updated successfully.");
    }

    private void accessStudentMenu() {
        System.out.println("\nStudent Menu");
        super.displayMenu();
    }

//    @Override
//    public void displayInfo() {
//        super.displayinfo();
//        System.out.println("Role: Teaching Assistant");
//        System.out.println("Expertise: " + expertise);
//    }

//    @Override
//    public void addCourse(String courseCode) throws coursefullexception, dropdeadlinepassedexception {
//        super.addcourse(courseCode);
//        System.out.println("Course added as a TA.");
//    }
//
//    @Override
//    public void dropCourse(String courseCode) throws dropdeadlinepassedexception {
//        super.dropcourse(courseCode);
//        System.out.println("Course dropped as a TA.");
//    }
}

