package com.lab2.csv_parsing;
import com.lab2.Student;

import com.opencsv.CSVReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class OpenCsvParser {

    public List<Student> parse(InputStream inputStream) {

        List<Student> students = new ArrayList<>();

        try (CSVReader reader =
                     new CSVReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            reader.readNext();

            String[] line;

            while ((line = reader.readNext()) != null) {

                Student student = new Student(
                        Integer.parseInt(line[0]),
                        line[1],
                        Integer.parseInt(line[2]),
                        Double.parseDouble(line[3])
                );

                students.add(student);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;
    }
}