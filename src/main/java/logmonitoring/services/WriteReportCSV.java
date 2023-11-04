package main.java.logmonitoring.services;

import main.java.logmonitoring.model.Report;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class WriteReportCSV implements WriteReport<Report> {
    private static final Logger log = Logger.getLogger(WriteReportCSV.class.getName());
    private String filePath;

    public WriteReportCSV(String filePath) {
        this.filePath =filePath;
    }

    @Override
    public void write(List<Report> result) {
        writeCsvFile(result,filePath);
    }

    private  <T> void writeCsvFile(List<T> objects, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (T object : objects) {
                String line = object.toString();
                writer.write(line);
                writer.write("\n");
            }
            log.info( String.format("CSV file created successfully at %s", filePath));
        } catch (IOException e) {
            log.severe(String.format("Error occurred while writing the CSV file: %s" , e.getMessage()));
        }
    }
}
