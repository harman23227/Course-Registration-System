import java.time.LocalDate;

class Course {
    private String code;
    private String title;
    private String professor;
    private String prerequisite;
    private String timings;
    private String venue;
    private int credits;
    private LocalDate addDropDeadline;
    private int maxCapacity;
    private int enrolledStudents;

    // Constructor
    public Course(String code, String title, String professor, String prerequisite,
                  String timings, String venue, int credits, LocalDate addDropDeadline,
                  int maxCapacity) {
        this.code = code;
        this.title = title;
        this.professor = professor;
        this.prerequisite = prerequisite;
        this.timings = timings;
        this.venue = venue;
        this.credits = credits;
        this.addDropDeadline = addDropDeadline;
        this.maxCapacity = maxCapacity;
        this.enrolledStudents = 0;
    }

    // Getters and setters
    public void setProfessor(String professor) { this.professor = professor; }
    public LocalDate getAddDropDeadline() { return addDropDeadline; }
    public int getMaxCapacity() { return maxCapacity; }
    public int getEnrolledStudents() { return enrolledStudents; }
    public String getcode(){return this.code;}
    public void displayDetails() {
        System.out.println("Course Code: " + code);
        System.out.println("Title: " + title);
        System.out.println("Professor: " + professor);
        System.out.println("Prerequisites: " + prerequisite);
        System.out.println("Timings: " + timings);
        System.out.println("Venue: " + venue);
        System.out.println("Credits: " + credits);
        System.out.println("Add/Drop Deadline: " + addDropDeadline);
        System.out.println("Maximum Capacity: " + maxCapacity);
        System.out.println("Enrolled Students: " + enrolledStudents);
    }

    public void addEnrolledStudent() { enrolledStudents++; }
    public void removeEnrolledStudent() { enrolledStudents--; }
}

