package com.williamlake.main;

import java.util.Arrays;

public class lead {
    public static Boolean checkRounds(String[] currentLine, String[] firstLine){
        return Arrays.equals(currentLine, firstLine);
    }

    public static String[] getNextLine(String[] currentline, String notation) {
        String[] nextLine;
        if ("x".equals(notation) || "X".equals(notation)) {
            nextLine = getLineIfAllChange(currentline);
        } else {
            nextLine = getLineIfNotAllChange(currentline, notation);
        }
        return nextLine;
    }

    public static String[] getLineIfAllChange(String[] currentline) {
        String[] nextLine = new String[currentline.length];
        int size = currentline.length;
        for (int i = 0; i < size; i++) {
            if (i % 2 == 0) {
                nextLine[i] = currentline[i + 1];
            } else {
                nextLine[i] = currentline[i - 1];
            }
        }
        return nextLine;
    }

    public static String[] getLineIfNotAllChange(String[] currentline, String notation) {
        String[] nextLine = new String[currentline.length];
        boolean[] done = new boolean[currentline.length];
        int temp;
        String[] parts = new String[notation.length()];
        for (int i = 0; i < notation.length(); i++){
            parts[i] = Character.toString(notation.charAt(i));
        }

        for (int i = 0; i < parts.length; i++) {
            if ("1".equals(parts[i]) || "2".equals(parts[i]) || "3".equals(parts[i]) ||
                    "4".equals(parts[i]) || "5".equals(parts[i]) ||
                    "6".equals(parts[i]) || "7".equals(parts[i]) ||
                    "8".equals(parts[i]) || "9".equals(parts[i])) {
                temp = Integer.parseInt(parts[i]) - 1;
            } else if ("0".equals(parts[i])) {
                temp = 9;
            } else if ("E".equals(parts[i])) {
                temp = 10;
            } else {
                temp = 11;
            }
            nextLine[temp] = currentline[temp];
            done[temp] = true;
        }

        for (int j = 0; j < done.length; j++) {
            if (done[j] == false) {
                nextLine[j] = currentline[j + 1];
                nextLine[j + 1] = currentline[j];
                done[j] = true;
                done[j + 1] = true;
            }
        }

        return nextLine;
    }

    public static String[] initiliseFirstLine(int numBells) {
        String[] firstLine = new String[numBells];
        String position;
        for (int i = 1; i <= numBells; i++) {
            if (i == 10) {
                position = "0";
            } else if (i == 11) {
                position = "E";
            } else if (i == 12) {
                position = "T";
            } else {
                position = Integer.toString(i);
            }
            firstLine[i - 1] = position;
        }
        return firstLine;
    }

    void outLine(String[] line){
        for(String bell : line){
            System.out.print(bell);
        }
        System.out.println();
    }
}
