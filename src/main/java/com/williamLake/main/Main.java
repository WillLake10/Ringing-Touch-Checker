package com.williamlake.main;


import com.williamlake.main.data.Method;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Method methord = new Method();
        methord = getNewMethod();
        System.out.println(methord.toString());
    }

    public static Method getNewMethod(){
        Method newMethod = new Method();
        String name = getMethordName();
        int numBells = numberOfBells();
        newMethod.setNo_of_bells(numBells);
        newMethod.setMethord_proper(name);
        newMethod.setName(numBells,name);
        newMethod.setNotation(getNotation("main methord"));
        newMethod.setBob_notation(getNotation("bob"));
        newMethod.setSingle_notation(getNotation("single"));
        return newMethod;
    }

    public static String getNotation(String notationType){
        System.out.print("Enter " + notationType + " notation separated by a comma (e.g. x,16): ");
        return getStringInput();
    }

    public static String getMethordName(){
        System.out.print("Enter Methord name without number of bells (e.g. Plain Bob): ");
        return getStringInputMulti();
    }

    public static int numberOfBells(){
        int numOfBells = 0;
        boolean valid = false;
        while (valid == false){
            System.out.print("How many bells in the methord (Enter number between 4 and 12): ");
            numOfBells = getIntInput();
            if (numOfBellsValid((numOfBells))){
                valid = true;
            }
        }
        return numOfBells;
    }

    public static boolean numOfBellsValid(int numOfBells){
        if (numOfBells >= 4 && numOfBells <= 12){
            return true;
        } else{
            System.out.println("Invalid input");
            return false;
        }
    }

    private static int getIntInput(){
        Scanner keyboardint = new Scanner(System.in);
        return keyboardint.nextInt();
    }

    private static String getStringInput(){
        Scanner keyboard = new Scanner(System.in);
        return keyboard.next();
    }

    private static String getStringInputMulti(){
        Scanner keyboard = new Scanner(System.in);
        return keyboard.nextLine();
    }
}
