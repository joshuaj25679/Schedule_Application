package prog.schedule_application.models;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Course extends Event{

    private String sectionCode;
    private String courseCode;
    private String roomNumber;
    private boolean isRequired;

    public Course(LocalTime startTime,LocalTime endTime, String eventName, String days, String sectionCode, String courseCode, String roomNumber, boolean isRequired) {
        super(startTime, endTime, eventName, days);
        setSectionCode(sectionCode);
        setCourseCode(courseCode);
        setRoomNumber(roomNumber);
        setRequired(isRequired);
    }

    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        this.sectionCode = sectionCode;
    }

    public String getRoomNumber(){
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber){
        this.roomNumber = roomNumber;
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
        sb.append("Time: " + getStartTime().format(dtf) + "\n");
        sb.append("DaysOfTheWeek: " + getDays());
        sb.append("ClassRoom: " + getRoomNumber());
        return sb.toString();
    }
}
