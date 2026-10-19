
import java.util.*;
import java.util.stream.Collectors;

public class StudentManagementSystem {

    static Scanner scanner = new Scanner(System.in);
    static List<Student> students = new ArrayList<>();

    public static void main(String[] args) {

        int choice;

        do {
            menu();
            choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    registerStudent();
                    break;
                case 2:
                    displayStudents();
                    break;
                case 3:
                    searchById();
                    break;
                case 4:
                    passingStudents();
                    break;
                case 5:
                    failingStudents();
                    break;
                case 6:
                    searchByName();
                    break;
                case 7:
                    sortByName();
                    break;
                case 8:
                    sortByAverage();
                    break;
                case 9:
                    allCourses();
                    break;
                case 10:
                    uniqueCourses();
                    break;
                case 11:
                    searchByCourse();
                    break;
                case 12:
                    uniqueSkills();
                    break;
                case 13:
                    groupByCourse();
                    break;
                case 14:
                    performanceReport();
                    break;
                case 15:
                    bestStudent();
                    break;
                case 16:
                    topThree();
                    break;
                case 17:
                    applyBonus();
                    break;
                case 18:
                    finalRanking();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);
    }

    static void menu() {
        System.out.println("\n======================================");
        System.out.println("STUDENT MANAGEMENT SYSTEM");
        System.out.println("======================================");
        System.out.println("1. Register New Student");
        System.out.println("2. Display All Students");
        System.out.println("3. Search Student by ID");
        System.out.println("4. Filter Passing Students");
        System.out.println("5. Filter Failing Students");
        System.out.println("6. Search Students by Name");
        System.out.println("7. Sort Students by Name");
        System.out.println("8. Sort Students by Average Marks");
        System.out.println("9. Display All Courses");
        System.out.println("10. Display Unique Courses");
        System.out.println("11. Search Students by Course");
        System.out.println("12. Display Unique Skills");
        System.out.println("13. Group Students by Course");
        System.out.println("14. View Student Performance Statistics");
        System.out.println("15. View Best Performing Student");
        System.out.println("16. View Top 3 Students");
        System.out.println("17. Apply Bonus Marks");
        System.out.println("18. View Final Student Ranking");
        System.out.println("0. Exit");
    }

