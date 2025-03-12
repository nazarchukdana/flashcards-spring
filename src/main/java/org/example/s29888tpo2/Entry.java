package org.example.s29888tpo2;

public class Entry {
    private String english;
    private String polish;
    private String german;

    public Entry(String english, String polish, String german) {
        this.english = english;
        this.polish = polish;
        this.german = german;
    }

    public String getPolish() { return polish; }
    public String getEnglish() { return english; }
    public String getGerman() { return german; }

    @Override
    public String toString(){
        return english + " -- "+polish+" -- "+german;
    }
}
