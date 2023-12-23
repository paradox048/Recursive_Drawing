/**
 * @name Derek Duong
 * This program uses recursion to draw out three shapes:
 * - A circle that prints a circle within that circle that's half the radius. This continues until 10 circles have been drawn
 * - A Ruler that has has ticks to mark the length. The largest length is in the middle and splits to half of its length left and right
 * the ticks represents 1/2, 1/4... etc of the length. This continues until the ticks lengths are less than 50 pixels in length
 *- A Flower which is drawn recursively with 5 branches branching out every branch.Every branch has 5
 * sub branches and is half the length of its parent branch.
 * Each one of these recursive images are centered within the canvas.
 */

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

class A5 extends  JFrame implements ActionListener{

    // Name-constants for the various dimensions
    public static final int CANVAS_WIDTH = 900;
    public static final int CANVAS_HEIGHT = 900;
    public static final Color CANVAS_BACKGROUND = Color.CYAN;

    private DrawCanvas canvas; // the custom drawing canvas (extends JPanel)

    // the buttons
    JButton btnQ1, btnQ2, btnQ3, btnExit;

    int progToRun;

    // timer to update the screen
    Timer timer;
    public A5() {

        // update the screen every 50 milliseconds
        timer =  new Timer(50, this);
        progToRun = -1;

        // Set up a panel for the buttons
        JPanel btnPanel = new JPanel(null);
        btnPanel.setBackground(Color.WHITE);

        btnPanel.setPreferredSize(new Dimension(160, CANVAS_HEIGHT));

        btnQ1 = new JButton("Circle");
        btnPanel.add(btnQ1);
        btnQ1.addActionListener(this);
        btnQ1.setBounds(5, 300, 150, 30);


        btnQ2 = new JButton("Ruler");
        btnPanel.add(btnQ2);
        btnQ2.addActionListener(this);
        btnQ2.setBounds(5, 350, 150, 30);

        btnQ3 = new JButton("Flower");
        btnPanel.add(btnQ3);
        btnQ3.addActionListener(this);
        btnQ3.setBounds(5, 400, 150, 30);

        btnExit = new JButton("Exit");
        btnPanel.add(btnExit);
        btnExit.addActionListener(this);
        btnExit.setBounds(5, 450, 150, 30);

        // Set up a custom drawing JPanel
        canvas = new DrawCanvas();
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

        // Add both panels to this JFrame
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(canvas, BorderLayout.CENTER);
        cp.add(btnPanel, BorderLayout.EAST);

        // "this" JFrame fires KeyEvent


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Handle the CLOSE button
        setTitle("Assignment 6");
        pack();           // pack all the components in the JFrame
        setVisible(true); // show it
        requestFocus();

    }

    public void actionPerformed(ActionEvent e)
    {
        // check if restart button was pressed
        if (e.getSource()== btnQ1)
        {
            progToRun = 0;
        }
        // check if restart button was pressed
        else if (e.getSource()== btnQ2)
        {
            progToRun = 1;
        }
        else if (e.getSource()== btnQ3)
        {
            progToRun = 2;
        }

        // check if exit button was pressed
        else if (e.getSource()==btnExit)
        {
            System.exit(0);
        }
        else if (e.getSource()== timer){
            canvas.repaint();
        }
    }

    /**
     * DrawCanvas (inner class) is a JPanel used for custom drawing
     */
    class DrawCanvas extends JPanel {

        int fHeight= CANVAS_HEIGHT ;
        int fWidth = CANVAS_WIDTH ;


        ////////////////////// ONLY MODIFY THE BODY OF THESE 3 METHODS BELOW  /////////////////////
        // Q1

        /**
         * Draws a circle within another circle recursively until 10 circles are drawn
         * @param g         the graphics variable that allows us to draw on the canvas
         * @param radius    the radius of the circle
         * @param num       the counter variable to count the amount of circles drawn
         */
        public void drawCircle(Graphics g, double radius, int num){
            //prints out the current circle number
            System.out.println(num);
            //draws the circle
            g.drawOval(fWidth/2 - (int)radius, fHeight/2 - (int)radius,(int)radius * 2, (int)radius * 2);

            //base case
            //checks if the current num is less than 10
            if (num < 10) {
                //calls drawCircle with modified radius and incremented num
                drawCircle(g, radius / 2d, num + 1);
            }
        }

