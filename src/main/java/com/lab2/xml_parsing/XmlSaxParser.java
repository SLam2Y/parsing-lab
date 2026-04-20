package com.lab2.xml_parsing;

import com.lab2.Student;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * XML parser using SAX streaming parser.
 */
public class XmlSaxParser {

    public List<Student> parse(InputStream inputStream) {

        List<Student> students = new ArrayList<>();

        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {

                Student student;
                String element;

                public void startElement(String uri, String localName, String qName, Attributes attributes) {

                    element = qName;

                    if (qName.equals("student")) {
                        student = new Student(0, "", 0, 0);
                    }
                }

                public void characters(char[] ch, int start, int length) {

                    String value = new String(ch, start, length).trim();
                    if (value.isEmpty()) return;

                    switch (element) {

                        case "id":
                            student = new Student(Integer.parseInt(value), student.getName(), student.getAge(), student.getGrade());
                            break;

                        case "name":
                            student = new Student(student.getId(), value, student.getAge(), student.getGrade());
                            break;

                        case "age":
                            student = new Student(student.getId(), student.getName(), Integer.parseInt(value), student.getGrade());
                            break;

                        case "grade":
                            student = new Student(student.getId(), student.getName(), student.getAge(), Double.parseDouble(value));
                            break;
                    }
                }

                public void endElement(String uri, String localName, String qName) {

                    if (qName.equals("student")) {
                        students.add(student);
                    }
                }
            };

            parser.parse(inputStream, handler);

        } catch (Exception e) {
            System.out.println("Invalid XML: " + e.getMessage());
        }

        return students;
    }
}