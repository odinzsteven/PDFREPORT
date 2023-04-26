package com.reporter.client.model;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Test {

    String dir = "C:\\html_test";
    String file = "file.txt";
    @org.junit.Test
    public void pathObject() {
        // Input dir and file paths


        // Create a Path object by providing dir and file as separate arguments
        Path path = Path.of(dir, file);

        // Print the Path object
        System.out.println("Path: " + path);
    }
}
