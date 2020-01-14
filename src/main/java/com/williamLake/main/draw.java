package com.williamlake.main;

import com.williamlake.main.data.Method;

import java.awt.*;       // Using AWT's Graphics and Color
import java.util.Arrays;
import javax.swing.*;    // Using Swing's components and containers

/** Custom Drawing Code Template */
// A Swing application extends javax.swing.JFrame
public class draw extends JFrame {
    // Define constants
    public static final int CANVAS_WIDTH  = 20;
    public static final int CANVAS_HEIGHT = 1000;
    public static final int LINE_HIGHT = 10;
    public static final int LINE_WIDTH = 20;
    public static final int HOME_X = 20;
    public static final int HOME_Y = 20;

    // Declare an instance of the drawing canvas,
    // which is an inner class called DrawCanvas extending javax.swing.JPanel.
    private DrawCanvas canvas;

    // Constructor to set up the GUI components and event handlers
    public draw(Method method) {
        canvas = new DrawCanvas(method);    // Construct the drawing canvas
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH+(LINE_WIDTH*method.getNo_of_bells()), CANVAS_HEIGHT));

        // Set the Drawing JPanel as the JFrame's content-pane
        Container cp = getContentPane();
        cp.add(canvas);
        // or "setContentPane(canvas);"

        setDefaultCloseOperation(EXIT_ON_CLOSE);   // Handle the CLOSE button
        pack();              // Either pack() the components; or setSize()
        setTitle("Blue Line");  // "super" JFrame sets the title
        setVisible(true);    // "super" JFrame show
    }

    /**
     * Define inner class DrawCanvas, which is a JPanel used for custom drawing.
     */
    private class DrawCanvas extends JPanel {
        private Method method;

        public DrawCanvas(Method method) {
            this.method = method;
        }

        // Override paintComponent to perform your own painting
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);     // paint parent's background
            g.setColor(Color.lightGray);
            for(int i = 0; i < method.getNo_of_bells(); i++){
                g.drawLine(HOME_X + (LINE_WIDTH*i), HOME_Y, HOME_X + (LINE_WIDTH*i), HOME_Y + (LINE_HIGHT*100));
            }

            String[] not = method.getNotation().split(",");
            String[] firstLine = initiliseFirstLine(method.getNo_of_bells());
            int lineNumber = 0;
            int nextPos = 1;
            int treblePos = 0;
            String placeBell = "2";
            String[] currentLine = getNextLine(firstLine, not[0]);
            for(int x = 0; x < method.getNo_of_bells() - 1; x++){
                if (currentLine[x].equals(placeBell)){
                    nextPos = x;
                }
            }
            g.setColor(Color.BLUE);
            g.drawLine(HOME_X + ((Integer.parseInt(placeBell) - 1)*LINE_WIDTH), HOME_Y+((lineNumber)*LINE_HIGHT), HOME_X+(nextPos*LINE_WIDTH), HOME_Y+((lineNumber+1)*LINE_HIGHT));

            for(int x = 0; x < method.getNo_of_bells() - 1; x++){
                if (currentLine[x].equals("1")){
                    treblePos = x;
                }
            }

            g.setColor(Color.RED);
            g.drawLine(HOME_X, HOME_Y+((lineNumber)*LINE_HIGHT), HOME_X+(treblePos*LINE_WIDTH), HOME_Y+((lineNumber+1)*LINE_HIGHT));
 }

        public Boolean checkRounds(String[] currentLine, String[] firstLine){
            if (Arrays.equals(currentLine, firstLine)){
                return true;
            } else {
                return false;
            }
        }

        public String[] getNextLine(String[] currentline, String notation) {
            String[] nextLine;
            if ("x".equals(notation) || "X".equals(notation)) {
                nextLine = getLineIfAllChange(currentline);
            } else {
                nextLine = getLineIfNotAllChange(currentline, notation);
            }
            return nextLine;
        }

        public String[] getLineIfAllChange(String[] currentline) {
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

        public String[] getLineIfNotAllChange(String[] currentline, String notation) {
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

        public String[] initiliseFirstLine(int numBells) {
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
    }

    // The entry main method
    public static void main(Method method) {
        // Run the GUI codes on the Event-Dispatching thread for thread safety
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new draw(method); // Let the constructor do the job
            }
        });
    }
}