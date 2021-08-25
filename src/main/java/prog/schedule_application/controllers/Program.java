package prog.schedule_application.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import prog.schedule_application.models.Course;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Program {
    @FXML
    static TextArea filePath;
    private static ArrayList<Course> userAddedCourses = new ArrayList<>();
    private static Pattern p = null;
    private static Pattern p2 = null;
    private static Matcher m = null;
    private static Matcher m2 = null;
    private static ArrayList<String> inputCourses = new ArrayList<>();
    private static ArrayList<Course> courseList = new ArrayList<>();
    private static ArrayList<Course> userSchedule = new ArrayList<>();
    private static String pathName = String.valueOf(filePath);

    public static ArrayList<Course> buildCourses(String path) {
        ArrayList<Course> courses = new ArrayList<>();
        int counter = 0;
        String regex = "^([A-Z]{3}[0-9]{3})[ ]([A-Z0-9]{0,2}[ ])?([A-z0-9\\W]{1,})[ ]([0-9]{1})[ ]([0-9]{1,2})[:]([0-9]{1,2})[ ](AM|PM)[-]([0-9]{1,2})[:]([0-9]{1,2})[ ](AM|PM)[ ]([MTWHF]{1,5})[ ]([0-9]{3})";
        String sprintRegex = "^(Sprint 2)";
        PDFtoTXT.test(path);
        for (String a : PDFtoTXT.pdfStrings) {
            p = Pattern.compile(regex);
            m = p.matcher(a);
            if (m.find()) {
                LocalTime startTime = LocalTime.of(Integer.parseInt(m.group(5)), Integer.parseInt(m.group(6)));
                if (m.group(7).equals("PM")) {
                    startTime = LocalTime.of(Integer.parseInt(m.group(5)) + 12, Integer.parseInt(m.group(6)));
                }
                LocalTime endTime = LocalTime.of(Integer.parseInt(m.group(8)), Integer.parseInt(m.group(9)));
                if (m.group(10).equals("PM")) {
                    endTime = LocalTime.of(Integer.parseInt(m.group(8)) + 12, Integer.parseInt(m.group(9)));
                }
                String eventName = m.group(3);
                String days = m.group(11);
                String sectionCode = m.group(2) != null ? m.group(2) : "N/A";
                String courseCode = m.group(1);
                String roomNumber = m.group(12);
                counter += 1;
                Course course = new Course(startTime, endTime, eventName, days, sectionCode, courseCode, roomNumber);
                if(isInList(course, courses)==false){
                    courses.add(course);
                }
            }
        }
        return courses;
    }

    public static boolean isInList(Course course, ArrayList<Course> courses){
        boolean check = false;
        for(Course a : courses){
            if(course.getCourseCode().equals(a.getCourseCode())
                    && course.getSectionCode().equals(a.getSectionCode())
                    && course.getRoomNumber().equals(a.getRoomNumber())
                    && course.getEventName().equals(a.getEventName())
                    && course.getDays().equals(a.getDays())){
                check = true;
            }
        }
        return check;
    }

    public static boolean addToInputCourses(String courseToAdd){
        boolean valid = false;
        String formattedInput = courseToAdd.toUpperCase();
        formattedInput = formattedInput.replaceAll(" ", "");
        String regex = "([A-Z]{3}[0-9]{3})";
        p = Pattern.compile(regex);
        m = p.matcher(formattedInput);
        for (Course a : courseList){
            valid = a.getCourseCode().equals(formattedInput);
            if (valid){
                if(m.find()){
                    getInputCourses().add(formattedInput);
                    return true;
                }
            }
        }
        return false;
    }

    public static ArrayList<Course> courseListCreator(int sprintTime, ArrayList<String> userClasses, ArrayList<Course> courseList) {
        ArrayList<Course> sprintCourses = new ArrayList<>();

        switch (sprintTime) {
            case 1:
                //System.out.println("\nSprint " + sprintTime + " Course List: \n");
                for (String courseCode : userClasses) {
                    //Check the course list for all the courses to be taken and add them to staging ground
                    for (Course course : courseList) {
                        if (course.getCourseCode().equals(courseCode) && !course.getSectionCode().contains("2")) {
                            sprintCourses.add(course);
                        }
                    }
                }
                printCourseList(sprintCourses);
            case 2:
                //System.out.println("\nSprint 2 Course List:");
                for (String courseCode : userClasses) {
                    //Check the course list for all the courses to be taken and add them to the list of sprint courses
                    for (Course course : courseList) {
                        if (course.getCourseCode().equals(courseCode) && course.getSectionCode().contains("2")) {
                            sprintCourses.add(course);
                        }
                    }
                }
                printCourseList(sprintCourses);
            case 3:
                //System.out.println("\nQuarter Course List:");
                for (String courseCode : userClasses) {
                    //Check the course list for all the courses to be taken and add them to the list of sprint courses
                    for (Course course : courseList) {
                        if (course.getCourseCode().equals(courseCode) && !sprintCourses.contains(course)) {
                            sprintCourses.add(course);
                        }
                    }
                }
                printCourseList(sprintCourses);
        }
        return sprintCourses;
    }

    public static String buildSchedule(int sprintIndicator, ArrayList<Course> userCourses){
        StringBuilder scheduleOutprint = new StringBuilder();
        if(!userCourses.isEmpty()){
            //Use Lambda to organize by time
            userCourses.sort((o1, o2) -> o1.getEndTime().compareTo(o2.getStartTime()));
            //System.out.println(userCourses);
        }
        //Use the switch to separate the Sprint Classes
        switch(sprintIndicator){
            case 1:
                for(Course course : userCourses){
                    if(course.getSectionCode().contains("" + sprintIndicator)
                            || !course.getSectionCode().contains("2")){
                        scheduleOutprint.append(course);
                    }
                    else{
                        //System.out.println(course);
                    }
                }
                break;
            case 2:
                for(Course course : userCourses){
                    if(course.getSectionCode().contains("" + sprintIndicator)
                            || !course.getSectionCode().contains("1")){
                        scheduleOutprint.append(course);
                    }
                    else{
                        //System.out.println(course);
                    }
                }
                break;
        }
        //System.out.println(scheduleOutprint);
        return scheduleOutprint.toString();
    }

    public static String printCourseList(ArrayList<Course> courseList){
        StringBuilder outPrint = new StringBuilder();
        for(Course courses : courseList){
            //System.out.println(courses.toString());
            outPrint.append(courses.toString());
            outPrint.append("\n");
        }
        return outPrint.toString();
    }

    public static String getPathName() {
        return pathName;
    }

    public static void setPathName(String pathName) {
        Program.pathName = pathName;
    }

    public static ArrayList<Course> getCourseList() {
        return courseList;
    }

    public static void setCourseList(ArrayList<Course> courseList) {
        Program.courseList = courseList;
    }

    public static ArrayList<String> getInputCourses() {
        return inputCourses;
    }

    public static void setInputCourses(ArrayList<String> inputCourses) {
        Program.inputCourses = inputCourses;
    }

    public static ArrayList<Course> getUserCourseList(){
        return userAddedCourses;
    }

    public static void setUserCourses(Course e){
        userAddedCourses.add(e);
    }

    public static ArrayList<Course> getUserSchedule() {
        return userSchedule;
    }

    public static void setUserSchedule(ArrayList<Course> userSchedule) {
        Program.userSchedule = userSchedule;
    }
}
