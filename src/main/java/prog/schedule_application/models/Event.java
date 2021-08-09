package prog.schedule_application.models;

import java.time.LocalTime;

public class Event {
    private LocalTime time;
    private String eventName;
    private String days;

    public Event(LocalTime time, String eventName, String days) {
        this.time = time;
        this.eventName = eventName;
        this.days = days;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

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
