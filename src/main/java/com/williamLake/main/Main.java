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
        System.out.println("Want to draw blue line (1-yes, 0-no): ");
        int answer = getStringInput();
        if(answer == 1){
            draw.main(method);
        }
    }

    private static int getStringInput(){
        Scanner keyboard = new Scanner(System.in);
        return keyboard.nextInt();
    }
}
