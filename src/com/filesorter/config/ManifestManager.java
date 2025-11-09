package com.filesorter.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ManifestManager {

    private static final String CONFIG_FOLDER = ".filesorter";
    private static final String MANIFEST_FILE = "manifest.json";

    private final Gson gson;
    private final Path manifestPath;

    public ManifestManager() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();

        String userHome = System.getProperty("user.home");
        Path configDirectory = Paths.get(userHome, CONFIG_FOLDER);
        this.manifestPath = configDirectory.resolve(MANIFEST_FILE);

        try{
            if(Files.notExists(configDirectory)){
                Files.createDirectories(configDirectory);
            }
        } catch (IOException e) {
            System.out.println("Failed to create a directory in " + configDirectory);
            throw new RuntimeException(e);
        }
    }

    /**
     * read the file in manifestPath
     * @return a manifest
     */
    public SortManifest loadManifest(){
        if(Files.notExists(manifestPath)){
            System.err.println("Manifest file not found!");
            return new SortManifest();
        }

        try(BufferedReader reader = Files.newBufferedReader(this.manifestPath)){
            SortManifest newSortManifest = this.gson.fromJson(reader, SortManifest.class);

            if(newSortManifest == null){
                return new SortManifest();
            }

            return newSortManifest;
        } catch (JsonSyntaxException e) {
            System.err.println("Manifest file is corrupted!");
            return new SortManifest();
        } catch (IOException e) {
            System.err.println("Cannot read file!");
            throw new RuntimeException(e);
        }
    }

    /**
     * Saves the SortManifest object to the manifest.json file
     * this will overwrite the existing file
     * @param manifest
     */
    public void saveManifest(SortManifest manifest){
        try (Writer writer = Files.newBufferedWriter(this.manifestPath)){
            this.gson.toJson(manifest, writer);
        } catch (IOException e) {
            System.err.println("Cannot write in file");
            throw new RuntimeException(e);
        }
    }
}