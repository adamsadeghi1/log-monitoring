package main.java.logmonitoring.services;

import main.java.logmonitoring.services.ReadLog;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringJoiner;

public class ReadLogFromFile implements ReadLog {

    private String filePath;

    public ReadLogFromFile(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String read() {
        try {
            return readLogFile(filePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private String readLogFile(String filePath) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        StringJoiner logContent = new StringJoiner(System.lineSeparator());
        String line;
        try {
            while ((line = reader.readLine()) !=null) {
                logContent.add(line);
            }
        }catch (IOException e){
            throw new RuntimeException(e.getMessage());
        }

        return logContent.toString();
    }
}
