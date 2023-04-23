package com.reporter.client.model;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PdfConverterTest {
    @Test
    public void basicGeneration() throws FileNotFoundException {
        PdfConverter pdfConverter = new PdfConverter(() -> "<h1>hello</h1><h3>world</h3>");

        pdfConverter.generatePDF(new FileOutputStream("out.pdf"));

    }
}
