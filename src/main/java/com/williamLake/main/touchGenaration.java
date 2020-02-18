package com.williamlake.main;

import com.williamlake.main.data.Method;
import com.williamlake.main.data.Touch;
import com.williamlake.main.data.Line;

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
        Line bellLine[] = new Line[5000];
        for(int i = 0; i < 5000; i++){
            bellLine[i] = new Line(getRoughLength(callOrder, method));
        }

        return bellLine;
    }

    public static boolean checkLineIsTrue(Line[] line){
        boolean isTrue = true;

        return isTrue;
    }

}
