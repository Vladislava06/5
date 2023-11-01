import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Student {
    private String name;
    private List<Integer> grades;
    private String major;

    public Student(String name, List<Integer> grades, String major) {
        this.name = name;
        this.grades = grades;
        this.major = major;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getGrades() {
        return grades;
    }

    public String getMajor() {
        return major;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("John", List.of(5, 4, 4, 5), "Информатика"));
        students.add(new Student("Alice", List.of(4, 5, 4, 5), "Информатика"));
        students.add(new Student("Bob", List.of(3, 4, 5, 5), "Математика"));
        students.add(new Student("Eve", List.of(5, 5, 5, 5), "Информатика"));
        students.add(new Student("Mike", List.of(4, 4, 4, 4), "Информатика"));

        List<Student> topStudents = students.stream()
                .filter(student -> student.getMajor().equals("Информатика"))
                .filter(student -> student.getGrades().stream().mapToInt(Integer::intValue).average().orElse(0) > 4.5)
                .sorted(Comparator.comparingDouble(student -> student.getGrades().stream().mapToInt(Integer::intValue).average().orElse(0), Comparator.reverseOrder()))
                .limit(5)
                .collect(Collectors.toList());

        topStudents.forEach(student -> System.out.println(student.getName() + " - " + student.getGrades().stream().mapToInt(Integer::intValue).average().orElse(0)));
    }
}