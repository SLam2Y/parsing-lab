package com.lab2;

import java.util.List;

/**
 * Utility class for printing student statistics.
 */
public class StudentStatistics {

    public static void print(List<Student> students) {

        System.out.println("Students:");

        for (Student s : students) {
            System.out.println(s);
        }

        System.out.println("Count: " + students.size());

        double avg = students.stream()
                .mapToDouble(Student::getGrade)
                .average()
                .orElse(0);

        System.out.println("Average grade: " + avg);
    }
}