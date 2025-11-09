package com.filesorter.core;

import com.filesorter.config.ManifestManager;
import com.filesorter.config.RuleLoader;
import com.filesorter.config.SortManifest;
import com.filesorter.config.SortSession;
import com.filesorter.util.FileUtils;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

public class SortEngine {

    private final RuleLoader ruleLoader;
    private final ManifestManager manifestManager;
    private final SortManifest currentManifest;

    public SortEngine() {
        this.ruleLoader = new RuleLoader();
        this.manifestManager = new ManifestManager();
        this.currentManifest = this.manifestManager.loadManifest();
    }

    /**
     * Starts the sorting process based on the given method.
     * @param source The directory to sort.
     * @param target The root directory to move sorted files into.
     * @param method The sorting method (e.g., "BY_EXTENSION").
     */
    public void startSorting(Path source, Path target, String method) {
        String sessionId = "session_" + UUID.randomUUID();
        SortSession newSession = new SortSession(sessionId, method, target.toString());

        int filesMovedCount = 0;

        System.out.println("Starting cleanup for: " + source);
        System.out.println("Using Method: " + method);

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(source)) {
            for (Path entry : stream) {

                if (Files.isDirectory(entry)) continue;
                if (entry.getFileName().toString().equals("FileSorting.jar")) continue;

                if (!method.equalsIgnoreCase("BY_EXTENSION")) {
                    System.err.println("Error: Sort method '" + method + "' is not yet supported.");
                    break;
                }

                String fileExtension = FileUtils.getFileExtension(entry);
                String targetFolderName = this.ruleLoader.getFolderFromExtension(fileExtension);

                if (targetFolderName == null) {
                    System.out.println(entry.getFileName() + " -> [SKIPPED]");
                    continue;
                }

                Path finalTargetDirectory = target.resolve(targetFolderName);

                try {
                    String originalPathStr = entry.toAbsolutePath().toString();
                    Path newFilePath = FileUtils.moveFile(entry, finalTargetDirectory);
                    String newPathStr = newFilePath.toAbsolutePath().toString();
                    newSession.addFileToMap(originalPathStr, newPathStr);

                    System.out.println(entry.getFileName() + " -> MOVED to " + targetFolderName);
                    ++filesMovedCount;
                } catch (IOException e) {
                    System.err.println("Error moving " + entry.getFileName() + ": " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Critical error reading directory stream.");
            e.printStackTrace();
        }

        this.currentManifest.addSession(newSession);
        this.manifestManager.saveManifest(this.currentManifest);

        System.out.println("\nCleanup complete! Moved " + filesMovedCount + " files.");
    }
}