        // Q2

        /**
         * This method draws a ruler recursively with the tallest tick being in the middle and halves its ticks size going left and
         * right. The ticks on the left/right represents 1/2,1/4, 1/8... etch of a value
         * this continues until the tick height is less than 50 pixels
         * @param g         the graphics variable that allows us to draw on the canvas
         * @param x         the x co-ordinate
         * @param y         the y co-ordinate
         * @param w         the width of the ticks
         * @param height    the height of the ticks
         */
        public void drawRuler(Graphics g, double x, double y, double w, double height){
            //draws base line
            g.drawLine((int)x, (int)(y + height), (int)(x + w), (int)(y + height));

            //base case
            //checks if the height is more than 50 pixels
            if (height > 50) {
                //draws middle line
                g.drawLine((int)(x + w/2), (int)y, (int)(x + w/2), (int)(y + height));
                //draws right side ticks
                drawRuler(g, x + w / 2,y + height/2, w/2, height/2);
                //draws left side ticks
                drawRuler(g, x ,y + height/2, w/2, height/2);

            } else {
                //if the height is less than 50 pixels return
                return;
               }
        }

        // Q3

        /**
         * This method draws a flower recursively that has 5 branches branching out.
         * Every branch has 5 sub-branches that are half the length of its parent branch
         * this continues until the level is 1 and which it stops running the program
         * @param g         the graphics parameter allows us to draw on the canvas
         * @param x1        the x co-ordinate
         * @param y1        the y co-ordinate
         * @param length    the length of the line
         * @param angleRad  90 degrees
         * @param level     the current level
         */
        public void drawFlower(Graphics g, double x1, double y1, int length, double angleRad, int level){
            //creates two points that correspond to the angle that's passed converting
            //the angle into a cos and sin value which will allow us to draw a diagonal lines at certain angles
            double x2 = Math.cos(angleRad) * length + x1;
            double y2 = Math.sin(angleRad) * length + y1;

            //draws the beginning line
            g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);

            //base case
            //checks if the level is 1
            if (level <= 1) {
                return;
            } else {
                //calls draw flower with half the length,60 degree angle, and decremented level by 1
                drawFlower(g, x2, y2, length/2,angleRad-Math.PI/6, level - 1 );

                //calls draw flower with half the length,30 degree angle, and decremented level by 1
                drawFlower(g, x2, y2, length/2,angleRad-Math.PI/3, level - 1 );

                //calls draw flower with half the length,90 degree angle, and decremented level by 1
                drawFlower(g, x2, y2, length/2,angleRad, level - 1 );

                //calls draw flower with half the length,150 degree angle, and decremented level by 1
                drawFlower(g, x2, y2, length/2,angleRad+Math.PI/3, level - 1 );

                //calls draw flower with half the length,120 degree angle, and decremented level by 1
                drawFlower(g, x2, y2, length/2,angleRad+Math.PI/6, level - 1 );

            }

        }

        public void paintComponent(Graphics g) {
            // erase the screen
            super.paintComponent(g);

            setBackground(CANVAS_BACKGROUND);

            // draw the desired program
            if (progToRun == 0){
                // Set initial radius of circle
                int radius = CANVAS_WIDTH/2;
                int num = 1;
                drawCircle(g, radius, num);
            }

            else if (progToRun == 1){
                // draw ruler
                double h = CANVAS_HEIGHT/2;
                double w = CANVAS_WIDTH;
                double x = 0;
                double y = 0;
                drawRuler( g,  x,  y,  w,  h);
            }
            else if (progToRun == 2){
                // draw flower
                // Set initial startig location of one end of the branch (bottom middle)
                // indicate 5 levels of branches
                int levels = 5;
                int x1 = fWidth/2;
                int y1 = fHeight;
                int length = CANVAS_WIDTH/2;
                int y2 =  CANVAS_HEIGHT/2;
                double angleRad = -Math.PI/2;

                drawFlower(g, x1, y1, length, angleRad, levels);
            }
        }
    }


    public static void main(String[] args)
    {
        A5 prog = new A5();
        prog.timer.start();
    }


}
