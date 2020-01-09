package com.williamlake.main.data;

public class Methord {
    private String methord_proper;
    private String notation;
    private int no_of_bells;
    private String name;
    private String bob_notation;
    private String single_notation;


    public Methord(String notation, int no_of_bells, String methord_proper){
        this.no_of_bells = no_of_bells;
        this.notation = notation;
        this.methord_proper = methord_proper;
        this.name = getName(no_of_bells,methord_proper);
    }

    public Methord(String methord_proper, String notation, int no_of_bells, String bob_notation, String single_notation, String extra1_name, String extra1_notation, String extra2_name, String extra2_notation) {
        this.methord_proper = methord_proper;
        this.notation = notation;
        this.no_of_bells = no_of_bells;
        this.name = getName(no_of_bells,methord_proper);
        this.bob_notation = bob_notation;
        this.single_notation = single_notation;
    }

    public static String getName(int no_of_bells, String methord_proper){
        String Name;
        Name = methord_proper + " " + getStage(no_of_bells);
        return Name;
    }

    public static String getStage(int no_of_bells){
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

    public String getMethord_proper() {
        return methord_proper;
    }

    public void setMethord_proper(String methord_proper) {
        this.methord_proper = methord_proper;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getBob_notation() {
        return bob_notation;
    }

    public void setBob_notation(String bob_notation) {
        this.bob_notation = bob_notation;
    }

    public String getSingle_notation() {
        return single_notation;
    }

    public void setSingle_notation(String single_notation) {
        this.single_notation = single_notation;
    }

    @Override
    public String toString() {
        return "Methord{" +
                "methord_proper='" + methord_proper + '\'' +
                ", notation='" + notation + '\'' +
                ", no_of_bells=" + no_of_bells +
                ", name='" + name + '\'' +
                ", bob_notation='" + bob_notation + '\'' +
                ", single_notation='" + single_notation + '\'' +
                '}';
    }
}
