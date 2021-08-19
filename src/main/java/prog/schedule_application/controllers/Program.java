package prog.schedule_application.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import prog.schedule_application.models.Course;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Program {

//    static String path= filePath.getText();
    private static Pattern p = null;
    private static Matcher m = null;
    private static ArrayList<String> inputCourses = new ArrayList<>();
    private static ArrayList<Course> courseList = new ArrayList<>();
    private static String pathName = "src/main/files/test.pdf";

    public static ArrayList<Course> courseListCreator(int sprintTime, ArrayList<String> userClasses, ArrayList<Course> courseList) {
        ArrayList<Course> sprintCourses = new ArrayList<>();

        switch (sprintTime) {
            case 1:
                System.out.println("\nSprint " + sprintTime + " Course List: \n");
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
                System.out.println("\nSprint 2 Course List:");
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
                System.out.println("\nQuarter Course List:");
                for (String courseCode : userClasses) {
                    //Check the course list for all the courses to be taken and add them to the list of sprint courses
                    for (Course course : courseList) {
                        if (course.getCourseCode().equals(courseCode)) {
                            sprintCourses.add(course);
                        }
                    }
                }
                printCourseList(sprintCourses);
        }
        return sprintCourses;
    }
        //Compare times and build schedule
        /*schedule.add(sprintOneCourses.get(0));
        int counter = 1;
            for(Course courses : sprintOneCourses){
                //Comparisons
                if(!schedule.get(counter - 1).getCourseCode().equals(courses.getCourseCode()) && courses.getStartTime().isAfter(schedule.get(counter-1).getEndTime())){
                    schedule.add(courses);
                    System.out.println(courses.getCourseCode() + " Added");
                    counter++;
                }
                else{
                    System.out.println(courses.getCourseCode() + "NOT ADDED DIMWIT");
                }

            }*/
        //System.out.println(schedule);

    public static String printCourseList(ArrayList<Course> courseList){
        StringBuilder outPrint = new StringBuilder();
        for(Course courses : courseList){
            System.out.println(courses.toString());
            outPrint.append(courses.toString());
            outPrint.append("\n");
        }
        return outPrint.toString();
    }

    public static ArrayList<Course> buildCourses(String path) {
        ArrayList<Course> courseList = new ArrayList<>();
        int counter = 0;
        String regex = "^([A-Z]{3}[0-9]{3})[ ](([A-Z0-9]{0,2})[ ])?([A-z\\s\\:\\-]{1,})[ ]([0-9]{1})[ ]([0-9]{1,2})[:]([0-9]{1,2})[ ](AM|PM)[-]([0-9]{1,2})[:]([0-9]{1,2})[ ](AM|PM)[ ]([MTWHF]{1,5})[ ]([0-9]{3})";
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
                String sectionCode = m.group(2);
                String courseCode = m.group(1);
                String roomNumber = m.group(12);
                Boolean isRequired = true;
                counter += 1;
                Course course = new Course(startTime, endTime, eventName, days, sectionCode, courseCode, roomNumber, isRequired);
                courseList.add(course);
            }
        }
        return courseList;
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

    public static void addToInputCourses(String courseToAdd){
        String formattedInput = courseToAdd.toUpperCase();
        formattedInput = formattedInput.replaceAll(" ", "");
        String regex = "([A-Z]{3}[0-9]{3})";
        p = Pattern.compile(regex);
        m = p.matcher(formattedInput);
        if(m.find()){
            getInputCourses().add(formattedInput);
        }
        else{
            System.out.println("Invalid Course Code Format");
        }
    }
}
