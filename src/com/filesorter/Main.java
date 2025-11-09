package com.filesorter;

import com.filesorter.config.RuleLoader;
import com.filesorter.core.SortEngine;
import com.filesorter.util.FileUtils;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Path willBeSortFolder;
        Path targetFolderAfterSort;
        String sortMethod;

        if (args.length == 0) {
            String currentDirectory = System.getProperty("user.dir");
            willBeSortFolder = Paths.get(currentDirectory);
            targetFolderAfterSort = Paths.get(currentDirectory);
            sortMethod = "BY_EXTENSION";

        } else if (args.length == 3) {
            willBeSortFolder = Paths.get(args[0]);
            targetFolderAfterSort = Paths.get(args[1]);
            sortMethod = args[2];

        } else {
            System.err.println("Usage: java -jar FileSorter.jar <Source_Directory> <Target_Directory> <Sort_Method>");
            System.err.println("Example: ... C:\\Downloads D:\\Sorted BY_EXTENSION");
            return;
        }

        //For debug only, will remove right before making this a jar file
        String userHome = System.getProperty("user.home");
        willBeSortFolder = Paths.get(userHome, "Downloads");
        targetFolderAfterSort = Paths.get(userHome, "Downloads");
        sortMethod = "BY_EXTENSION";

        SortEngine engine = new SortEngine();
        engine.startSorting(willBeSortFolder, targetFolderAfterSort, sortMethod);
    }
}