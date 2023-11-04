package main.java.logmonitoring.model;

import java.text.ParseException;

public class LogLine {
    String eventSeverity ;
    String dateTime ;
    String eventID ;
    String eventType;
    String cause;
    String fullName;
    String contactInfo;
    String sourceOfError;

    private LogLine() {
    }

    public String getEventSeverity() {
        return eventSeverity;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getEventID() {
        return eventID;
    }

    public String getEventType() {
        return eventType;
    }

    public String getCause() {
        return cause;
    }

    public String getFullName() {
        return fullName;
    }


    public String getContactInfo() {
        return contactInfo;
    }


    public String getSourceOfError() {
        return sourceOfError;
    }

    public void setSourceOfError(String sourceOfError) {
        this.sourceOfError = sourceOfError;
    }

    public Report createReport() throws ParseException {

        var report = new Report();
        report.setEventId(this.eventID);
        report.setDateTimeUTC(this.dateTime);
        report.setEventType(this.eventType);
        report.setSourceOfError(this.sourceOfError);
        report.setFullName(this.fullName);
        report.setContactInfo(this.contactInfo);

        return report;
    }
    @Override
    public String toString() {
        return "LogLine{" +
                "eventSeverity='" + eventSeverity + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", eventID='" + eventID + '\'' +
                ", eventType='" + eventType + '\'' +
                ", cause='" + cause + '\'' +
                ", fullName='" + fullName + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                '}';
    }

    public static class Builder {
        String eventSeverity ;
        String dateTime ;
        String eventID ;
        String eventType;
        String cause;
        String fullName;
        String contactInfo;
        String sourceOfError;

        public Builder setEventSeverity(String eventSeverity){
            this.eventSeverity = eventSeverity;
            return this;
        }
        public Builder setDateTime(String dateTime){
            this.dateTime = dateTime;
            return this;
        }
        public Builder setEventID(String eventID){
            this.eventID = eventID;
            return this;
        }
        public Builder setEventType(String eventType){
            this.eventType = eventType;
            return this;
        }
        public Builder setCause(String cause){
            this.cause = cause;
            return this;
        }
        public Builder setFullName(String fullName){
            this.fullName = fullName;
            return this;
        }
        public Builder setContactInfo(String contactInfo){
            this.contactInfo = contactInfo;
            return this;
        }
        public Builder setSourceOfError(String sourceOfError){
            this.sourceOfError = sourceOfError;
            return this;
        }

        public LogLine build() {
            LogLine logLine = new LogLine();
            logLine.eventSeverity = this.eventSeverity;
            logLine.dateTime = this.dateTime;
            logLine.eventID = this.eventID;
            logLine.eventType =  this.eventType;
            logLine.cause = this.cause;
            logLine.fullName = this.fullName ;
            logLine.contactInfo = this.contactInfo ;
            logLine.sourceOfError =this.sourceOfError;
            return logLine;
        }


    }
}
