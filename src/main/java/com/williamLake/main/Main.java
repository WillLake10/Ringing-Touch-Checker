package com.williamlake.main;

import com.williamlake.main.data.Method;

import java.awt.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Method workingMethod = Method.getInstance();
        System.out.println(workingMethod.toString());
        //Method workingMethod = new Method("Plain Bob", "x,16,x,16,x,16,x,16,x,16,x,12", 6, "x,14,x", "x,1234,x");
        drawBlueLine(workingMethod);
    }

    public static void drawBlueLine(Method method){
        System.out.println("Chose an option: ");
        System.out.println("  1 - Draw just blue line");
        System.out.println("  2 - Draw blue line with others gray");
        System.out.println("  3 - Draw grid");
        System.out.println("  4 - Draw Bob");
        System.out.println("  5 - Draw Single");
        System.out.println("  6 - Draw All");
        System.out.println("  9 - End");
        int answer = getStringInput();
        if (answer == 6){
            draw.main(method, 1, 10);
            draw.main(method, 4, 500);
            draw.main(method, 5, 1000);
        } else {
            draw.main(method, answer, 10);
        }


    }

    private static int getStringInput(){
        Scanner keyboard = new Scanner(System.in);
        return keyboard.nextInt();
    }
}
