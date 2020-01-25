package com.williamlake.main.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

public class Method {
    private String method_proper;
    private String notation;
    private int no_of_bells;
    private String name;
    private String bob_notation;
    private String single_notation;
    private int plain_course_length;
    private String call_point;
    private int hunt_bells;

    public int getPlain_course_length() {
        return plain_course_length;
    }

    public void setPlain_course_length(int plain_course_length) {
        this.plain_course_length = plain_course_length;
    }

    public Method(String method_proper, String notation, int no_of_bells, String bob_notation, String single_notation, String call_point, int hunt_bells) {
        this.method_proper = method_proper;
        this.notation = notation;
        this.no_of_bells = no_of_bells;
        setName(no_of_bells,method_proper);
        this.bob_notation = bob_notation;
        this.single_notation = single_notation;
        this.plain_course_length = findPlainCourseLength(notation,no_of_bells);
        this.call_point = call_point;
        this.hunt_bells = hunt_bells;
    }


    public Method() {
        this.method_proper = "";
        this.notation = "";
        this.no_of_bells = 0;
        setName(no_of_bells,method_proper);
        this.bob_notation = "";
        this.single_notation = "";
        this.plain_course_length = 0;
        this.call_point = "";
        this.hunt_bells = 1;
    }

    public int getHunt_bells() {
        return hunt_bells;
    }

    public void setHunt_bells(int hunt_bells) {
        this.hunt_bells = hunt_bells;
    }

    public String getCall_point() {
        return call_point;
    }

    public void setCall_point(String call_point) {
        this.call_point = call_point;
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
        return "Method{" +
                "method_proper='" + method_proper + '\'' +
                ", notation='" + notation + '\'' +
                ", no_of_bells=" + no_of_bells +
                ", name='" + name + '\'' +
                ", bob_notation='" + bob_notation + '\'' +
                ", single_notation='" + single_notation + '\'' +
                ", plain_course_length=" + plain_course_length +
                ", call_point='" + call_point + '\'' +
                ", hunt_bells=" + hunt_bells +
                '}';
    }

    private static int findPlainCourseLength(String notation, int No_of_bells){
        int length = 0;

        String[] not = notation.split(",");
        String[] firstLine = initiliseFirstLine(No_of_bells);
        String[] currentLine = firstLine;

        Boolean firstTime = true;
        while(!checkRounds(currentLine, firstLine) || firstTime){
            firstTime = false;
            for (int notNumber = 0; notNumber < not.length; notNumber++) {

                currentLine = getNextLine(currentLine, not[notNumber]);

                length += 1;
            }
        }
        return length;
    }

    static Boolean checkRounds(String[] currentLine, String[] firstLine){
        return Arrays.equals(currentLine, firstLine);
    }

    public static String[] getNextLine(String[] currentline, String notation) {
        String[] nextLine;
        if ("x".equals(notation) || "X".equals(notation)) {
            nextLine = getLineIfAllChange(currentline);
        } else {
            nextLine = getLineIfNotAllChange(currentline, notation);
        }
        return nextLine;
    }

    static String[] getLineIfAllChange(String[] currentline) {
        String[] nextLine = new String[currentline.length];
        int size = currentline.length;
        for (int i = 0; i < size; i++) {
            if (i % 2 == 0) {
                nextLine[i] = currentline[i + 1];
            } else {
                nextLine[i] = currentline[i - 1];
            }
        }
        return nextLine;
    }

    static String[] getLineIfNotAllChange(String[] currentline, String notation) {
        String[] nextLine = new String[currentline.length];
        boolean[] done = new boolean[currentline.length];
        int temp;
        String[] parts = new String[notation.length()];
        for (int i = 0; i < notation.length(); i++){
            parts[i] = Character.toString(notation.charAt(i));
        }

        for (int i = 0; i < parts.length; i++) {
            if ("1".equals(parts[i]) || "2".equals(parts[i]) || "3".equals(parts[i]) ||
                    "4".equals(parts[i]) || "5".equals(parts[i]) ||
                    "6".equals(parts[i]) || "7".equals(parts[i]) ||
                    "8".equals(parts[i]) || "9".equals(parts[i])) {
                temp = Integer.parseInt(parts[i]) - 1;
            } else if ("0".equals(parts[i])) {
                temp = 9;
            } else if ("E".equals(parts[i])) {
                temp = 10;
            } else {
                temp = 11;
            }
            nextLine[temp] = currentline[temp];
            done[temp] = true;
        }

        for (int j = 0; j < done.length; j++) {
            if (done[j] == false) {
                nextLine[j] = currentline[j + 1];
                nextLine[j + 1] = currentline[j];
                done[j] = true;
                done[j + 1] = true;
            }
        }

        return nextLine;
    }

