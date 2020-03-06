package com.williamlake.main.data;

import static com.williamlake.main.lead.*;

public class Method {
    private String method_proper;
    private String notation;
    private int no_of_bells;
    private String name;
    private String bob_notation;
    private String single_notation;
    private int plain_course_length;
    private String call_point;
    private int hunt_bells;
    private int lead_length;
    private String coursing_order;;

    public int getPlain_course_length() {
        return plain_course_length;
    }

    public void setPlain_course_length(int plain_course_length) {
        this.plain_course_length = plain_course_length;
    }

    public Method(String method_proper, String notation, int no_of_bells, String bob_notation, String single_notation, String call_point, int hunt_bells) {
        this.method_proper = method_proper;
        this.notation = notation;
        this.no_of_bells = no_of_bells;
        setName(no_of_bells, method_proper);
        this.bob_notation = bob_notation;
        this.single_notation = single_notation;
        this.plain_course_length = findPlainCourseLength(notation, no_of_bells);
        this.call_point = call_point;
        this.hunt_bells = hunt_bells;
        this.lead_length = plain_course_length / (no_of_bells - hunt_bells);
        this.coursing_order = getCoursingOrder(no_of_bells, notation);
    }

    private String getCoursingOrder(int num_bells, String notation) {
        Line first = initiliseFirstLine(num_bells);
        Line leadend = getLeadEnd(first, notation);
        String current = Integer.toString(num_bells);
        String coursingOrder = current;
        for(int i = 0; i < num_bells - 1; i++){
            for(int j = 0; j < leadend.getbLine().length; j++){
                if(leadend.getbLine()[j] .equals(current)){
                    coursingOrder += Integer.toString(j+1);
                    current = Integer.toString(j+1);
                    break;
                }
            }
        }
        return coursingOrder;
    }


    public Method() {
        this.method_proper = "";
        this.notation = "";
        this.no_of_bells = 0;
        setName(no_of_bells, method_proper);
        this.bob_notation = "";
        this.single_notation = "";
        this.plain_course_length = 0;
        this.call_point = "";
        this.hunt_bells = 1;
        this.lead_length = 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLead_length() {
        return lead_length;
    }

    public void setLead_length(int lead_length) {
        this.lead_length = lead_length;
    }

    public int getHunt_bells() {
        return hunt_bells;
    }

    public void setHunt_bells(int hunt_bells) {
        this.hunt_bells = hunt_bells;
    }

    public String getCall_point() {
        return call_point;
    }

    public void setCall_point(String call_point) {
        this.call_point = call_point;
    }

    public void setName(int no_of_bells, String methord_proper) {
        String Name;
        Name = methord_proper + " " + getStage(no_of_bells);
        this.name = Name;
    }

    public static String getStage(int no_of_bells) {
        switch (no_of_bells) {
            case 0:
                return "";
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

    public String getMethod_proper() {
        return method_proper;
    }

    public void setMethod_proper(String methord_proper) {
        this.method_proper = methord_proper;
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
        return "Method{" +
                "method_proper='" + method_proper + '\'' +
                ", notation='" + notation + '\'' +
                ", no_of_bells=" + no_of_bells +
                ", name='" + name + '\'' +
                ", bob_notation='" + bob_notation + '\'' +
                ", single_notation='" + single_notation + '\'' +
                ", plain_course_length=" + plain_course_length +
                ", call_point='" + call_point + '\'' +
                ", hunt_bells=" + hunt_bells +
                ", lead_length=" + lead_length +
                ", coursing_order='" + coursing_order + '\'' +
                '}';
    }

    public static int findPlainCourseLength(String notation, int No_of_bells) {
        int length = 0;

        String[] not = notation.split(",");
        Line firstLine = initiliseFirstLine(No_of_bells);
        Line currentLine = firstLine;

        Boolean firstTime = true;
        while (!checkLineMatch(currentLine, firstLine) || firstTime) {
            firstTime = false;
            for (int notNumber = 0; notNumber < not.length; notNumber++) {
                currentLine = getNextLine(currentLine, not[notNumber]);
                length += 1;
            }
        }
        return length;
    }

}
