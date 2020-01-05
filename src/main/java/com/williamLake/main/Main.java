package com.williamlake.main;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
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
        Scanner keyboard = new Scanner(System.in);
        return keyboard.nextInt();
    }
}
