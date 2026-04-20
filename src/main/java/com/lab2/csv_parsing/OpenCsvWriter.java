package com.lab2.csv_parsing;
import com.lab2.Student;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.util.List;

public class OpenCsvWriter {

    public void write(String filePath, List<Student> students) {

        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {

            writer.writeNext(new String[]{"id","name","age","grade"});

            for (Student s : students) {

                writer.writeNext(new String[]{
                        String.valueOf(s.getId()),
                        s.getName(),
                        String.valueOf(s.getAge()),
                        String.valueOf(s.getGrade())
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}