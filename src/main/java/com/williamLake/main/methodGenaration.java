package com.williamlake.main;

import com.williamlake.main.data.Method;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import static com.williamlake.main.data.Method.findPlainCourseLength;
import static com.williamlake.main.userInput.*;

public class methodGenaration {
    public static Method getMethodInstanceFromUser() {
        Method method = new Method();
        if (addNewMethod() == true) {
            method = getNewMethod();
            System.out.println(method.toString());
            wantToSaveToFile(method);
        } else {
            method = null;
            while (method == null) {
                method = openMethodFromFile(findWantedStage());
            }
            saveMethodToFile(method);
        }
        return method;
    }

    //save method
    private static void wantToSaveToFile(Method method) {
        System.out.print("Want to save this method (Y/N): ");
        String answer = getStringInput();
        if (answer == "y" || answer == "Y") {
            saveMethodToFile(method);
        }
        saveMethodToFile(method);
    }

    private static void saveMethodToFile(Method method) {
        try {
            FileWriter myWriter = new FileWriter("src/main/resources/methods/" + method.getStage(method.getNo_of_bells())
                    + "/" + method.getMethod_proper() + ".txt");
            myWriter.write(method.getName() + "\r\n");
            myWriter.write(method.getMethod_proper() + "\r\n");
            myWriter.write(method.getNo_of_bells() + "\r\n");
            myWriter.write(method.getNotation() + "\r\n");
            myWriter.write(method.getBob_notation() + "\r\n");
            myWriter.write(method.getSingle_notation() + "\r\n");
            myWriter.write(method.getPlain_course_length() + "\r\n");
            myWriter.write(method.getCall_point() + "\r\n");
            System.out.println("Writing hunt bells as: " + method.getHunt_bells());
            myWriter.write(method.getHunt_bells() + "\r\n");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    //open saved method
    private static boolean addNewMethod() {
        System.out.println("Please select an option:");
        System.out.println("   1 - Add new method");
        System.out.println("   2 - Open method");
        int choice = getIntInput();
        if (choice == 1) {
            return true;
        } else {
            return false;
        }
    }

    private static String findWantedStage() {
        System.out.print("How many bells in method you are looking for: ");
        return Method.getStage(getIntInput());
    }

    private static Method openMethodFromFile(String location) {
        File[] fileList = getResourceFolderFiles(location);
        try {
            String[] methodsList = new String[fileList.length];
            System.out.println(location + " Methods");
            for (int i = 0; i < fileList.length; i++) {
                methodsList[i] = fileList[i].getName().substring(0, fileList[i].getName().length() - 4);
                System.out.println((i + 1) + " - " + methodsList[i]);
            }
            System.out.print("Which method do you want to open: ");
            int fileNum = getIntInput();
            //fileNum = 4;
            return openFile(location, methodsList[fileNum - 1]);
        } catch (Exception e) {
            System.out.println("No methods available please try again");
            return null;
        }

    }

    private static Method openFile(String location, String fileName) {

        try {
            File myObj = new File("src/main/resources/methods/" + location + "/" + fileName + ".txt");
            Scanner myReader = new Scanner(myObj);
            String data[] = new String[9];
            for (int i = 0; i < 9; i++) {
                data[i] = myReader.nextLine();
            }

            Method method = new Method(data[1], data[3], Integer.parseInt(data[2]), data[4], data[5], data[7], Integer.parseInt(data[8]));
            myReader.close();
            return method;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
        return null;
    }

    private static File[] getResourceFolderFiles(String folder) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL url = loader.getResource("methods/" + folder);
        try {
            String path = url.getPath();
            return new File(path).listFiles();
        } catch (Exception e) {
            return null;
        }
    }

    //Creates new method from user input
    private static Method getNewMethod() {
        Method newMethod = new Method();
        String name = getMethordName();
        int numBells = numberOfBells();
        newMethod.setNo_of_bells(numBells);
        newMethod.setMethod_proper(name);
        newMethod.setName(numBells, name);
        newMethod.setNotation(getNotation("main method"));
        newMethod.setBob_notation(getNotation("bob"));
        newMethod.setSingle_notation(getNotation("single"));
        newMethod.setCall_point(callPoint(newMethod));
        newMethod.setPlain_course_length(findPlainCourseLength(newMethod.getNotation(), newMethod.getNo_of_bells()));
        newMethod.setHunt_bells(getNumOfHuntBells());
        return newMethod;
    }

    private static int getNumOfHuntBells() {
        System.out.print("Enter number of hunt bells in method: ");
        return getIntInput();
    }

    private static String getNotation(String notationType) {
        System.out.print("Enter " + notationType + " notation separated by a comma (e.g. x,16): ");
        return getStringInput();
    }

    private static String callPoint(Method method) {
        String[] not = method.getNotation().split(",");
        System.out.println("Select Line of call point or points:");
        for (int i = 1; i < not.length; i++) {
            System.out.println(i + " - " + not[i]);
        }
        return getStringInputMulti();
    }

    private static String getMethordName() {
        System.out.print("Enter Method name without number of bells (e.g. Plain Bob): ");
        return getStringInputMulti();
    }

    private static int numberOfBells() {
        int numOfBells = 0;
        boolean valid = false;
        while (valid == false) {
            System.out.print("How many bells in the method (Enter number between 4 and 12): ");
            numOfBells = getIntInput();
            if (numOfBellsValid((numOfBells))) {
                valid = true;
            }
        }
        return numOfBells;
    }

    private static boolean numOfBellsValid(int numOfBells) {
        if (numOfBells >= 4 && numOfBells <= 12) {
            return true;
        } else {
            System.out.println("Invalid input");
            return false;
        }
    }


}
