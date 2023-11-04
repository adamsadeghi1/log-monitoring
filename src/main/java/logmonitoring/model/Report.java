package main.java.logmonitoring.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Report {
    private String dateTimeUTC;
    private String eventId;
    private String eventType;
    private String sourceOfError;
    private String fullName;
    private String contactInfo;



    public void setDateTimeUTC(String dateTimeStr) throws ParseException {
        this.dateTimeUTC = convertTo24HourFormat(dateTimeStr);
    }

    public String getDateTimeUTC() {
        return dateTimeUTC;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = addHyphens(eventId);
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType.split(" ")[0];
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName =  fullName ;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getSourceOfError() {
        return sourceOfError;
    }

    public void setSourceOfError(String sourceOfError) {
        this.sourceOfError = sourceOfError;
    }

    @Override
    public String toString() {
        return   dateTimeUTC + "," + eventId +"," + eventType +","+ (sourceOfError!=null?sourceOfError:"") +","+ (fullName!=null?fullName:"") + ", " + contactInfo ;
    }

    protected String convertTo24HourFormat(String inputDateTime) throws ParseException {
        SimpleDateFormat inputFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a Z", Locale.US);
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        inputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        outputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date parsedDate = inputFormat.parse(inputDateTime);
        return outputFormat.format(parsedDate);
    }

    protected String addHyphens(String inputStr) {
        if (inputStr.length() <= 3) {
            return inputStr;
        }
        int lastIndex = inputStr.length() -1;

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < inputStr.length(); i++) {

            if (i % 3 == 0 && i!=0 && i<lastIndex )
                result.append('-');

            result.append(inputStr.charAt(i));
        }

        return result.toString();
    }
}
