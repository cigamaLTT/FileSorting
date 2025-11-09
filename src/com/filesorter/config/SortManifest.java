package com.filesorter.config;

import java.util.ArrayList;
import java.util.List;

public class SortManifest {
    private List<SortSession> activeSessions;

    public SortManifest() {
        this.activeSessions = new ArrayList<>();
    }

    /**
     * add a new session to the list
     * @param newSortSession
     */
    public void addSession(SortSession newSortSession){
        if(newSortSession != null){
            this.activeSessions.add(newSortSession);
        }
    }
}
