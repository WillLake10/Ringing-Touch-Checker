package com.williamlake.main.data;

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
}