    static int readInt(String message) {
        while (true) {
            System.out.print(message);

            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid whole number.");
            }
        }
    }

    static String readText(String message) {
        while (true) {
            System.out.print(message);
            String text = scanner.nextLine().trim();

            if (!text.isEmpty()) {
                return text;
            }

            System.out.println("Please enter some text.");
        }
    }

    static double readMark() {
        while (true) {
            System.out.print("Enter marks (0 - 100): ");

            try {
                double mark = Double.parseDouble(scanner.nextLine().trim());

                if (Double.isFinite(mark) && mark >= 0 && mark <= 100) {
                    return mark;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid mark.");
            }

            System.out.println("Marks must be between 0 and 100.");
        }
    }

    static boolean hasStudents() {
        if (students.isEmpty()) {
            System.out.println("No students registered.");
            return false;
        }
        return true;
    }

    static void registerStudent() {

        int id;

        while (true) {
            id = readInt("Enter student ID: ");

            if (id <= 0) {
                System.out.println("ID must be positive.");
                continue;
            }

            int checkId = id;

            boolean exists = students.stream()
                    .anyMatch(s -> s.getId() == checkId);

            if (!exists) {
                break;
            }

            System.out.println("Student ID already exists!");
        }

        String name = readText("Enter name: ");

        int age;

        do {
            age = readInt("Enter age: ");

            if (age <= 0) {
                System.out.println("Age must be positive.");
            }
        } while (age <= 0);

        Map<String, Double> courses = new LinkedHashMap<>();

        int number;

        do {
            number = readInt("Number of courses: ");
        } while (number <= 0);

        for (int i = 0; i < number; i++) {

            String course = readText("Course name: ");

            boolean exists = courses.keySet().stream()
                    .anyMatch(c -> c.equalsIgnoreCase(course));

            if (exists) {
                System.out.println("Course already entered.");
                i--;
                continue;
            }

            double mark = readMark();
            courses.put(course, mark);
        }

        List<String> skills = new ArrayList<>();

        int count;

        do {
            count = readInt("Number of skills: ");
        } while (count < 0);

        for (int i = 0; i < count; i++) {
            String skill = readText("Enter skill: ");
            skills.add(skill);
        }

        Student student = new Student(id, name, age, courses, skills);
        students.add(student);

        System.out.println("Student registered successfully.");
    }

    static void displayStudents() {
        if (!hasStudents()) return;

        students.stream()
                .forEach(s -> System.out.println(s));
    }

    static void searchById() {
        if (!hasStudents()) return;

        int id = readInt("Enter student ID: ");

        Optional<Student> student = students.stream()
                .filter(s -> s.getId() == id)
                .findFirst();

        if (student.isPresent()) {
            System.out.println(student.get());
        } else {
            System.out.println("Student not found.");
        }
    }

    static void passingStudents() {
        if (!hasStudents()) return;

        List<Student> result = students.stream()
                .filter(s -> s.getAverage() >= 65)
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            System.out.println("No passing students.");
        } else {
            result.forEach(s -> System.out.println(s));
        }
    }

    static void failingStudents() {
        if (!hasStudents()) return;

        List<Student> result = students.stream()
                .filter(s -> s.getAverage() < 65)
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            System.out.println("No failing students.");
        } else {
            result.forEach(s -> System.out.println(s));
        }
    }

    static void searchByName() {
        if (!hasStudents()) return;

        String name = readText("Enter student name: ");

        List<Student> result = students.stream()
                .filter(s -> s.getStudentName().toLowerCase()
                        .contains(name.toLowerCase()))
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            System.out.println("No student found.");
        } else {
            result.forEach(s -> System.out.println(s));
        }
    }

    static void sortByName() {
        if (!hasStudents()) return;

        students.stream()
                .sorted((a, b) -> a.getStudentName()
                        .compareToIgnoreCase(b.getStudentName()))
                .forEach(s -> System.out.println(s));
    }

    static void sortByAverage() {
        if (!hasStudents()) return;

        students.stream()
                .sorted((a, b) -> Double.compare(
                        b.getAverage(), a.getAverage()))
                .forEach(s -> System.out.println(s));
    }

    static void allCourses() {
        if (!hasStudents()) return;

        students.stream()
                .forEach(s -> {
                    System.out.println(s.getStudentName() + ":");
                    s.getCourses().forEach((course, mark) ->
                            System.out.println(course + " = " + mark));
                });
    }

    static void uniqueCourses() {
        if (!hasStudents()) return;

        students.stream()
                .flatMap(s -> s.getCourses().keySet().stream())
                .map(course -> course.toLowerCase())
                .distinct()
                .sorted()
                .forEach(course -> System.out.println(course));
    }

    static void searchByCourse() {
        if (!hasStudents()) return;

        String course = readText("Enter course: ");

        List<Student> result = students.stream()
                .filter(s -> s.getCourses().keySet().stream()
                        .anyMatch(c -> c.equalsIgnoreCase(course)))
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            System.out.println("No students found.");
        } else {
            result.forEach(s -> System.out.println(s));
        }
    }

    static void uniqueSkills() {
        if (!hasStudents()) return;

        students.stream()
                .flatMap(s -> s.getSkills().stream())
                .map(skill -> skill.toLowerCase())
                .distinct()
                .sorted()
                .forEach(skill -> System.out.println(skill));
    }

    static void groupByCourse() {
        if (!hasStudents()) return;

        Map<String, List<Student>> groups = students.stream()
                .flatMap(s -> s.getCourses().keySet().stream()
                        .map(c -> new AbstractMap.SimpleEntry<>(
                                c.toLowerCase(), s)))
                .collect(Collectors.groupingBy(
                        e -> e.getKey(),
                        Collectors.mapping(
                                e -> e.getValue(),
                                Collectors.toList()
                        )
                ));

        groups.forEach((course, list) -> {
            System.out.println("\nCourse: " + course);

            list.forEach(s ->
                    System.out.println(s.getStudentName()));
        });
    }

    static void performanceReport() {
        if (!hasStudents()) return;

        students.stream().forEach(s -> {

            DoubleSummaryStatistics stats =
                    s.getCourses().values().stream()
                            .mapToDouble(mark -> mark)
                            .summaryStatistics();

            System.out.println("\nStudent: " + s.getStudentName());
            System.out.println("Average: " + stats.getAverage());
            System.out.println("Highest: " + stats.getMax());
            System.out.println("Lowest: " + stats.getMin());
            System.out.println("Total: " + stats.getSum());
            System.out.println("Courses: " + stats.getCount());
        });

        long passed = students.stream()
                .filter(s -> s.getAverage() >= 65)
                .count();

        System.out.println("\nNumber of passing students: " + passed);

        bestStudent();
        topThree();
    }

    static void bestStudent() {
        if (!hasStudents()) return;

        Optional<Student> best = students.stream()
                .max((a, b) -> Double.compare(
                        a.getAverage(), b.getAverage()));

        if (best.isPresent()) {
            System.out.println("Best student: " + best.get());
        }
    }

    static void topThree() {
        if (!hasStudents()) return;

        System.out.println("Top three students:");

        students.stream()
                .sorted((a, b) -> Double.compare(
                        b.getAverage(), a.getAverage()))
                .limit(3)
                .forEach(s -> System.out.println(s));
    }

    static void applyBonus() {
        if (!hasStudents()) return;

        List<Student> waiting = students.stream()
                .filter(s -> !s.isBonusApplied())
                .collect(Collectors.toList());

        if (waiting.isEmpty()) {
            System.out.println("Bonuses already processed.");
            return;
        }

        waiting.forEach(s -> {

            double oldAverage = s.getAverage();
            s.setOriginalAverage(oldAverage);

            if (oldAverage > 50) {
                s.getCourses().replaceAll((course, mark) ->
                        Math.min(100, mark + 10));
            }

            s.setBonusApplied(true);

            double sum = s.getCourses().values().stream()
                    .reduce(0.0, (a, b) -> a + b);

            double newAverage = sum / s.getCourses().size();

            System.out.println(s.getStudentName());
            System.out.println("Original average: " + oldAverage);
            System.out.println("Final average: " + newAverage);
        });

        finalRanking();
    }

    static void finalRanking() {
        if (!hasStudents()) return;

        List<Student> ranking = students.stream()
                .sorted((a, b) -> Double.compare(
                        b.getAverage(), a.getAverage()))
                .collect(Collectors.toList());

        int position = 1;

        for (Student s : ranking) {

            double original = s.getAverage();

            if (s.isBonusApplied()) {
                original = s.getOriginalAverage();
            }

            System.out.println(position + ". " + s.getStudentName());
            System.out.println("Original: " + original);
            System.out.println("Final: " + s.getAverage());

            position++;
        }
    }
}
