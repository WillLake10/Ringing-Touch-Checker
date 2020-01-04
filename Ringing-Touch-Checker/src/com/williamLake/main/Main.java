package com.williamLake.main;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
    }

    public static int NumberOfBells(){
        int numOfBells = 0;
        boolean valid = false;
        while (valid == false){
            System.out.print("How many bells in the methord (Enter number between 4 and 12): ");
            numOfBells = GetIntInput();
            if (CheckNumOfBellsValid((numOfBells))){
                valid = true;
            }
        }
        return numOfBells;
    }

    public static boolean CheckNumOfBellsValid(int numOfBells){
        if (numOfBells >= 4 && numOfBells <= 12){
            return true;
        } else{
            System.out.println("Invalid input");
            return false;
        }
    }

    private static int GetIntInput(){
        Scanner keyboard = new Scanner(System.in);
        return keyboard.nextInt();
    }
}
