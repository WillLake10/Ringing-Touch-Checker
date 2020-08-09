package com.williamLake.main.data;

public class CccbrMethod {
    private int stage;
    private String classification;
    private int lengthOfLead;
    private int numberOfHunts;
    private String id;
    private String title;
    private String name;
    private String notation;
    private String symmetry;
    private String leadHead;

    public CccbrMethod(int stage, String classification, int lengthOfLead, int numberOfHunts, String id, String title, String name, String notation, String symmetry, String leadHead) {
        this.stage = stage;
        this.classification = classification;
        this.lengthOfLead = lengthOfLead;
        this.numberOfHunts = numberOfHunts;
        this.id = id;
        this.title = title;
        this.name = name;
        this.notation = notation;
        this.symmetry = symmetry;
        this.leadHead = leadHead;
    }

    public CccbrMethod() {
        this.stage = 0;
        this.classification = "";
        this.lengthOfLead = 0;
        this.numberOfHunts = 1;
        this.id = "";
        this.title = "";
        this.name = "";
        this.notation = "";
        this.symmetry = "";
        this.leadHead = "";
    }

    @Override
    public String toString() {
        return "\nCccbrMethod{" +
                "title='" + title + '\'' +
                ", stage=" + stage +
                ", classification='" + classification + '\'' +
                ", lengthOfLead=" + lengthOfLead +
                ", numberOfHunts=" + numberOfHunts +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", notation='" + notation + '\'' +
                ", symmetry='" + symmetry + '\'' +
                ", leadHead='" + leadHead + '\'' +
                '}';
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public int getLengthOfLead() {
        return lengthOfLead;
    }

    public void setLengthOfLead(int lengthOfLead) {
        this.lengthOfLead = lengthOfLead;
    }

    public int getNumberOfHunts() {
        return numberOfHunts;
    }

    public void setNumberOfHunts(int numberOfHunts) {
        this.numberOfHunts = numberOfHunts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotation() {
        return notation;
    }

    public void setNotation(String notation) {
        this.notation = notation;
    }

    public String getSymmetry() {
        return symmetry;
    }

    public void setSymmetry(String symmetry) {
        this.symmetry = symmetry;
    }

    public String getLeadHead() {
        return leadHead;
    }

    public void setLeadHead(String leadHead) {
        this.leadHead = leadHead;
    }
}
