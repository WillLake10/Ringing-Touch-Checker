package com.williamlake.main.data;

import java.util.Arrays;

public class Line {
    private String[] bLine;

    public Line(String[] bLine) {
        this.bLine = bLine;
    }

    public Line(int noBells) {
        this.bLine = new String[noBells];
    }

    public String[] getbLine() {
        return bLine;
    }

    public void setbLine(String[] bLine) {
        this.bLine = bLine;
    }

    @Override
    public String toString() {
        return "Line{" +
                "bLine=" + Arrays.toString(bLine) +
                '}';
    }

    public String toStringJustNum() {
        String returnString = "";
        for(int i = 0; i < bLine.length; i++){
            returnString += bLine[i];
        }
        return returnString;
    }
}
