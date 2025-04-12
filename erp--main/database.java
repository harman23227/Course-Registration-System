import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;
class database {
    private static database instance=null;
    private static Map<String,String> studentdata;
    private Map<String,user> students;
    private static Map<String,String> professordata;
    private Map<String,user> professors;
    private Map<String,Course> courses;
    private Map<String, List<complaint>> coursecomplaints;
    private Map<String,List<feedbackitem>> feedbackmap;
    private Map<String,List<Result>> results;
    private final String adminpassword="admin123";

    private database() {
        studentdata=new HashMap<>();
        students=new HashMap<>();
        professordata=new HashMap<>();
        professors=new HashMap<>();
        courses=new HashMap<>();
        coursecomplaints=new HashMap<>();
        feedbackmap=new HashMap<>();
        results=new HashMap<>();
        populatecourses();
    }

    public static database getinstance() {
        if(instance==null) {
            instance=new database();
        }
        return instance;
    }

    public void addprofessor(String name,String expertise,String email,String password) {
        professors.put(email,new professor("p"+(professors.size()+1),name,email,expertise));
        professordata.put(email,password);
    }

    public void addstudent(String name,String email,String academicstanding,String contactdetails,String password) {
        studentdata.put(email,password);
        students.put(email,new student(name,academicstanding,email));
    }

    public void addta(String name,String email,String expertise,String password) {
        studentdata.put(email,password);
        students.put(email,new TA(name,email,expertise));
    }

    public void assignprofessor(String coursecode,String professoremail) {
        if(courses.containsKey(coursecode) && professors.containsKey(professoremail)) {
            Course course=courses.get(coursecode);
            professor prof=(professor)professors.get(professoremail);
            course.setProfessor(prof.name);
        }
    }

    public void updateacademicstanding(String email,String newacademicstanding) {
        user user=students.get(email);
        if(user instanceof student) {
            ((student)user).setacademicstanding(newacademicstanding);
        }
    }

    public void handlecomplaint(String coursecode,complaint complaint) {
        coursecomplaints.computeIfAbsent(coursecode,k -> new ArrayList<>()).add(complaint);
    }

    public user getstudent(String email) {
        return students.get(email);
    }

    public Course getcourse(String coursecode) {
        return courses.get(coursecode);
    }

    public user getprofessor(String email) {
        return professors.get(email);
    }

    private void populatecourses() {
        courses.put("cs101",new Course("cs101","Intro to CS","Prof. John Doe","None","Mon 9-11","Room 101",4,LocalDate.now().plusMonths(1),50));
        courses.put("mth101",new Course("mth101","Linear Algebra","Prof. Jane Smith","None","Tue 10-12","Room 132",4,LocalDate.now().plusMonths(1),40));
    }

    public static boolean validatestudent(String email, String password) {
        return password.equals(studentdata.get(email));
    }

    public static boolean validateprofessor(String email, String password) {
        return password.equals(professordata.get(email));
    }

    public boolean isadmin(String password) {
        return password.equals(adminpassword);
    }

    public List<complaint> getcoursecomplaints(String coursecode) {
        return coursecomplaints.getOrDefault(coursecode,new ArrayList<>());
    }

    public void addcourse(Course course) {
        courses.put(course.getcode(),course);
    }

    public boolean removecourse(String coursecode) {
        return courses.remove(coursecode)!=null;
    }

    public List<Course> getallcourses() {
        return new ArrayList<>(courses.values());
    }

    public List<user> getallusers() {
        List<user> allusers=new ArrayList<>(students.values());
        allusers.addAll(professors.values());
        return allusers;
    }

    public void updateresult(String studentemail,String coursecode,String newgrade) {
        List<Result> studentresults=results.computeIfAbsent(studentemail,k -> new ArrayList<>());
        for(Result result : studentresults) {
            if (result.getCourseCode().equals(coursecode)) {
                result.setGrade(newgrade);
                return;
            }
        }
        studentresults.add(new Result(studentemail,coursecode,newgrade));
    }

    public void addresult(String studentemail,String coursecode,String grade) {
        results.computeIfAbsent(studentemail,k -> new ArrayList<>()).add(new Result(studentemail,coursecode,grade));
    }

    public List<Result> getstudentresults(String studentemail) {
        return results.getOrDefault(studentemail,new ArrayList<>());
    }

    public <T> void addfeedback(String coursecode,T feedbackdata) {
        feedbackmap.computeIfAbsent(coursecode,k -> new ArrayList<>()).add(new feedbackitem(feedbackdata));
    }

    public List<feedbackitem> getfeedbackforcourse(String coursecode) {
        return feedbackmap.getOrDefault(coursecode,new ArrayList<>());
    }

    public static class feedbackitem {
        private Object data;

        public feedbackitem(Object data) {
            this.data=data;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }
}