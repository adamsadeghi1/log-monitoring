package main.java.logmonitoring;

import main.java.logmonitoring.model.LogLine;
import main.java.logmonitoring.model.Report;
import main.java.logmonitoring.services.ReadLog;
import main.java.logmonitoring.services.ReadLogFromFile;
import main.java.logmonitoring.services.WriteReport;
import main.java.logmonitoring.services.WriteReportCSV;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final String SEVERITY = "ERROR";
    private static final String START_DATE_FILTER_STR ="02/12/2020 01:30:00 PM +0000";
    private static final String END_DATE_FILTER_STR ="03/01/2020 05:00:00 PM +0000";
    private static final String LOG_FILE_PATH= "./data.log";
    private static final String OUTPUT_FILE_PATH= "./out.csv";
    private static final Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args)  {

        try {
            WriteReport writeCSVReport = new WriteReportCSV(OUTPUT_FILE_PATH);
            ReadLog readLog =new ReadLogFromFile(LOG_FILE_PATH);

            String[] logContents = readLog.read().split("\\n");
            List<Report> result = generateReport(logContents);
            writeCSVReport.write(result);
        }
        catch (ParseException e){
            log.severe( String.format("Pars exception occurred with message:%s",e.getMessage()));
        }
        catch (Exception e){
            log.severe(e.getMessage());
        }

    }

    private static List<Report> generateReport(String [] logContents) throws ParseException {
        var report =new ArrayList<Report>();
        for (int i = 0; i < logContents.length; i++) {
            if (logContents[i].length() > 50) {
                var logLine = getLogLineValue(logContents[i]);
                if (logLine==null)
                    throw new RuntimeException(String.format("value %s is not correctly formatted on line %s",logContents[i],i));
                if(applyFilter(logLine)){
                    logLine.setSourceOfError(findSourceOfError(logContents,i));
                    report.add(logLine.createReport());
                }
            }
        }
        return report;
    }

    private static String findSourceOfError(String[] logContents, int index){
        if (index + 1 < logContents.length && logContents[index + 1].length() < 50)
            return logContents[index + 1].trim();
        return "";
    }

    private static boolean applyFilter(LogLine logLine) throws ParseException {
        return logLine.getEventSeverity().equals(SEVERITY) &&
                isDateTimeInRange(logLine.getDateTime(),START_DATE_FILTER_STR,END_DATE_FILTER_STR) ;
    }

    private static boolean isDateTimeInRange(String inputDateTime, String startDateStr, String endDateStr) throws ParseException {
        SimpleDateFormat inputFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a Z",Locale.US);
        inputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date startDate = inputFormat.parse(startDateStr);
        Date endDate = inputFormat.parse(endDateStr);
        Date inputDate = inputFormat.parse(inputDateTime);

        return inputDate.compareTo(startDate) >= 0 && inputDate.compareTo(endDate) <= 0;
    }

    private static LogLine getLogLineValue(String rawLog){
        String pattern = "^(\\w+): (\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2} [APM]+ [-+]?\\d{4}) \\[([\\w]+)] ([\\w\\s]+): (\\w+) by (?:(\\b[A-Z][a-z]+(?:\\s[A-Z][a-z]+)*)\\s)?([a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(rawLog);

        if (m.find()) {
            return new LogLine.Builder()
                    .setEventSeverity(m.group(1))
                    .setDateTime(m.group(2))
                    .setEventID(m.group(3))
                    .setEventType(m.group(4))
                    .setCause(m.group(5))
                    .setFullName(m.group(6))
                    .setContactInfo(m.group(7))
                    .build();


        } else {
            log.severe("Log string does not match the expected format.");
            return null;
        }
    }


}