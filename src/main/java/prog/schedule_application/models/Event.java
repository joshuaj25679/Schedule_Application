package prog.schedule_application.models;

import java.time.LocalTime;

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
        this.eventName = eventName;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }
}
