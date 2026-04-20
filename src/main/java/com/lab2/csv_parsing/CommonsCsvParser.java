package com.lab2.csv_parsing;
import com.lab2.Student;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;



import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class CommonsCsvParser {

    public List<Student> parse(InputStream inputStream) {

        List<Student> students = new ArrayList<>();

        try {

            Iterable<CSVRecord> records =
                    CSVFormat.DEFAULT.builder()
                            .setHeader()
                            .setSkipHeaderRecord(true)
                            .build()
                            .parse(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

            for (CSVRecord record : records) {

                Student student = new Student(
                        Integer.parseInt(record.get("id")),
                        record.get("name"),
                        Integer.parseInt(record.get("age")),
                        Double.parseDouble(record.get("grade"))
                );

                students.add(student);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;
    }
}