package prog.schedule_application.controllers;

import prog.schedule_application.models.Course;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
        int counter = 0;
        String regex = "^([A-Z]{3}[0-9]{3})[ ]([A-Z0-9]{1,2})[ ]([A-z\\s\\:\\-]{1,})[ ]([0-9]{1})[ ]([0-9]{1,2})[:]([0-9]{1,2})[ ](AM|PM)[-]([0-9]{1,2})[:]([0-9]{1,2})[ ](AM|PM)[ ]([MTWHF]{1,5})[ ]([0-9]{3})";
        PDFtoTXT.test("src/main/files/test.pdf");
        for(String a : PDFtoTXT.pdfStrings){
            p = Pattern.compile(regex);
            m = p.matcher(a);
            if(m.find()){

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm");
                LocalTime startTime = LocalTime.of(Integer.parseInt(m.group(5)), Integer.parseInt(m.group(6)));
                if (m.group(7).equals("PM")){
                    startTime = LocalTime.of(Integer.parseInt(m.group(5)) + 12, Integer.parseInt(m.group(6)));
                }
//                LocalTime startTime = LocalTime.of(8,0);
                LocalTime endTime = LocalTime.of(Integer.parseInt(m.group(8)), Integer.parseInt(m.group(9)));
                if (m.group(10).equals("PM")) {
                    endTime = LocalTime.of(Integer.parseInt(m.group(8)) + 12, Integer.parseInt(m.group(9)));
                }
//                LocalTime endTime = LocalTime.of(9,30);
                String eventName = m.group(3);
                String days = m.group(11);
                String sectionCode = m.group(2);
                String courseCode = m.group(1);
                String roomNumber = m.group(12);
                Boolean isRequired = true;
                counter += 1;
                Course course = new Course(startTime, endTime, eventName, days, sectionCode, courseCode, roomNumber, isRequired);
                System.out.println(course);
            }
        }
    }


}
