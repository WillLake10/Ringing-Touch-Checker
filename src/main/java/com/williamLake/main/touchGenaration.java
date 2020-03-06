package com.williamlake.main;

import com.williamlake.main.data.Method;
import com.williamlake.main.data.Touch;
import com.williamlake.main.data.Line;

import static com.williamlake.main.lead.*;
import static com.williamlake.main.userInput.*;


public class touchGenaration {
    public static Touch getTouchInstanceFromUser(Method method) {
        System.out.println("Chose an option: ");
        System.out.println("  1 - Enter calls");
        System.out.println("  2 - Enter tenner position at calls");
        int ans = getIntInput();
        Touch touch;
        if(ans == 1){
            touch = new Touch(getCallOrderrFromUser(), method, 1);
        }else{
            touch = new Touch(getCallPosFromUser(), method, 2);
        }

        return touch;
    }

    private static String getCallPosFromUser() {
        System.out.print("Enter the call Position for tenner: ");
        String call = getStringInput();
        System.out.println();
        String[] validChar = {"W", "H", "M", "S", "B", "I", "3rds", "4ths", "5ths", "6ths", "7ths", "8ths", "9ths"};
        while (!checkOnlyStringsWanted(validChar, call)) {
            System.out.println("Invalid input");
            System.out.print("Enter the call order: ");
            call = getStringInput();
            System.out.println();
        }
        return call;
    }

    private static String getCallOrderrFromUser() {
        System.out.print("Enter the call order: ");
        String call = getStringInput();
        System.out.println();
        char[] validChar = {'p', 'b', 's', 'P', 'B', 'S'};
        while (!checkOnlyCharsWanted(validChar, call)) {
            System.out.println("Invalid input");
            System.out.print("Enter the call order: ");
            call = getStringInput();
            System.out.println();
        }
        return call;
    }

    private static boolean checkOnlyCharsWanted(char[] validInput, String stringToTest) {
        boolean found = false;
        for (int i = 0; i < stringToTest.length(); i++) {
            char ch = stringToTest.charAt(i);
            for (int j = 0; j < validInput.length; j++) {
                if (validInput[j] != ch) {
                    found = true;
                }
            }
            if (!found) {
                return false;
            }
            found = false;
        }
        return true;
    }

    private static boolean checkOnlyStringsWanted(String[] validInput, String stringToTest) {
        boolean found = false;
        for (int i = 0; i < stringToTest.length(); i++) {
            String ch = stringToTest.split(",")[i];
            for (int j = 0; j < validInput.length; j++) {
                if (!validInput[j].equals(ch)) {
                    found = true;
                }
            }
            if (!found) {
                return false;
            }
            found = false;
        }
        return true;
    }

    private static int getRoughLength(String callOrder, Method method) {
        return (method.getPlain_course_length() / (method.getNo_of_bells() - method.getHunt_bells())) * callOrder.length();
    }

    public static Line[] getTouchBellLine(String callOrder, Method method) {
        Line bellLine[] = new Line[getRoughLength(callOrder, method) + method.getNo_of_bells()];
        Line firstLine = initiliseFirstLine(method.getNo_of_bells());
        boolean firstTime = true;
        String[] not = method.getNotation().split(",");
        String[] plain_lead = new String[not.length];
        String[] bob_lead = new String[not.length];
        String[] single_lead = new String[not.length];
        int length = 0;

        for (int i = 0; i < not.length; i++) {
            plain_lead[i] = not[i];
            bob_lead[i] = not[i];
            single_lead[i] = not[i];
        }

        bob_lead[Integer.parseInt(method.getCall_point())] = method.getBob_notation();
        single_lead[Integer.parseInt(method.getCall_point())] = method.getSingle_notation();


        for (int i = 0; i < bellLine.length; i++) {
            bellLine[i] = new Line(method.getNo_of_bells());
        }

        bellLine[0] = firstLine;
        //System.out.println("          " + bellLine[length].toStringJustNum());
        char calls[] = callOrder.toCharArray();


        for (int i = 0; i < calls.length; i++) {

            if (calls[i] == 'b') {
                not = bob_lead;
            } else if (calls[i] == 's') {
                not = single_lead;
            } else {
                not = plain_lead;
            }
            for (int notNumber = 0; notNumber < not.length; notNumber++) {
                if(!checkLineMatch(firstLine, bellLine[length]) || firstTime) {
                    firstTime = false;
                    bellLine[length + 1] = getNextLine(bellLine[length], not[notNumber]);
                    //System.out.println(bellLine[length].toStringJustNum());
                    length += 1;
                }
            }
            //System.out.println("        "+ calls[i] + " " + bellLine[length].toStringJustNum());
        }
        //System.out.println(bellLine[length].toStringJustNum());
        Line returnLine[] = new Line[length + 1];
        for (int i = 0; i < length; i++) {
            returnLine[i] = new Line(method.getNo_of_bells());
            returnLine[i] = bellLine[i];
        }
        returnLine[length] = returnLine[0];
        return returnLine;
    }

