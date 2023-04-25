package com.reporter.client.model;

import org.junit.Test;

import java.io.*;
import java.util.Scanner;

public class PdfConverterTest {
    @Test
    public void basicGeneration() throws FileNotFoundException {
        // TODO#2 read these from the UI
        String workdir = "C:\\html_test";
        String htmlFile = "wood_bill_simple.html";

        // TODO#1 register file watcher at the workdir and call refresh in each update
        refresh(workdir, htmlFile);
    }

    private void refresh(String workdir, String file) throws FileNotFoundException {
        File outputDir = new File("out/pdf/");
        outputDir.mkdirs();
        try (Scanner scanner = new Scanner(new File(workdir, file))) {
            scanner.useDelimiter("\\Z");
            File output = new File(outputDir, "wood_bill_" + System.currentTimeMillis() + ".pdf");
            PdfConverter.INSTANCE.generatePDF(workdir, scanner.next(), new FileOutputStream(output));
        }
    }
}
