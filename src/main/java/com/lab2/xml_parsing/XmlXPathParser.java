package com.lab2.xml_parsing;

import com.lab2.Student;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.*;

import org.w3c.dom.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * XML parser using XPath queries.
 */
public class XmlXPathParser {

    public List<Student> parse(InputStream inputStream) {

        List<Student> students = new ArrayList<>();

        try {

            Document doc = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(inputStream);

            XPath xpath = XPathFactory.newInstance().newXPath();

            NodeList list = (NodeList) xpath.evaluate(
                    "/students/student",
                    doc,
                    XPathConstants.NODESET
            );

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

    public List<Student> findStudentsWithGradeGreaterThan(InputStream inputStream, double minGrade) {

    List<Student> students = new ArrayList<>();

    try {

        Document doc = DocumentBuilderFactory
                .newInstance()
                .newDocumentBuilder()
                .parse(inputStream);

        XPath xpath = XPathFactory.newInstance().newXPath();

        String expression = "/students/student[grade>" + minGrade + "]";

        NodeList list = (NodeList) xpath.evaluate(
                expression,
                doc,
                XPathConstants.NODESET
        );

        for (int i = 0; i < list.getLength(); i++) {

            Element element = (Element) list.item(i);

            int id = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
            String name = element.getElementsByTagName("name").item(0).getTextContent();
            int age = Integer.parseInt(element.getElementsByTagName("age").item(0).getTextContent());
            double studentGrade = Double.parseDouble(
                    element.getElementsByTagName("grade").item(0).getTextContent()
            );

            students.add(new Student(id, name, age, studentGrade));
        }

    } catch (Exception e) {
        System.out.println("Invalid XML: " + e.getMessage());
    }

    return students;
}
}