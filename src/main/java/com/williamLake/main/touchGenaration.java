package com.williamlake.main;

import com.williamlake.main.data.Method;
import com.williamlake.main.data.Touch;

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

}
