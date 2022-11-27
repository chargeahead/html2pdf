package com.demo.html2pdf;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.xhtmlrenderer.layout.SharedContext;
import org.xhtmlrenderer.pdf.ITextRenderer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        File htmlFile = new File("/home/demo/Documents/html2pdf/test.html");
        Document doc = Jsoup.parse(htmlFile,"UTF-8");
        doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        try (OutputStream os = new FileOutputStream("/home/demo/Documents/html2pdf/output.pdf")){
        	ITextRenderer renderer = new ITextRenderer();
        	SharedContext cntxt = renderer.getSharedContext();
        	cntxt.setPrint(true);
        	cntxt.setInteractive(false);
        	String baseUrl = FileSystems.getDefault().getPath("/home/demo/Documents/html2pdf")
        			         .toUri().toURL().toString();
        	renderer.setDocumentFromString(doc.html(), baseUrl);
        	renderer.layout();
        	renderer.createPDF(os);
        	System.out.println("done");
        }
    }
}
