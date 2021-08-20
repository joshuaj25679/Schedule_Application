package prog.schedule_application.models;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Course extends Event{

    private String sectionCode;
    private String courseCode;
    private String roomNumber;
    private boolean isRequired;

    public Course(LocalTime startTime,LocalTime endTime, String eventName, String days, String sectionCode, String courseCode, String roomNumber) {
        super(startTime, endTime, eventName, days);
        setSectionCode(sectionCode);
        setCourseCode(courseCode);
        setRoomNumber(roomNumber);
    }

    public String getSectionCode() {
        return sectionCode;
    }

    public void setSectionCode(String sectionCode) {
        if(sectionCode == null) {
            this.sectionCode = "N/A";
        }
        else{
            this.sectionCode = sectionCode;
        }
    }

    public String getRoomNumber(){
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber){
        this.roomNumber = super.dataChecker("([0-9]{3})", roomNumber);
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        //Validate the courseCode is in the correct format before setting it
        this.courseCode = super.dataChecker("([A-Z]{3}[0-9]{3})", courseCode);
    }

    @Override
    public String toString(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm a");
        StringBuilder sb = new StringBuilder();
        sb.append("***COURSE***\n");
        sb.append("Code: " + getCourseCode() + "\n");
        sb.append("Section: " + getSectionCode() + "\n");
        sb.append("Name: " + getEventName() + "\n");
        sb.append("Time: " + getStartTime().format(dtf) + "-" + getEndTime().format(dtf) + "\n");
        sb.append("Days Taught: " + getDays() + "\n");
        sb.append("Class Room: " + getRoomNumber() + "\n");
        return sb.toString();
    }

    public String toStringSpecified(){
        StringBuilder sb = new StringBuilder();
        sb.append("Code: " + getCourseCode() + "\n");
        sb.append("Section: " + getSectionCode() + "\n");
        return sb.toString();
    }



}
