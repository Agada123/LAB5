package org.example.Route.utils;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class FileManager {


    public static String readFile(String fileName) throws IOException {
        StringBuilder content = new StringBuilder();
        try (FileReader reader = new FileReader(fileName)) {
            int ch;
            while ((ch = reader.read()) != -1) {
                content.append((char) ch);
            }
        }
        return content.toString();
    }


    public static void writeFile(String fileName, String content) throws IOException {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(content);
        }
    }
}
