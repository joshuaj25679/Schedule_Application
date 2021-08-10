package prog.schedule_application.controllers;

import java.util.ArrayList;

public class Program {
    private static String pathName = "src/main/files/test.pdf";


    public static void run(){

    }

    public static void stringSplitter(ArrayList<String> pdfString){
        //Use regex on pdf information string to separate values for Event/Course constructor
        //Return ArrayList<String> of the separated values
    }

    public static void buildCourses(){
        //Take in ArrayList<String> Parameter
        //Use values in Parameter to build Courses
        //Add Courses to ArrayList<Course>
        //Return ArrayList<Course>
        PDFtoTXT.test("src/main/files/test.pdf");

    }


}
