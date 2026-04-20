package com.lab2.csv_parsing;
import com.lab2.Student;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;



public class CsvSplitParser {

    public List<Student> parse(InputStream inputStream) {

        List<Student> students = new ArrayList<>();

        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            reader.readLine();

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");

                Student student = new Student(
                        Integer.parseInt(parts[0]),
                        parts[1],
                        Integer.parseInt(parts[2]),
                        Double.parseDouble(parts[3])
                );

                students.add(student);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;
    }
}