package se_project;

import java.time.LocalDate;
import java.util.*;

public class Event {

    //Attributes of event objects
    private String eventName;
    private String eventType;
    private LocalDate eventDate;
    private String eventDuration;
    private String eventStartTime;

    //ArrayList to store all events created
    protected static ArrayList<Event> eventList = new ArrayList<>();

    public Event() {
        eventName = "";
        eventType = "";
        eventDate = null;
        eventStartTime = "";
        eventDuration = "";
    }

    public Event(String name, String type, LocalDate date, String startTime, String duration) {
        eventName = name;
        eventType = type;
        eventDate = date;
        eventStartTime = startTime;
        eventDuration = duration;
    }

    public ArrayList<Event> getEventList() {
        //use built in sort method to sort arrayList by date
        eventList.sort(Comparator.comparing(o -> o.getEventDate()));
        return eventList;
    }

    public String getEventName() {
        return this.eventName;
    }

    public String getEventType() {
        return this.eventType;
    }

    public LocalDate getEventDate() {
        return this.eventDate;
    }

    public String getEventDuration() {
        return this.eventDuration;
    }

    public String getEventStartTime() {
        return this.eventStartTime;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public void setEventDuration(String eventDuration) {
        this.eventDuration = eventDuration;
    }

    public void setEventStartTime(String eventStartTime) {
        this.eventStartTime = eventStartTime;
    }
}
