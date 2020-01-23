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
    public static final int CANVAS_HEIGHT = 100;
    public static final int LINE_HIGHT = 20;
    public static final int LINE_WIDTH = 40;
    public static final int HOME_X = 20;
    public static final int HOME_Y = 70;

    // Declare an instance of the drawing canvas,
    // which is an inner class called DrawCanvas extending javax.swing.JPanel.
    private DrawCanvas canvas;

    // Constructor to set up the GUI components and event handlers
    public draw(Method method, int option) {
        canvas = new DrawCanvas(method, option);    // Construct the drawing canvas
        if (option == 3){
            canvas.setPreferredSize(new Dimension(CANVAS_WIDTH+(LINE_WIDTH*method.getNo_of_bells()),
                    CANVAS_HEIGHT + (LINE_HIGHT*method.getNotation().split(",").length)));
        }else{
            canvas.setPreferredSize(new Dimension(CANVAS_WIDTH+(LINE_WIDTH*method.getNo_of_bells()),
                    CANVAS_HEIGHT + (LINE_HIGHT*method.getPlain_course_length())));
        }

        JScrollPane scrollPane = new JScrollPane();
        canvas.add(scrollPane);

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
        private int option;

        public DrawCanvas(Method method, int option) {
            this.method = method;
            this.option = option;
        }

        // Override paintComponent to perform your own painting
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);     // paint parent's background
            Graphics2D g2 = (Graphics2D) g;

            Font font = new Font("Serif", Font.BOLD, 15);
            int lineNumber = 0;
            int[] nextPosition = new int[12];
            int[] lastPos = nextPosition;
            String[] not = method.getNotation().split(",");
            String[] firstLine = initiliseFirstLine(method.getNo_of_bells());

            drawVerticalLines(g2);
            drawTitle(g2, font);

            String[] currentLine = getNextLine(firstLine, not[0]);
            for(int i = 0; i < 12; i++){
                lastPos[i] = i - 1;
            }

            nextPosition = drawLine(g2, currentLine, lastPos, lineNumber);
            drawNewLeadMarkers(g2,  1, lineNumber-1, font);
            outLine(currentLine);
            int temp = 1;
            int grid = 0;
            while(!checkRounds(currentLine, firstLine) && grid == 0){
                if(temp == 0){
                    drawNewLeadMarkers(g2, nextPosition[2], lineNumber, font);
                }

                for (int notNumber = temp; notNumber < not.length; notNumber++) {
                    currentLine = getNextLine(currentLine, not[notNumber]);
                    outLine(currentLine);
                    lineNumber += 1;

                    lastPos = nextPosition;
                    nextPosition = drawLine(g2, currentLine, lastPos, lineNumber);
                }
                temp = 0;

                if (option == 3){
                    grid = 1;
                }


            }
        }

        int[] drawLine(Graphics2D g, String[] currentLine, int[] lastPos, int lineNumber){
            int[] nextPos;
            Color color;
            color = Color.BLUE;
            nextPos = getNextLinePos(currentLine);
            for(int i = method.getNo_of_bells(); i > 0; i--){
                g.setStroke(new BasicStroke(2));
                if(i == 1){
                    color = Color.RED;
                }else if(i == 2){
                    g.setStroke(new BasicStroke(3));
                    color = Color.BLUE;
                }else if(i == 3){
                    final Color DARK_GREEN = new Color(0, 255, 0);
                    color = DARK_GREEN;
                }else if(i == 4){
                    color = Color.MAGENTA;
                }else if(i == 5){
                    final Color MUSTARD_YELLOW = new Color(144, 0, 255);
                    color = MUSTARD_YELLOW;
                }else if(i == 6){
                    final Color TURQUOISE = new Color(17,244, 241);
                    color = TURQUOISE;
                }else if(i == 7){
                    color = Color.gray;
                }else if(i == 8){
                    color = Color.BLACK;
                }else{
                    color = Color.LIGHT_GRAY;
                }
                if (option == 3) {
                    g.setStroke(new BasicStroke(3));
                    drawBellLine(g, lastPos[i], nextPos[i], color, lineNumber);
                } else{
                    if (i >= 1 && i <= 2){
                        drawBellLine(g, lastPos[i], nextPos[i], color, lineNumber);
                    }
                }

            }
            return nextPos;
        }

        int[] getNextLinePos(String[] currentLine){
            int[] nextPos = new int[12];
            for (int x = 0; x < method.getNo_of_bells(); x++) {
                if (currentLine[x].equals("1")) {
                    nextPos[1] = x;
                }else if (currentLine[x].equals("2")) {
                    nextPos[2] = x;
                }else if (currentLine[x].equals("3")) {
                    nextPos[3] = x;
                }else if (currentLine[x].equals("4")) {
                    nextPos[4] = x;
                }else if (currentLine[x].equals("5")) {
                    nextPos[5] = x;
                }else if (currentLine[x].equals("6")) {
                    nextPos[6] = x;
                }else if (currentLine[x].equals("7")) {
                    nextPos[7] = x;
                }else if (currentLine[x].equals("8")) {
                    nextPos[8] = x;
                }else if (currentLine[x].equals("9")) {
                    nextPos[9] = x;
                }else if (currentLine[x].equals("0")) {
                    nextPos[10] = x;
                }else if (currentLine[x].equals("E")) {
                    nextPos[11] = x;
                }else if (currentLine[x].equals("T")) {
                    nextPos[12] = x;
                }
            }
            return nextPos;
        }

        void drawNewLeadMarkers(Graphics2D g, int nextPos, int lineNumber, Font font){
            g.setColor(Color.gray);
            //g.drawLine(HOME_X, HOME_Y + ((lineNumber+1) * LINE_HIGHT), HOME_X + ((method.getNo_of_bells()-1) * LINE_WIDTH), HOME_Y + (lineNumber + 1) * LINE_HIGHT);

            g.setColor(Color.BLUE);
            g.fillOval(HOME_X + (nextPos * LINE_WIDTH) - 3, HOME_Y + ((lineNumber + 1) * LINE_HIGHT) - 3, 6,6);

            g.setColor(Color.black);
            g.setFont(font);
            g.drawString(Integer.toString(nextPos +1 ),
                    HOME_X + (method.getNo_of_bells() * LINE_WIDTH) - Math.round(LINE_WIDTH/2),
                    HOME_Y + ((lineNumber+1) * LINE_HIGHT) + Math.round(LINE_HIGHT/2));

        }

        void drawBellLine(Graphics2D g, int lastPos, int nextPos, Color color, int lineNumber){
            g.setColor(color);
            g.drawLine(HOME_X + (lastPos * LINE_WIDTH), HOME_Y + ((lineNumber) * LINE_HIGHT), HOME_X + (nextPos * LINE_WIDTH), HOME_Y + ((lineNumber + 1) * LINE_HIGHT));

        }

        void drawTitle(Graphics2D g, Font font){

            g.setColor(Color.black);

            g.setFont(font);
            g.drawString(method.getName().split(" ")[0], HOME_X, HOME_Y - 50);
            g.drawString(method.getName().split(" ")[1], HOME_X, HOME_Y - 35);
            try{
                g.drawString(method.getName().split(" ")[2], HOME_X, HOME_Y - 20);
            }catch (Exception e){}
        }

        void drawVerticalLines(Graphics2D g){
            g.setColor(Color.lightGray);
            for(int i = 0; i < method.getNo_of_bells(); i++){
                if(option == 3){
                    g.drawLine(HOME_X + (LINE_WIDTH*i), HOME_Y, HOME_X + (LINE_WIDTH*i), HOME_Y + (LINE_HIGHT*method.getNotation().split(",").length));
                } else{
                    g.drawLine(HOME_X + (LINE_WIDTH*i), HOME_Y, HOME_X + (LINE_WIDTH*i), HOME_Y + (LINE_HIGHT*method.getPlain_course_length()));
                }
            }
        }

        Boolean checkRounds(String[] currentLine, String[] firstLine){
            return Arrays.equals(currentLine, firstLine);
        }

        String[] getNextLine(String[] currentline, String notation) {
            String[] nextLine;
            if ("x".equals(notation) || "X".equals(notation)) {
                nextLine = getLineIfAllChange(currentline);
            } else {
                nextLine = getLineIfNotAllChange(currentline, notation);
            }
            return nextLine;
        }

        String[] getLineIfAllChange(String[] currentline) {
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

        String[] getLineIfNotAllChange(String[] currentline, String notation) {
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

        String[] initiliseFirstLine(int numBells) {
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

        void outLine(String[] line){
            for(String bell : line){
                System.out.print(bell);
            }
            System.out.println();
        }
    }

    // The entry main method
    public static void main(Method method, int option) {
        // Run the GUI codes on the Event-Dispatching thread for thread safety
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new draw(method, option);
            }
        });
    }
}