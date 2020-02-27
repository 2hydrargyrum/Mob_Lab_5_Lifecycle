package com.westjonathan.moblab5;

import android.content.SharedPreferences;

public class LifecycleCounts {
    private int creates, starts, resumes, pauses, stops, restarts, destroys;
    private int[] counts;
    public LifecycleCounts(){
        counts = new int[]{0, 0, 0, 0, 0, 0, 0};
    }
    // Accessor methods
    public int getCreates() {
        return counts[0];
    }
    public int getStarts() { return counts[1]; }
    public int getResumes() {
        return counts[2];
    }
    public int getPauses() {
        return counts[3];
    }
    public int getRestarts() {
        return counts[4];
    }
    public int getDestroys() {
        return counts[5];
    }
    public int getStops() {
        return counts[6];
    }
    public int getSpecCount(int choice) { return counts[choice]; }

    // Modifier methods:
    public void setStops(int stops) { this.counts[6] = stops; }
    public void setDestroys(int destroys) { this.counts[5] = destroys; }
    public void setRestarts(int restarts) {
        this.counts[4] = restarts;
    }
    public void setPauses(int pauses) {
        this.counts[3] = pauses;
    }
    public void setResumes(int resumes) {
        this.counts[2] = resumes;
    }
    public void setStarts(int starts) {
        this.counts[1] = starts;
    }
    public void setCreates(int creates) {
        this.counts[0] = creates;
    }
    public void setSpecCount(int choice, int newCount) { counts[choice] = newCount; }
}
