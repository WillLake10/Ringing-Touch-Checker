package com.williamlake.main.dto;

public class Methord {
    private String methord_proper;
    private String notation;
    private int no_of_bells;
    private String name;

    public Methord(String notation, int no_of_bells, String methord_proper){
        this.no_of_bells = no_of_bells;
        this.notation = notation;
        this.methord_proper = methord_proper;
        this.name = getName(no_of_bells,methord_proper);
    }

    public String getName(int no_of_bells, String methord_proper){
        String Name;
        Name = methord_proper + getStage(no_of_bells);
        return Name;
    }

    public String getStage(int no_of_bells){
        switch (no_of_bells) {
            case 4:
                return "Minimus";
            case 5:
                return "Doubles";
            case 6:
                return "Minor";
            case 7:
                return "Triples";
            case 8:
                return "Major";
            case 9:
                return "Caters";
            case 10:
                return "Royal";
            case 11:
                return "Cinques";
            case 12:
                return "Maximus";
        }
        return null;
    }

    public String getNotation() {
        return notation;
    }

    public void setNotation(String notation) {
        this.notation = notation;
    }

    public int getNo_of_bells() {
        return no_of_bells;
    }

    public void setNo_of_bells(int no_of_bells) {
        this.no_of_bells = no_of_bells;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Methord{" +
                "methord_proper='" + methord_proper + '\'' +
                ", notation='" + notation + '\'' +
                ", no_of_bells=" + no_of_bells +
                ", name='" + name + '\'' +
                '}';
    }

}
