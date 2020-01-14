package com.williamlake.main.data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class Method {
    private String method_proper;
    private String notation;
    private int no_of_bells;
    private String name;
    private String bob_notation;
    private String single_notation;


    public Method(String method_proper, String notation, int no_of_bells, String bob_notation, String single_notation) {
        this.method_proper = method_proper;
        this.notation = notation;
        this.no_of_bells = no_of_bells;
        setName(no_of_bells,method_proper);
        this.bob_notation = bob_notation;
        this.single_notation = single_notation;
    }

    public Method() {
        this.method_proper = "";
        this.notation = "";
        this.no_of_bells = 0;
        setName(no_of_bells,method_proper);
        this.bob_notation = "";
        this.single_notation = "";
    }

    public void setName(int no_of_bells, String methord_proper){
        String Name;
        Name = methord_proper + " " + getStage(no_of_bells);
        this.name = Name;
    }

    public static String getStage(int no_of_bells){
        switch (no_of_bells) {
            case 0:
                return "";
            case 4:
                return "Minimus";
            case 5:
                return "Doubles";
            case 6:
                return "Minor";
            case 7:
                return "Triples";
            case 8:
                return "Major";
            case 9:
                return "Caters";
            case 10:
                return "Royal";
            case 11:
                return "Cinques";
            case 12:
                return "Maximus";
        }
        return null;
    }

    public String getMethod_proper() {
        return method_proper;
    }

    public void setMethod_proper(String methord_proper) {
        this.method_proper = methord_proper;
    }

    public String getNotation() {
        return notation;
    }

    public void setNotation(String notation) {
        this.notation = notation;
    }

    public int getNo_of_bells() {
        return no_of_bells;
    }

    public void setNo_of_bells(int no_of_bells) {
        this.no_of_bells = no_of_bells;
    }

    public String getName() {
        return name;
    }

    public String getBob_notation() {
        return bob_notation;
    }

    public void setBob_notation(String bob_notation) {
        this.bob_notation = bob_notation;
    }

    public String getSingle_notation() {
        return single_notation;
    }

    public void setSingle_notation(String single_notation) {
        this.single_notation = single_notation;
    }

    @Override
    public String toString() {
        return "Methord{" +
                "methord_proper='" + method_proper + '\'' +
                ", notation='" + notation + '\'' +
                ", no_of_bells=" + no_of_bells +
                ", name='" + name + '\'' +
                ", bob_notation='" + bob_notation + '\'' +
                ", single_notation='" + single_notation + '\'' +
                '}';
    }

    //Method working functions

    public static Method getInstance(){
        Method method = new Method();
        if(addNewMethod() == true){
            method = Method.getNewMethod();
            System.out.println(method.toString());
            wantToSaveToFile(method);
        } else {
            method = null;
            while (method == null){
                method = Method.openMethodFromFile(findWantedStage());
            }
        }
        return method;
    }

    //save method
    private static void wantToSaveToFile(Method method){
        System.out.print("Want to save this method (Y/N): ");
        String answer = getStringInput();
        if(answer == "y" || answer == "Y"){
            saveMethodToFile(method);
        }
        saveMethodToFile(method);
    }
    private static void saveMethodToFile(Method method){
        try {
            FileWriter myWriter = new FileWriter("src/main/resources/methods/"+getStage(method.getNo_of_bells())+"/"+method.getName()+".txt");
            myWriter.write(method.getName()+"|");
            myWriter.write(method.getMethod_proper()+"|");
            myWriter.write(method.getNo_of_bells()+"|");
            myWriter.write(method.getNotation()+"|");
            myWriter.write(method.getBob_notation()+"|");
            myWriter.write(method.getSingle_notation());
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    //open saved method
    private static boolean addNewMethod(){
        System.out.println("Please select an option:");
        System.out.println("   1 - Add new method");
        System.out.println("   2 - Open method");
        int choice = getIntInput();
        if(choice == 1){
            return true;
        } else{
            return false;
        }
    }

    private static String findWantedStage(){
        System.out.print("How many bells in method you are looking for: ");
        return getStage(getIntInput());
    }

    private static Method openMethodFromFile(String location){
        Method method = new Method();
        File[] fileList = getResourceFolderFiles(location);
        try{
            String[] methodsList = new String[fileList.length];
            System.out.println(location + " Methods");
            for (int i = 0; i < fileList.length; i++) {
                methodsList[i] = fileList[i].getName().substring(0, fileList[i].getName().length() - 4);
                System.out.println(" - " + methodsList[i]);
            }
            //add open method
            return method;
        }catch (Exception e){
            System.out.println("No methods available please try again");
            return null;
        }

    }

    private static Method openFile(Method method){
        System.out.println("Which method do you want to open: ");
        String fileName = getStringInputMulti();

    }

    private static File[] getResourceFolderFiles (String folder) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL url = loader.getResource("methods/" + folder);
        try{
            String path = url.getPath();
            return new File(path).listFiles();
        }catch (Exception e){
            return null;
        }
    }

    //Creates new method from user input
    private static Method getNewMethod(){
        Method newMethod = new Method();
        String name = getMethordName();
        int numBells = numberOfBells();
        newMethod.setNo_of_bells(numBells);
        newMethod.setMethod_proper(name);
        newMethod.setName(numBells,name);
        newMethod.setNotation(getNotation("main method"));
        newMethod.setBob_notation(getNotation("bob"));
        newMethod.setSingle_notation(getNotation("single"));
        return newMethod;
    }

    private static String getNotation(String notationType){
        System.out.print("Enter " + notationType + " notation separated by a comma (e.g. x,16): ");
        return getStringInput();
    }

    private static String getMethordName(){
        System.out.print("Enter Method name without number of bells (e.g. Plain Bob): ");
        return getStringInputMulti();
    }

    private static int numberOfBells(){
        int numOfBells = 0;
        boolean valid = false;
        while (valid == false){
            System.out.print("How many bells in the method (Enter number between 4 and 12): ");
            numOfBells = getIntInput();
            if (numOfBellsValid((numOfBells))){
                valid = true;
            }
        }
        return numOfBells;
    }

    private static boolean numOfBellsValid(int numOfBells){
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

    private static String getStringInput(){
        Scanner keyboard = new Scanner(System.in);
        return keyboard.next();
    }

    private static String getStringInputMulti(){
        Scanner keyboard = new Scanner(System.in);
        return keyboard.nextLine();
    }


}
