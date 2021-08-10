package prog.schedule_application.controllers;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFtoTXT {
    public static void test(){
        try {
            //TODO Make Pathname dynamic
            File file = new File("src/main/files/test.pdf");
            PDDocument document = Loader.loadPDF(file);
            //Instantiate PDFTextStripper class
            PDFTextStripper pdfStripper = new PDFTextStripper();
            //Retrieving text from PDF document
            String text = pdfStripper.getText(document);
            System.out.println(text);
            //Closing the document
            document.close();
            } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }



}
