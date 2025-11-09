package com.filesorter.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {
    /**
     * Extracts the file extension from a Path
     * @param path path of the file
     * @return the extension, .mp4 or something else
     */
    public static String getFileExtension(Path path){

        if(Files.isDirectory(path)){
            return "";
        }

        int lastDot = path.getFileName().toString().lastIndexOf('.');
        String fileExtension = "";
        if (lastDot != -1 && lastDot != 0) {
            fileExtension = path.getFileName().toString().substring(lastDot);
        }

        return fileExtension;
    }

    /**
     * move the file from source to target
     * change the name if it already exists
     * @param source
     * @param target
     * @throws IOException
     */
    public static Path moveFile(Path source, Path target) throws IOException{
        Path originalFileName = source.getFileName();
        String fileName = originalFileName.toString();

        int lastDot = fileName.lastIndexOf('.');
        String baseName = "";
        if(lastDot >= 1) {
            baseName = fileName.substring(0, lastDot);
        } else {
            baseName = fileName;
        }

        String extension = getFileExtension(source);

        Path targetPath = target.resolve(originalFileName);
        int counter = 0;

        while (Files.exists(targetPath)) {
            counter++;
            String newFileName = baseName + " (" + counter + ")" + extension;
            targetPath = target.resolve(newFileName);
        }
        Files.createDirectories(targetPath.getParent());
        Files.move(source, targetPath);

        return targetPath;
    }
}
