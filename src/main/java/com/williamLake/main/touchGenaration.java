package com.williamlake.main;

import com.williamlake.main.data.Method;
import com.williamlake.main.data.Touch;
import com.williamlake.main.data.Line;

import static com.williamlake.main.lead.*;
import static com.williamlake.main.userInput.*;


public class touchGenaration {
    public Touch getTouchInstanceFromUser(Method method){
        Touch touch = new Touch(getCallOrderrFromUser(), method);
        return touch;
    }

    private String getCallOrderrFromUser(){
        System.out.print("Enter the call order: ");
        String call = getStringInput();
        System.out.println();
        char[] validChar = {'p', 'b', 's', 'P', 'B', 'S'};
        while (!checkOnlyCharsWanted(validChar, call)){
            System.out.println("Invalid input");
            System.out.print("Enter the call order: ");
            call = getStringInput();
            System.out.println();
        }
        return call;
    }

    private boolean checkOnlyCharsWanted(char[] validInput, String stringToTest){
        for (int i = 0; i < stringToTest.length(); i++) {
            char ch = stringToTest.charAt(i);
            for (int j = 0; j < validInput.length; j++) {
                if (validInput[j] != ch) {
                    return false;
                }
            }
        }
        return true;
    }

    private static int getRoughLength(String callOrder, Method method){
        return (method.getPlain_course_length()/(method.getNo_of_bells()-method.getHunt_bells()))*callOrder.length();
    }

    public static Line[] getTouchBellLine(String callOrder, Method method){
        Line bellLine[] = new Line[getRoughLength(callOrder, method)];
        Line firstLine = initiliseFirstLine(method.getNo_of_bells());
        Line currentLine = firstLine;
        String[] not = method.getNotation().split(",");
        String[] plain_lead = new String[not.length];
        String[] bob_lead = new String[not.length];
        String[] single_lead = new String[not.length];
        int length = 0;

        for(int i = 0; i < not.length; i++){
            plain_lead[i] = not[i];
            bob_lead[i] = not[i];
            single_lead[i] = not[i];
        }

        bob_lead[Integer.parseInt(method.getCall_point())] = method.getBob_notation();
        single_lead[Integer.parseInt(method.getCall_point())] = method.getSingle_notation();


        for(int i = 0; i < bellLine.length; i++){
            bellLine[i] = new Line(method.getNo_of_bells());
        }

        for(char call : callOrder.toCharArray()){
            if(call == 'b'){
                not = bob_lead;
            }else if(call == 's'){
                not = bob_lead;
            }else{
                not = plain_lead;
            }
            for (int notNumber = 0; notNumber < not.length; notNumber++) {
                bellLine[length] = getNextLine(bellLine[length-1], not[notNumber]);
                length += 1;
                System.out.println(bellLine[length-1].toStringJustNum());
            }
        }
        Line returnLine[] = new Line[length];
        for(int i = 0; i < length; i++){
            returnLine[i] = new Line(method.getNo_of_bells());
            returnLine[i] = bellLine[i];
        }
        return returnLine;
    }

    public static boolean checkLineIsTrue(Line[] line){
        boolean isTrue = true;

        return isTrue;
    }

}
