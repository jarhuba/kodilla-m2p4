package com.kodilla.kodillam2p4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.*;

@SpringBootApplication
public class KodillaM2p4Application {

    public static void main(String[] args) {
        SpringApplication.run(KodillaM2p4Application.class, args);

        Map<Student, List<Double>> group = new HashMap<>();

        Student student1 = new Student("Zawisza", "Czarny", "88052158324");
        List<Double> grades1 = Arrays.asList(new Double[] {5.0, 5.5, 3.0});
        Student student2 = new Student("Zawisza", "Czarny", "88052158324");
        List<Double> grades2 = Arrays.asList(new Double[] {2.0, 2.5, 2.0, 2.0});
        Student student3 = new Student("Zawisza", "Czarny", "87122158324");
        List<Double> grades3 = Arrays.asList(new Double[] {5.0, 3.5, 2.0});
        Student student4 = new Student("Pan", "Twardowsy", "78052453394");
        List<Double> grades4 = Arrays.asList(new Double[] {5.0, 3.5, 2.0, 5.0, 5.0, 6.0});
        Student student5 = new Student("Don", "Komorowsy", "70072158958");
        List<Double> grades5 = Arrays.asList(new Double[] {5.0, 3.5, 2.0, 2.0, 2.0, 2.0});
        Student student6 = new Student("Non", "Existent", "11052158326");
        List<Double> grades6 = Arrays.asList(new Double[] {});

        group.put(student1, grades1);
        group.put(student2, grades2); // grades1 jest referencją w mapie i tutaj zmienia się tylko stan pamięci (sama Lista - oceny)?
        group.put(student3, grades3);
        group.put(student4, grades4);
        group.put(student5, grades5);
        group.put(student6, grades6);

        calculateWholeMap(group);

    }

    public static void calculateWholeMap(Map<Student, List<Double>> group) {
        int count = 0;
        for(Map.Entry<Student, List<Double>> e : group.entrySet()) {
            count++;
            System.out.println(count + ". " + e.getKey() + " --> ma średnią ocen " + calculateListAverage(e.getValue()));
        }
    }

    public static double calculateListAverage(List<Double> list) {
        double sum = 0;
        for (double d : list) {
            sum = sum + d;
        }
        return sum > 0 ? sum / list.size() : 0;
    }
}
class Student {

    private String firstName;
    private String lastName;
    private String peselId;

    public Student(String firstName, String lastName, String peselId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.peselId = peselId;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " PESEL: " + peselId;
    }

    @Override
    public int hashCode() {
        //buckets by birth year from pesel year, for example 89112405931 mean 89
        return Integer.parseInt(this.peselId.substring(0,1));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        final Student s = (Student) o; //dlaczego jest final?
        return this.firstName.equals(s.firstName) && this.lastName.equals(s.lastName) && this.peselId.equals(s.peselId);
    }
}