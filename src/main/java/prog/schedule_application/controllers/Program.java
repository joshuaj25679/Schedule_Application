package prog.schedule_application.controllers;

import prog.schedule_application.models.Course;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Program {
    private static Pattern p = null;
    private static Matcher m = null;
    private static String pathName = "src/main/files/test.pdf";


    public static void run(){

    }

    public static void buildCourses(){
        //Take in ArrayList<String> Parameter
        //Use values in Parameter to build Courses
        //Add Courses to ArrayList<Course>
        //Return ArrayList<Course>
        int counter = 0;
        String regex = "^([A-Z]{3}[0-9]{3})[ ]([A-Z0-9]{1})[ ]([A-z\\s\\:\\-]{1,})[ ]([0-9]{1})[ ]([0-9\\:]{4})[ ](AM|PM)[-]([0-9\\:]{4})[ ](AM|PM)[ ]([MTWHF]{1,5})[ ]([0-9]{3})";
        PDFtoTXT.test("src/main/files/test.pdf");
        for(String a : PDFtoTXT.pdfStrings){
            p = Pattern.compile(regex);
            m = p.matcher(a);
            if(m.find()){
                LocalTime startTime = LocalTime.parse(m.group(5));
                LocalTime endTime = LocalTime.parse(m.group(7));
                String eventName = m.group(3);
                String days = m.group(9);
                String sectionCode = m.group(2);
                String courseCode = m.group(1);
                String roomNumber = m.group(10);
                Boolean isRequired = true;
                counter += 1;
                Course course = new Course(startTime, endTime, eventName, days, sectionCode, courseCode, roomNumber, isRequired);
                System.out.println(course);
            }
        }
    }


}
