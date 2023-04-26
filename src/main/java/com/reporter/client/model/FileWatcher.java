package com.reporter.client.model;

import java.io.IOException;
import java.nio.file.*;

public class FileWatcher {
    public static void registerFileWatcher(Path dir) {
        try {
            // Create a WatchService instance
            WatchService watchService = FileSystems.getDefault().newWatchService();

            // Register the directory with the WatchService for ENTRY_MODIFY events
            dir.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

            // Start an infinite loop to continuously listen for events
            while (!Thread.interrupted()) {
                WatchKey key;
                try {
                    // Wait for events
                    key = watchService.take();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }

                // Process events
                for (WatchEvent<?> event : key.pollEvents()) {
                    // Check if the event is an ENTRY_MODIFY event
                    if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
                        // Get the file name that was modified
                        Path modifiedFile = (Path) event.context();
                        System.out.println("File modified: " + modifiedFile);

                        // Call your custom action here, e.g., trigger some work
                        // For example, you can call a refresh() method or invoke other business logic
                        performCustomAction(modifiedFile);
                    }
                }

                // Reset the WatchKey to receive further events
                key.reset();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Custom action to be performed on file update (replace with your own logic)
    static void performCustomAction(Path modifiedFile) {
        System.out.println("Performing custom action for file: " + modifiedFile);
        // Replace with your own logic to handle the file modification event
    }
}

