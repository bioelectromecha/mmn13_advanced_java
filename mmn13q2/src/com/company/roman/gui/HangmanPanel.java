package com.company.roman.gui;

import com.company.roman.Controller;

import javax.swing.*;
import java.awt.*;

/**
 * This class displays the hanging man drawing
 */
public class HangmanPanel extends JPanel{

    //this is the object which communicates between the com.company.roman.gui and the com.company.roman.data
    Controller mController;

    //some constants for the programmatic drawing
    private final int mWidthOfBase = 200;
    private final int mHeightOfBase = 40;
    private final int mWidthOfPole = 40;
    //number of bad guesses
    private int mNumOfBadGuesses;

    /**
     * main and only constructor
     */
    public HangmanPanel(){
        mNumOfBadGuesses=0;
        this.setVisible(true);
    }

    /**
     * return a different preffered size to show drawing correctly
     * @return new dimension object
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(300,400);
    }

    /**
     * repaint with the new number of wrong guesses
     * @param numOfBadGuess
     */
    public void updateView(int numOfBadGuess){
        mNumOfBadGuesses=numOfBadGuess;
        repaint();
    }

    /**
     * draw the hanging man
     * @param g Graphics object
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.MAGENTA);
        //draw body parts according to the num of guesses
        for (int i = 1; i <=mNumOfBadGuesses ; i++) {
            switch (i) {
                case 10:
                    drawNoose(g, Color.MAGENTA);
                    break;
                case 9:
                    drawRightLeg(g, Color.MAGENTA);
                    break;
                case 8:
                    drawLeftLeg(g, Color.MAGENTA);
                    break;
                case 7:
                    drawRightArm(g, Color.MAGENTA);
                    break;
                case 6:
                    drawLeftArm(g, Color.MAGENTA);
                    break;
                case 5:
                    drawBody(g, Color.MAGENTA);
                    break;
                case 4:
                    drawHead(g, Color.MAGENTA);
                    break;
                case 3:
                    drawHeader(g, Color.MAGENTA);
                    break;
                case 2:
                    drawPole(g, Color.MAGENTA);
                    break;
                case 1:
                    drawBase(g, Color.MAGENTA);
                    break;
            }
        }

    }

    /**
     * draw the noose
     * @param g
     * @param color
     */
    private void drawNoose(Graphics g, Color color) {
        g.setColor(color);
        Graphics2D g2d = (Graphics2D) g;
        BasicStroke stroke = new BasicStroke(8.0f);
        g2d.setStroke(stroke);
        int x = getWidth() / 2;
        int y = mHeightOfBase;
        g2d.drawLine(x, y, x, y + 40);
        stroke = new BasicStroke(20.0f);
        g2d.setStroke(stroke);
        g2d.drawLine(x, y + 120, x, y + 128);
    }
    /**
     * draw the right leg
     * @param g
     * @param color
     */
    private void drawRightLeg(Graphics g, Color color) {
        g.setColor(color);
        Graphics2D g2d = (Graphics2D) g;
        BasicStroke stroke = new BasicStroke(4.0f);
        g2d.setStroke(stroke);
        int x = getWidth() / 2;
        int y = 240;
        g2d.drawLine(x, y, x + 50, y + 50);
    }
    /**
     * draw the left leg
     * @param g
     * @param color
     */
    private void drawLeftLeg(Graphics g, Color color) {
        g.setColor(color);
        Graphics2D g2d = (Graphics2D) g;
        BasicStroke stroke = new BasicStroke(4.0f);
        g2d.setStroke(stroke);
        int x = getWidth() / 2;
        int y = 240;
        g2d.drawLine(x, y, x - 50, y + 50);
    }
    /**
     * draw the right arm
     * @param g
     * @param color
     */
    private void drawRightArm(Graphics g, Color color) {
        g.setColor(color);
        Graphics2D g2d = (Graphics2D) g;
        BasicStroke stroke = new BasicStroke(4.0f);
        g2d.setStroke(stroke);
        int x = getWidth() / 2;
        int y = 200;
        g2d.drawLine(x, y, x + 50, y - 50);
    }
    /**
     * draw the left arm
     * @param g
     * @param color
     */
    private void drawLeftArm(Graphics g, Color color) {
        g.setColor(color);
        Graphics2D g2d = (Graphics2D) g;
        BasicStroke stroke = new BasicStroke(4.0f);
        g2d.setStroke(stroke);
        int x = getWidth() / 2;
        int y = 200;
        g2d.drawLine(x, y, x - 50, y - 50);
    }
    /**
     * draw the body
     * @param g
     * @param color
     */
    private void drawBody(Graphics g, Color color) {
        g.setColor(color);
        Graphics2D g2d = (Graphics2D) g;
        BasicStroke stroke = new BasicStroke(4.0f);
        g2d.setStroke(stroke);
        int x = getWidth() / 2;
        int y = 140;
        g2d.drawLine(x, y, x, y + 100);
    }
    /**
     * draw the head
     * @param g
     * @param color
     */
    private void drawHead(Graphics g, Color color) {
        int width = 40;
        int x = (getWidth() - mWidthOfBase) / 2 + mWidthOfBase / 2 - width;
        int y = 100 - width;
        g.setColor(color);
        Graphics2D g2d = (Graphics2D) g;
        BasicStroke stroke = new BasicStroke(4.0f);
        g2d.setStroke(stroke);
        g2d.drawOval(x, y, width + width, width + width);
    }
    /**
     * draw the part above the head
     * @param g
     * @param color
     */
    private void drawHeader(Graphics g, Color color) {
        int width = mWidthOfBase / 2 + 20;
        int x = (getWidth() - mWidthOfBase) / 2 + mWidthOfBase - width;
        int y = 10;
        g.setColor(color);
        g.fill3DRect(x, y, width, mHeightOfBase, true);
    }
    /**
     * draw the pole
     * @param g
     * @param color
     */
    private void drawPole(Graphics g, Color color) {
        int height = getHeight() - 20;
        int x = (getWidth() - mWidthOfBase) / 2 + mWidthOfBase;
        int y = 10;
        g.setColor(color);
        g.fill3DRect(x, y, mWidthOfPole, height, true);
    }
    /**
     * draw the base
     * @param g
     * @param color
     */
    private void drawBase(Graphics g, Color color) {
        int x = (getWidth() - mWidthOfBase) / 2;
        int y = getHeight() - mHeightOfBase - 10;
        g.setColor(color);
        g.fillRect(x, y, mWidthOfBase, mHeightOfBase);
    }
}
