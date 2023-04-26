package com.reporter.client.model;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class WatcherTest {

    private static Executor watcher = Executors.newSingleThreadExecutor();
    public static void main(String[] args) {
        watcher.execute(() -> {
            try {
                registerFileWatcher(Path.of("C:/html_test"));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    static void registerFileWatcher(Path directory) throws IOException, InterruptedException {
        try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
            directory.register(watchService,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_MODIFY,
                    StandardWatchEventKinds.ENTRY_DELETE
            );
            System.out.println("FileWatcher registered for directory: " + directory);

            WatchKey key;
            while ((key = watchService.take()) != null) {
                if (!key.pollEvents().isEmpty()) {
                    new
                    System.out.println("refresh");
                }
                key.reset();
            }
        }
    }

}
