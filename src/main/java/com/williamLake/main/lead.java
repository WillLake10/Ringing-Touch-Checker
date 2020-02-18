package com.williamlake.main;

import com.williamlake.main.data.Line;

import java.util.Arrays;

public class lead {
    public static Boolean checkLineMatch(Line currentLine, Line firstLine){
        return Arrays.equals(currentLine.getbLine(), firstLine.getbLine());
    }

    public static Line getNextLine(Line currentline, String notation) {
        Line nextLine;
        if ("x".equals(notation) || "X".equals(notation)) {
            nextLine = getLineIfAllChange(currentline);
        } else {
            nextLine = getLineIfNotAllChange(currentline, notation);
        }
        return nextLine;
    }

    public static Line getLineIfAllChange(Line currentline) {
        Line nextLine = new Line(currentline.getbLine().length);
        int size = currentline.getbLine().length;
        for (int i = 0; i < size; i++) {
            if (i % 2 == 0) {
                nextLine.getbLine()[i] = currentline.getbLine()[i + 1];
            } else {
                nextLine.getbLine()[i] = currentline.getbLine()[i - 1];
            }
        }
        return nextLine;
    }

    public static Line getLineIfNotAllChange(Line currentline, String notation) {
        Line nextLine = new Line(currentline.getbLine().length);
        boolean[] done = new boolean[currentline.getbLine().length];
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
            nextLine.getbLine()[temp] = currentline.getbLine()[temp];
            done[temp] = true;
        }

        for (int j = 0; j < done.length; j++) {
            if (done[j] == false) {
                nextLine.getbLine()[j] = currentline.getbLine()[j + 1];
                nextLine.getbLine()[j + 1] = currentline.getbLine()[j];
                done[j] = true;
                done[j + 1] = true;
            }
        }

        return nextLine;
    }

    public static Line initiliseFirstLine(int numBells) {
        Line firstLine = new Line(numBells);
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
            firstLine.getbLine()[i - 1] = position;
        }
        return firstLine;
    }

    public static Line getLeadEnd(Line currentLine, String notation){
        String[] not = notation.split(",");
        for (int notNumber = 0; notNumber < not.length; notNumber++) {
            currentLine = getNextLine(currentLine, not[notNumber]);
        }
        return currentLine;
    }

    public static int[] getBellsEffectedByCall(String notation, int No_of_bells, String callNot){
        char[] planeMove = new char[No_of_bells];
        char[] callMove = new char[No_of_bells];
        String[] not = notation.split(",");
        String notTemp = not[not.length - 1];
        char[] notOriginal = notTemp.toCharArray();
        int[] temp = new int[30];
        int length = 0;
        for(String cNot : callNot.split(",")){
            char[] notCall = cNot.toCharArray();
            for(char bell : notOriginal){
                planeMove[Character.getNumericValue(bell) - 1] = 's';
            }
            for(char bell : notCall){
                callMove[Character.getNumericValue(bell) - 1] = 's';
            }

            char lastOrigingal = 'd';
            char lastCall = 'd';
            for(int i = 0; i < No_of_bells; i++){
                if(planeMove[i] != 's'){
                    if(lastOrigingal == 'd'){
                        planeMove[i] = 'u';
                        lastOrigingal = 'u';
                    }else if(lastOrigingal == 'u'){
                        planeMove[i] = 'd';
                        lastOrigingal = 'd';
                    }
                }
                if(callMove[i] != 's'){
                    if(lastCall == 'd'){
                        callMove[i] = 'u';
                        lastCall = 'u';
                    }else if(lastCall == 'u'){
                        callMove[i] = 'd';
                        lastCall = 'd';
                    }
                }

                if(planeMove[i] != callMove[i]){
                    temp[length] = i;
                    length += 1;
                }
            }

        }


        int bellPos[] = new int[length];
        int returnVal[] = new int[length];

        for(int j = 0; j < length; j++){
            bellPos[j] = temp[j];
        }

        Line leadEnd = getLeadEnd(initiliseFirstLine(No_of_bells), notation);

        for(int j = 0; j < length; j++){
            returnVal[j] = Integer.parseInt(leadEnd.getbLine()[bellPos[j]]);
        }

        return returnVal;
    }

    void outLine(String[] line){
        for(String bell : line){
            System.out.print(bell);
        }
        System.out.println();
    }
}
