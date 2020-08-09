package com.williamLake.main;

import com.williamLake.main.data.CccbrMethod;
import com.williamLake.main.data.Method;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import static com.williamLake.main.data.Method.findPlainCourseLength;

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
        Method returnMethod = new Method(method.getMethod_proper(), method.getNotation(), method.getNo_of_bells(), method.getBob_notation(), method.getSingle_notation(), method.getCall_point(), method.getHunt_bells());
        return returnMethod;
    }

    //save method
    private static void wantToSaveToFile(Method method) {
        System.out.print("Want to save this method (Y/N): ");
        String answer = userInput.getStringInput();
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
        int choice = userInput.getIntInput();
        if (choice == 1) {
            return true;
        } else {
            return false;
        }
    }

    private static String findWantedStage() {
        System.out.print("How many bells in method you are looking for: ");
        return Method.getStage(userInput.getIntInput());
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
            int fileNum = userInput.getIntInput();
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
        return userInput.getIntInput();
    }

    private static String getNotation(String notationType) {
        System.out.print("Enter " + notationType + " notation separated by a comma (e.g. x,16): ");
        return userInput.getStringInput();
    }

    private static String callPoint(Method method) {
        String[] not = method.getNotation().split(",");
        System.out.println("Select Line of call point or points:");
        for (int i = 1; i < not.length; i++) {
            System.out.println(i + " - " + not[i]);
        }
        return userInput.getStringInputMulti();
    }

    private static String getMethordName() {
        System.out.print("Enter Method name without number of bells (e.g. Plain Bob): ");
        return userInput.getStringInputMulti();
    }

    private static int numberOfBells() {
        int numOfBells = 0;
        boolean valid = false;
        while (!valid) {
            System.out.print("How many bells in the method (Enter number between 4 and 12): ");
            numOfBells = userInput.getIntInput();
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

    public static List<CccbrMethod> parseMethodxml()
    {
        try {
            //Initialize a list of methods
            List<CccbrMethod> methods = new ArrayList<CccbrMethod>();
            CccbrMethod method = null;

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("src/main/resources/CCCBR_methods.xml"));
            document.getDocumentElement().normalize();
            NodeList nList = document.getElementsByTagName("methodSet");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node node = nList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    String notes = (eElement.getElementsByTagName("notes").item(0).getTextContent());
                    String classification = (eElement.getElementsByTagName("classification").item(0).getTextContent());
                    int stage = (Integer.parseInt(eElement.getElementsByTagName("stage").item(0).getTextContent()));
                    int lengthOfLead = (Integer.parseInt(eElement.getElementsByTagName("lengthOfLead").item(0).getTextContent()));
                    int numberOfHunts = (Integer.parseInt(eElement.getElementsByTagName("stage").item(0).getTextContent()));

                    NodeList mList = document.getElementsByTagName("methodSet");
                    for (int temp2 = 0; temp2 < mList.getLength(); temp2++) {
                        Node mnode = mList.item(temp2);
                        if (mnode.getNodeType() == Node.ELEMENT_NODE) {
                            Element mElement = (Element) mnode;
                            method = new CccbrMethod();
                            try{method.setStage(stage);}catch (NullPointerException e){method.setStage(4);}
                            try{method.setClassification(classification);}catch (NullPointerException e){method.setClassification("");}
                            try{method.setLengthOfLead(lengthOfLead);}catch (NullPointerException e){method.setLengthOfLead(0);}
                            try{method.setNumberOfHunts(numberOfHunts);}catch (NullPointerException e){method.setNumberOfHunts(1);}

                            try{method.setId(mElement.getAttribute("id"));}catch (NullPointerException e){method.setId("");}
                            try{method.setTitle(mElement.getElementsByTagName("title").item(0).getTextContent());}catch (NullPointerException e){method.setTitle("");}
                            try{method.setName(mElement.getElementsByTagName("name").item(0).getTextContent());}catch (NullPointerException e){method.setName("");}
                            try{method.setNotation(mElement.getElementsByTagName("notation").item(0).getTextContent());}catch (NullPointerException e){method.setNotation("");}
                            try{method.setSymmetry(mElement.getElementsByTagName("symmetry").item(0).getTextContent());}catch (NullPointerException e){method.setSymmetry("");}
                            try{method.setLeadHead(mElement.getElementsByTagName("leadHead").item(0).getTextContent());}catch (NullPointerException e){method.setLeadHead("");}

                            methods.add(method);
                        }
                    }
                }
            }
            return methods;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}

