package com.williamlake.main;

import com.williamlake.main.data.Method;
import com.williamlake.main.data.Touch;

import static com.williamlake.main.methodGenaration.getMethodInstanceFromUser;
import static com.williamlake.main.touchGenaration.getTouchInstanceFromUser;
import static com.williamlake.main.userInput.getIntInput;

public class Main {

    public static void main(String[] args) {
        Method workingMethod = getMethodInstanceFromUser();
        System.out.println(workingMethod.toString());
        int answer = getWhatUsertWantsToDoWithMethod();
        if(answer == 1){
            drawBlueLine(workingMethod);
        } else if(answer == 2){
            Touch workingTouch = getTouchInstanceFromUser(workingMethod);
            System.out.println(workingTouch.toString());
        }

    }

    public static void drawBlueLine(Method method){
        System.out.println("Chose an option: ");
        System.out.println("  1 - Draw just blue Line");
        System.out.println("  2 - Draw blue Line with others gray");
        System.out.println("  3 - Draw grid");
        System.out.println("  4 - Draw Bob");
        System.out.println("  5 - Draw Single");
        System.out.println("  6 - Draw All");
        System.out.println("  9 - End");
        int answer = getIntInput();
        if (answer == 6){
            draw.main(method, 1, 10);
            draw.main(method, 4, 500);
            draw.main(method, 5, 1000);
        } else {
            draw.main(method, answer, 10);
        }
    }

    private static int getWhatUsertWantsToDoWithMethod(){
        System.out.println("Chose an option: ");
        System.out.println("  1 - Draw Blue Lines");
        System.out.println("  2 - Make Touch");
        return getIntInput();
    }
}
