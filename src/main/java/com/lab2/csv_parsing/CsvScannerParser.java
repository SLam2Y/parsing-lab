package com.lab2.csv_parsing;
import com.lab2.Student;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class CsvScannerParser {

    public List<Student> parse(InputStream inputStream) {

        List<Student> students = new ArrayList<>();

        try (Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8)) {

            scanner.nextLine();

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
                String[] parts = line.split(",");

                Student student = new Student(
                        Integer.parseInt(parts[0]),
                        parts[1],
                        Integer.parseInt(parts[2]),
                        Double.parseDouble(parts[3])
                );

                students.add(student);
            }
        }

        return students;
    }
}