    public static boolean checkLineIsTrue(Line[] line) {
        boolean isTrue = true;
        for(int i = 0; i < line.length - 1; i++){
            for(int j = 0; j < line.length - 1; j++){
                if(i != j){
                    if(line[i] == line[j]){
                        isTrue = false;
                    }
                }
            }
        }
        //System.out.println(isTrue);
        return isTrue;
    }

    public static String getCalledFromBell(String callOrder, Line[] bellLines, int leadLength, int no_bells){
        String calledFromBell = "";
        char[] calls = callOrder.toCharArray();
        for(int i = 0; i < bellLines.length/leadLength; i++){
            if(calls[i] == 'b' || calls[i] == 's'){
                for(int j = 0; j < no_bells; j++){
                    if(bellLines[(i+1)*leadLength].getbLine()[j].equals(Integer.toString(no_bells))){
                        if(calls[i] == 's'){
                            calledFromBell += "S";
                        }
                        if(j == no_bells - 1){
                            calledFromBell += "H,";
                        }else if(j == no_bells - 2 && no_bells > 5){
                            calledFromBell += "W,";
                        }else if(j == no_bells - 3 && no_bells > 6){
                            calledFromBell += "M,";
                        }else if(j == 1){
                            if(calls[i] == 's'){
                                calledFromBell += "B,";
                            }else{
                                calledFromBell += "I,";
                            }
                        }else if(j == 2){
                            if(calls[i] == 's'){
                                calledFromBell += "3rds,";
                            }else{
                                calledFromBell += "B,";
                            }
                        }else if(j == 3){
                            calledFromBell += "4ths,";
                        }else if(j == 4){
                            calledFromBell += "5ths,";
                        }else if(j == 5){
                            calledFromBell += "6ths,";
                        }else if(j == 6){
                            calledFromBell += "7ths,";
                        }else if(j == 7){
                            calledFromBell += "8ths,";
                        }else if(j == 8){
                            calledFromBell += "9ths,";
                        }
                    }
                }
            }

        }
        calledFromBell = calledFromBell.substring(0,calledFromBell.length()-1);
        return calledFromBell;
    }

    public static int getBellPosPreviousLead(String call, String coursing_order, int num_bells){
        String bell = "";
        if(call.equals("H") || call.equals("SH")){
            bell = Integer.toString(num_bells);
        }else if((call.equals("W") || call.equals("SW")) && num_bells > 5){
            bell = Integer.toString(num_bells-1);
        }else if((call.equals("M") || call.equals("SM")) && num_bells > 6){
            bell = Integer.toString(num_bells-2);
        }else if((call.equals("I") || call.equals("SB")) && num_bells > 6){
            bell = "2";
        }else if((call.equals("B") || call.equals("S3rds")) && num_bells > 6){
            bell = "3";
        }else if((call.equals("4ths") || call.equals("S4ths")) && num_bells > 6){
            bell = "4";
        }else if((call.equals("5ths") || call.equals("S5ths")) && num_bells > 6){
            bell = "5";
        }else if((call.equals("6ths") || call.equals("S6ths")) && num_bells > 6){
            bell = "6";
        }else if((call.equals("7ths") || call.equals("S7ths")) && num_bells > 6){
            bell = "7";
        }else if((call.equals("8ths") || call.equals("S8ths")) && num_bells > 6){
            bell = "8";
        }else if((call.equals("9ths") || call.equals("S9ths")) && num_bells > 6){
            bell = "9";
        }
        String[] split = coursing_order.split("");
        int returnBell = 0;

        for(int i=coursing_order.length(); i >= 0; i--){
            if(bell.equals(split[i])){
                returnBell = Integer.parseInt(split[i-1]);
                break;
            }
        }
        return returnBell;
    }

    public static String CalledFromToCallOrder(){
        String callOrder = "";
        return callOrder;
    }

}
