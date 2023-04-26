//package com.reporter.client.model;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.nio.file.*;
//import java.util.concurrent.TimeUnit;
//
//import static org.junit.Assert.*;
//
//public class FileWatcherTest {
//
//    private FileWatcher fileWatcher;
//    private boolean actionPerformed;
//    private String directory = new com.reporter.client.model.Test().dir;
//    private String file = new com.reporter.client.model.Test().file;
//    private Path direct = Path.of(directory, file);
//
//
//    @Before
//    public void setup() {
//        fileWatcher = new FileWatcher();
//        actionPerformed = false;
//    }
//
//    @Test
//    public void testFileWatcher() throws IOException, InterruptedException {
//        // Create a test file
//        Path testFile = direct;
//
//        if (!Files.exists(Path.of(directory))) {
//            Files.createFile(testFile);
//            System.out.println("File created: " + direct);
//        } else {
//            System.out.println("File already exists: " + direct);
//        }
//
//        // Run the file watcher logic in a separate thread
//        Thread watcherThread = new Thread(() -> {
//            try {
//                fileWatcher.registerFileWatcher(direct);
//            } catch (IOException | InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        watcherThread.start();
//
//        // Sleep for a short time to allow the file watcher to start
//        Thread.sleep(1000);
//
//        // Modify the test file
//        Files.write(testFile, "Test data".getBytes(), StandardOpenOption.APPEND);
//        System.out.println("Modification happened: " + testFile); // Print message for modification
//
//        // Sleep for a short time to allow the file watcher to process the event
//        Thread.sleep(1000);
//
//        // Verify that the expected changes were made by the file watcher
//        assertTrue(actionPerformed);
//
//        // Clean up
//        watcherThread.interrupt();
//    }
//
//    @Test
//    public void performCustomAction() {
//        // This is a placeholder method that is called by the file watcher
//        // You can modify this method to perform your custom action
//        actionPerformed = true;
//        try {
//            new PdfConverterTest().basicGeneration();
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private class FileWatcher {
//
//        void registerFileWatcher(Path directory) throws IOException, InterruptedException {
//            try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
//                directory.register(watchService,
//                        StandardWatchEventKinds.ENTRY_CREATE,
//                        StandardWatchEventKinds.ENTRY_MODIFY,
//                        StandardWatchEventKinds.ENTRY_DELETE
//                );
//                System.out.println("FileWatcher registered for directory: " + directory);
//
//                WatchKey key;
//                while ((key = watchService.take()) != null) {
//                    if (!key.pollEvents().isEmpty()) {
//                        System.out.println("refresh");
//                    }
//                    key.reset();
//                }
//            }
//        }
//    }
//}
//
