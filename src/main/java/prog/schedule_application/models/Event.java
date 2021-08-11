package prog.schedule_application.models;

import java.lang.reflect.Type;
import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Event {
    private LocalTime startTime;
    private LocalTime endTime;
    private String eventName;
    private String days;

    public Event(LocalTime startTime, LocalTime endTime, String eventName, String days) {
        setStartTime(startTime);
        setEndTime(endTime);
        setEventName(eventName);
        setDays(days);
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() { return endTime; }

    public void setEndTime(LocalTime endTime) { this.endTime = endTime;}

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = dataChecker("([A-z\\s\\:\\-]{1,})[ ]([0-9]{1})", eventName);
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = dataChecker("([MTWHF]{1,5})", days);
    }

    protected String dataChecker(String regex, String toCheck){
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(toCheck);
        if(m.find()){
            return toCheck;
        }
        else{
            System.out.println("Invalid Data '" + toCheck + "'");
            return null;
        }
    }
}
