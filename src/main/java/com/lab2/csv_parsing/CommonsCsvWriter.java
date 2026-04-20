package com.lab2.csv_parsing;
import com.lab2.Student;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.util.List;

public class CommonsCsvWriter {

    public void write(String filePath, List<Student> students) {

        try (CSVPrinter printer =
                     new CSVPrinter(new FileWriter(filePath),
                             CSVFormat.DEFAULT.builder().setHeader("id","name","age","grade").build())) {

            for (Student s : students) {

                printer.printRecord(
                        s.getId(),
                        s.getName(),
                        s.getAge(),
                        s.getGrade()
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}