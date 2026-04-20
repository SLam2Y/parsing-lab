package com.lab2;

import com.lab2.csv_parsing.CsvSplitParser;
import com.lab2.json_parsing.JsonParser;
import com.lab2.json_parsing.XmlToJsonConverter;
import com.lab2.xml_parsing.XmlXPathParser;

import java.io.InputStream;
import java.util.List;

public class App {

    public static void main(String[] args) {

        // CSV
        InputStream csvStream = App.class
                .getClassLoader()
                .getResourceAsStream("students.csv");

        CsvSplitParser csvParser = new CsvSplitParser();

        List<Student> csvStudents = csvParser.parse(csvStream);

        System.out.println("CSV students:");
        StudentStatistics.print(csvStudents);


        // XML
        InputStream xmlStream = App.class
                .getClassLoader()
                .getResourceAsStream("students.xml");

        XmlXPathParser xmlParser = new XmlXPathParser();

        List<Student> xmlStudents = xmlParser.parse(xmlStream);

        System.out.println("XML students:");
        StudentStatistics.print(xmlStudents);


        // XPath query
        InputStream xmlStream2 = App.class
                .getClassLoader()
                .getResourceAsStream("students.xml");

        List<Student> goodStudents =
                xmlParser.findStudentsWithGradeGreaterThan(xmlStream2, 8);

        System.out.println("Students with grade > 8:");
        StudentStatistics.print(goodStudents);
        

        JsonParser jsonParser = new JsonParser();

        String jsonPath = "src/main/resources/students.json";

        List<Student> jsonStudents = jsonParser.read(jsonPath);

        System.out.println("JSON students:");
        StudentStatistics.print(jsonStudents);


        // добавить студента
        jsonParser.addStudent(jsonPath, new Student(10,"Mike",22,7.5));


        // изменить оценку
        jsonParser.updateStudentGrade(jsonPath, 2, 9.0);


        // XML → JSON
        InputStream xml = App.class
                .getClassLoader()
                .getResourceAsStream("students.xml");

        XmlToJsonConverter converter = new XmlToJsonConverter();
        converter.convert(xml, jsonPath);
    }
}