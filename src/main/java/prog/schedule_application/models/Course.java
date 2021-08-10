package prog.schedule_application.models;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Course extends Event{

    private String sectionCode;
    private String courseCode;
    private boolean isRequired;

    public Course(LocalTime time, String eventName, String days, String sectionCode, String courseCode, boolean isRequired) {
        super(time, eventName, days);
        this.sectionCode = sectionCode;
        this.courseCode = courseCode;
        this.isRequired = isRequired;
    }

    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public void setRequired(boolean required) {
        isRequired = required;
    }

    @Override
    public String toString(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm a");
        StringBuilder sb = new StringBuilder();
        sb.append("\n***COURSE***\n");
        sb.append("Code: " + getCourseCode() + "\n");
        sb.append("Section: " + getSectionCode() + "\n");
        sb.append("Name: " + getEventName() + "\n");
        sb.append("Time: " + getTime().format(dtf) + "\n");
        sb.append("DaysOfTheWeek: " + getDays());
        return sb.toString();
    }
}
