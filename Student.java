
import java.util.*;

public class Student {

    private int id;
    private String studentName;
    private int age;
    private Map<String, Double> courses;
    private List<String> skills;
    private boolean bonusApplied;
    private double originalAverage;

    public Student(int id, String studentName, int age,
                   Map<String, Double> courses, List<String> skills) {

        this.id = id;
        this.studentName = studentName;
        this.age = age;
        this.courses = courses;
        this.skills = skills;
        this.bonusApplied = false;
        this.originalAverage = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Map<String, Double> getCourses() {
        return courses;
    }

    public void setCourses(Map<String, Double> courses) {
        this.courses = courses;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public boolean isBonusApplied() {
        return bonusApplied;
    }

    public void setBonusApplied(boolean bonusApplied) {
        this.bonusApplied = bonusApplied;
    }

    public double getOriginalAverage() {
        return originalAverage;
    }

    public void setOriginalAverage(double average) {
        originalAverage = average;
    }

    public double getAverage() {
        return courses.values().stream()
                .mapToDouble(mark -> mark)
                .average()
                .orElse(0);
    }

    @Override
    public String toString() {
        return "ID: " + id +
                ", Name: " + studentName +
                ", Age: " + age +
                ", Courses: " + courses +
                ", Skills: " + skills +
                ", Average: " + getAverage();
    }
}
