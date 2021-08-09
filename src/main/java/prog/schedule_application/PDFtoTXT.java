package prog.schedule_application;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFtoTXT {

    public static void test(){
        try {
            PDDocument document = PDDocument.class.cast(new File("test.pdf"));
            if (!document.isEncrypted()) {
                PDFTextStripper stripper = new PDFTextStripper();
                String text = stripper.getText(document);
                System.out.println("Text:" + text);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
