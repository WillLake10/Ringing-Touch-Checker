package com.williamlake.main;

import java.util.Scanner;

public class userInput {
    public static int getIntInput(){
        Scanner keyboard = new Scanner(System.in);
        return keyboard.nextInt();
    }

    public static String getStringInput(){
        Scanner keyboard = new Scanner(System.in);
        return keyboard.next();
    }

    public static String getStringInputMulti(){
        Scanner keyboard = new Scanner(System.in);
        return keyboard.nextLine();
    }
}
