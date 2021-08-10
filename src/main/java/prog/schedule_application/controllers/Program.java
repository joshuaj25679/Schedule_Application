package prog.schedule_application.controllers;

import java.util.ArrayList;
import java.util.List;

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
        String regex = "^([A-Z]{3}[0-9]{3})[ ]([A-Z0-9]{1})[ ]([A-z\\s\\:\\-]{1,})[ ]([0-9]{1})[ ]([0-9\\:]{4})[ ](AM|PM)[-]([0-9\\:]{4})[ ](AM|PM)[ ]([MTWHF]{1,5})[ ]([0-9]{3})";
        PDFtoTXT.test("src/main/files/test.pdf");
        for(String a : PDFtoTXT.pdfStrings){

        }
    }


}