    static String[] initiliseFirstLine(int numBells) {
        String[] firstLine = new String[numBells];
        String position;
        for (int i = 1; i <= numBells; i++) {
            if (i == 10) {
                position = "0";
            } else if (i == 11) {
                position = "E";
            } else if (i == 12) {
                position = "T";
            } else {
                position = Integer.toString(i);
            }
            firstLine[i - 1] = position;
        }
        return firstLine;
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
            saveMethodToFile(method);
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
            FileWriter myWriter = new FileWriter("src/main/resources/methods/"+getStage(method.getNo_of_bells())
                    +"/"+method.getMethod_proper()+".txt");
            /*
            myWriter.write(method.getName()+"-");
            myWriter.write(method.getMethod_proper()+"-");
            myWriter.write(method.getNo_of_bells()+"-");
            myWriter.write(method.getNotation()+"-");
            myWriter.write(method.getBob_notation()+"-");
            myWriter.write(method.getSingle_notation()+"-");
            myWriter.write(method.getPlain_course_length()+"-");
            myWriter.write(method.getCall_point()+"-");
            System.out.println("Writing hunt bells as: "+method.getHunt_bells());
            myWriter.write(method.getHunt_bells());*/
            myWriter.write(method.getName()+"\r\n");
            myWriter.write(method.getMethod_proper()+"\r\n");
            myWriter.write(method.getNo_of_bells()+"\r\n");
            myWriter.write(method.getNotation()+"\r\n");
            myWriter.write(method.getBob_notation()+"\r\n");
            myWriter.write(method.getSingle_notation()+"\r\n");
            myWriter.write(method.getPlain_course_length()+"\r\n");
            myWriter.write(method.getCall_point()+"\r\n");
            System.out.println("Writing hunt bells as: "+method.getHunt_bells());
            myWriter.write(method.getHunt_bells()+"\r\n");
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
        File[] fileList = getResourceFolderFiles(location);
        try{
            String[] methodsList = new String[fileList.length];
            System.out.println(location + " Methods");
            for (int i = 0; i < fileList.length; i++) {
                methodsList[i] = fileList[i].getName().substring(0, fileList[i].getName().length() - 4);
                System.out.println((i+1) + " - " + methodsList[i]);
            }
            System.out.print("Which method do you want to open: ");
            int fileNum = getIntInput();
            //fileNum = 4;
            return openFile(location, methodsList[fileNum-1]);
        }catch (Exception e){
            System.out.println("No methods available please try again");
            return null;
        }

    }

    private static Method openFile(String location, String fileName){

        try {
            File myObj = new File("src/main/resources/methods/"+location+"/"+fileName+".txt");
            Scanner myReader = new Scanner(myObj);
            String data[] = new String[9];
            for (int i = 0; i < 9; i++){
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
        newMethod.setCall_point(callPoint(newMethod));
        newMethod.setPlain_course_length(findPlainCourseLength(newMethod.getNotation(),newMethod.getNo_of_bells()));
        newMethod.setHunt_bells(getNumOfHuntBells());
        return newMethod;
    }

    private static int getNumOfHuntBells() {
        System.out.print("Enter number of hunt bells in method: ");
        return getIntInput();
    }

    private static String getNotation(String notationType){
        System.out.print("Enter " + notationType + " notation separated by a comma (e.g. x,16): ");
        return getStringInput();
    }

    private static String callPoint(Method method){
        String[] not = method.getNotation().split(",");
        System.out.println("Select line of call point or points:");
        for(int i = 1; i < not.length; i++){
            System.out.println(i + " - " + not[i]);
        }
        return getStringInputMulti();
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
