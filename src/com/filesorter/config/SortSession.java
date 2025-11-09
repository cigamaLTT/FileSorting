package com.filesorter.config;

import java.util.HashMap;
import java.util.Map;

public class SortSession {
    private String sessionId;
    private String sortMethod; // BY_EXTENSION, BY_SIZE, etc
    private String targetRoot; // target file after sorting
    private long timeStamp;

    private Map<String,String> fileMap;

    public SortSession(String sessionId, String sortMethod, String targetRoot) {
        this.sessionId = sessionId;
        this.sortMethod = sortMethod;
        this.targetRoot = targetRoot;
        this.timeStamp = System.currentTimeMillis();
        this.fileMap = new HashMap<>();
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getSortMethod() {
        return sortMethod;
    }

    public String getTargetRoot() {
        return targetRoot;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public Map<String, String> getFileMap() {
        return fileMap;
    }

    /**
     * Add a record of a moved file to this session's map
     * @param originalPath
     * @param afterSortPath
     */
    public void addFileToMap(String originalPath, String afterSortPath){
        if(this.fileMap != null){
            this.fileMap.put(originalPath, afterSortPath);
        }
    }
}
