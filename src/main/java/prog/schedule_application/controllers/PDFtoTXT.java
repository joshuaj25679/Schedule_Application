package prog.schedule_application.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFtoTXT {

    public static String[] pdfStrings;
    public static void test(String pathname){
        try {
            File file = new File(pathname);
            PDDocument document = Loader.loadPDF(file);
            //Instantiate PDFTextStripper class
            PDFTextStripper pdfStripper = new PDFTextStripper();
            //Retrieving text from PDF document
            String text = pdfStripper.getText(document);
            pdfStrings = text.split("\n");
//            System.out.println(text);
            //Closing the document
            document.close();

            } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }



}
