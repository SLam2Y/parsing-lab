package com.lab2.json_parsing;

import com.lab2.Student;
import com.lab2.xml_parsing.XmlDomParser;

import java.io.InputStream;
import java.util.List;

/**
 * Converts XML students into JSON file.
 */
public class XmlToJsonConverter {

    public void convert(InputStream xmlStream, String jsonPath) {

        XmlDomParser parser = new XmlDomParser();

        List<Student> students = parser.parse(xmlStream);

        JsonParser json = new JsonParser();

        json.save(jsonPath, students);

        System.out.println("XML converted to JSON");
    }
}