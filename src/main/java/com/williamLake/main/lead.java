package com.williamlake.main;

import java.util.Arrays;

public class lead {
    public static Boolean checkLineMatch(String[] currentLine, String[] firstLine){
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

    public static String[] getLeadEnd(String[] currentLine, String notation){
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
        char[] notCall = callNot.toCharArray();
        int[] temp = new int[12];
        int length = 0;

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
        int bellPos[] = new int[length];
        int returnVal[] = new int[length];

        for(int j = 0; j < length; j++){
            bellPos[j] = temp[j];
        }

        String[] leadEnd = getLeadEnd(initiliseFirstLine(No_of_bells), notation);

        for(int j = 0; j < length; j++){
            returnVal[j] = Integer.parseInt(leadEnd[bellPos[j]]);
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
