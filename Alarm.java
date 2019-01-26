package se_project;

import java.util.*;

public class Alarm {

    //Attributes for Alarm objects
    private String alarmTime;
    private String alarmName;
    //variables for the days alarm is active
    private boolean mon;
    private boolean tue;
    private boolean wed;
    private boolean thu;
    private boolean fri;
    private boolean sat;
    private boolean sun;

    //ArrayList to store all alarms
    protected static ArrayList<Alarm> alarmList = new ArrayList<>();

    //Default constructor
    public Alarm() {
        alarmTime = "";
        alarmName = "";
    }

    //Constructor for alarm object
    public Alarm(String startTime, String name) {
        alarmTime = startTime;
        alarmName = name;
    }

    public ArrayList<Alarm> getAlarmList() {
        return alarmList;
    }

    public void setAlarmList(ArrayList<Alarm> alarmList) {
        this.alarmList = alarmList;
    }

    public String getAlarmTime() {
        return this.alarmTime;
    }

    public void setAlarmTime(String alarmTime) {
        this.alarmTime = alarmTime;
    }

    public String getAlarmName() {
        return this.alarmName;
    }

    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName;
    }

    public boolean isMon() {
        return mon;
    }

    public void setMon(boolean mon) {
        this.mon = mon;
    }

    public boolean isTue() {
        return tue;
    }

    public void setTue(boolean tue) {
        this.tue = tue;
    }

    public boolean isWed() {
        return wed;
    }

    public void setWed(boolean wed) {
        this.wed = wed;
    }

    public boolean isThu() {
        return thu;
    }

    public void setThu(boolean thu) {
        this.thu = thu;
    }

    public boolean isFri() {
        return fri;
    }

    public void setFri(boolean fri) {
        this.fri = fri;
    }

    public boolean isSat() {
        return sat;
    }

    public void setSat(boolean sat) {
        this.sat = sat;
    }

    public boolean isSun() {
        return sun;
    }

    public void setSun(boolean sun) {
        this.sun = sun;
    }
}
