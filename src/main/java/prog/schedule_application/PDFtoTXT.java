package prog.schedule_application;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFtoTXT {
    PDDocument document = PDDocument.load(new File("test.pdf"));
if (!document.isEncrypted()) {
        PDFTextStripper stripper = new PDFTextStripper();
        String text = stripper.getText(document);
        System.out.println("Text:" + text);
}
