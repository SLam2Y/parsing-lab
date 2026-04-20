package com.lab2.xml_parsing;

import com.lab2.Student;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * XML parser using DOM approach.
 */
public class XmlDomParser {

    public List<Student> parse(InputStream inputStream) {

        List<Student> students = new ArrayList<>();

        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.parse(inputStream);
            doc.getDocumentElement().normalize();

            NodeList list = doc.getElementsByTagName("student");

            for (int i = 0; i < list.getLength(); i++) {

                Element element = (Element) list.item(i);

                int id = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
                String name = element.getElementsByTagName("name").item(0).getTextContent();
                int age = Integer.parseInt(element.getElementsByTagName("age").item(0).getTextContent());
                double grade = Double.parseDouble(element.getElementsByTagName("grade").item(0).getTextContent());

                students.add(new Student(id, name, age, grade));
            }

        } catch (Exception e) {
            System.out.println("Invalid XML: " + e.getMessage());
        }

        return students;
    }

    public List<Student> findStudentsWithGradeGreaterThan(List<Student> students, double grade) {

        List<Student> result = new ArrayList<>();

        for (Student s : students) {
            if (s.getGrade() > grade) {
                result.add(s);
            }
        }

        return result;
    }
}