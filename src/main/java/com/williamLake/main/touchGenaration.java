package com.williamlake.main;

import com.williamlake.main.data.Method;
import com.williamlake.main.data.Touch;

import static com.williamlake.main.userInput.*;


public class touchGenaration {
    public Touch getTouchInstance(Method method){
        Touch touch = new Touch();
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
        String str = "pqrst";
        // characters to be searched
        char[] chSearch = {'p', 'q', 'r'};
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            for (int j = 0; j < chSearch.length; j++) {
                if (chSearch[j] != ch) {
                    return false;
                }
            }
        }
        return true;
    }

}
