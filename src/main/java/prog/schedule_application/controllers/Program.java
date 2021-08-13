package prog.schedule_application.controllers;

import prog.schedule_application.models.Course;
import prog.schedule_application.models.Event;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Program {
    private static Pattern p = null;
    private static Matcher m = null;


    //TODO Make a method to output schedule nicely

    public static ArrayList<Course> scheduleCreator(String[] userClasses, ArrayList<Course> courseList){
        ArrayList<Course> schedule = new ArrayList<>();
        ArrayList<Course> stagingGround = new ArrayList<>();

        for(String courseCode : userClasses){
            //Check the course list for all the courses to be taken and add them to staging ground
            for(Course course : courseList){
                if(course.getCourseCode().equals(courseCode) && !course.getSectionCode().contains("2")) {
                    stagingGround.add(course);
                }
            }
        }
        //Compare times and build schedule
        schedule.add(stagingGround.get(0));
        int counter = 1;
            for(Course courses : stagingGround){
                //Comparisons
                if(!schedule.get(counter - 1).getCourseCode().equals(courses.getCourseCode())){
                    schedule.add(courses);
                    System.out.println(courses.getCourseCode() + " Added");
                    counter++;
                }
                else{
                    System.out.println("NOT ADDED DIMWIT");
                }

            }


        System.out.println(schedule);

        return schedule;
    }

    public static String[] promptForClass() {
        String[] userClasses = new String[6];
        for (int i = 0; i < 5; i++) {
            userClasses[i] = promptForString("What is the Course Code (CSC180): ", false);
        }
        return userClasses;
    }

    public static ArrayList<Course> buildCourses(String path) {
        ArrayList<Course> courseList = new ArrayList<>();
        int counter = 0;
        String regex = "^([A-Z]{3}[0-9]{3})[ ]([A-Z0-9]{1,2})[ ]([A-z\\s\\:\\-]{1,})[ ]([0-9]{1})[ ]([0-9]{1,2})[:]([0-9]{1,2})[ ](AM|PM)[-]([0-9]{1,2})[:]([0-9]{1,2})[ ](AM|PM)[ ]([MTWHF]{1,5})[ ]([0-9]{3})";
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

    public static String promptForString(String prompt, boolean allowBlank) {
        if (prompt == null || prompt.isBlank()) {
            throw new IllegalArgumentException("The prompt cannot be null, empty, or just white space. prompt=" + prompt);
        }

        String input = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean inputIsInvalid = true;

        do {
            System.out.println(prompt);
            try {
                input = br.readLine();
                inputIsInvalid = input == null || (!allowBlank && input.isBlank());

                if (inputIsInvalid) {
                    System.out.println("Your input was invalid. Please, try again.");
                }
            } catch (IOException ioe) {
                System.out.println("There was a problem and your input was not received. Please, try again.");
            }

        } while (inputIsInvalid);

        return input;
    }
}
