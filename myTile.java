/******************************************************************************
 * Copyright (C) 2019 Sarah Mahram and Luciano Petrongelli
 * 
 * This file is a stand-alone library liscensed under the BSD-3-Clause
 * 
 * You are free to reuse the unmodified version of this file in your projects
 * as long as you give credit to the library in your source code.  
 * 
 *****************************************************************************/

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.BorderLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

import java.util.ArrayList;
import java.util.Random;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;


class myTile extends JPanel implements MouseListener  {
    private int red, green, blue;
    private String letter;
    private int randomShape;
    private Face myFace;
    private boolean drawFace;

    int panelWidth = getWidth();
    int panelHeight = getHeight();

    myTile() {
        super();
        SetRandomValue();

        addMouseListener(this);
        randomShape = GetNumberBetween(0,1);
        drawFace = false;
        myFace = new Face(0, 0, 0, 0);     
    }

        final public void SetAllOval() {
            randomShape = 1;
    }

        final public void SetAllRectangle() {
            randomShape = 0;
    }

    final public void SetRandomValue() { // sets color to random
        red = GetNumberBetween(0,255);
        green = GetNumberBetween(0,255);
        blue = GetNumberBetween(0,255);
        setLetters(); // adds letter
        drawFace = false;
    }

    final public void SetRedColor() { // sets color to red
        red = GetNumberBetween(0,255);
        green = GetNumberBetween(0,0);
        blue = GetNumberBetween(0,0);
        setLetters(); // adds letter
    }

    final public void SetGreenColor() { // sets color to green
        red = GetNumberBetween(0,0);
        green = GetNumberBetween(0,255);
        blue = GetNumberBetween(0,0);
        setLetters(); // adds letter
    }

    final public void SetBlueColor() { // sets color to blue
        red = GetNumberBetween(0,0);
        green = GetNumberBetween(0,0);
        blue = GetNumberBetween(0,255);
        setLetters(); // adds letter
    }

    final public void setLetters() {
        ArrayList<String> letters = new ArrayList<>(); // A-Z, a-z, 0-9, and some symbols are added
        letters.add("A");
        letters.add("B");
        letters.add("C");
        letters.add("D");
        letters.add("E");
        letters.add("F");
        letters.add("G");
        letters.add("H");
        letters.add("I");
        letters.add("J");
        letters.add("K");
        letters.add("L");
        letters.add("M");
        letters.add("N");
        letters.add("O");
        letters.add("P");
        letters.add("Q");
        letters.add("R");
        letters.add("S");
        letters.add("T");
        letters.add("U");
        letters.add("V");
        letters.add("W");
        letters.add("X");
        letters.add("Y");
        letters.add("Z");

        letters.add("a");
        letters.add("b");
        letters.add("c");
        letters.add("d");
        letters.add("e");
        letters.add("f");
        letters.add("g");
        letters.add("h");
        letters.add("i");
        letters.add("j");
        letters.add("k");
        letters.add("l");
        letters.add("m");
        letters.add("n");
        letters.add("o");
        letters.add("p");
        letters.add("q");
        letters.add("r");
        letters.add("s");
        letters.add("t");
        letters.add("u");
        letters.add("v");
        letters.add("w");
        letters.add("x");
        letters.add("y");
        letters.add("z");

        letters.add("0");
        letters.add("1");
        letters.add("2");
        letters.add("3");
        letters.add("4");
        letters.add("5");
        letters.add("7");
        letters.add("8");
        letters.add("9");

        letters.add("!");
        letters.add("@");
        letters.add("#");
        letters.add("$");
        letters.add("%");
        letters.add("&");
        letters.add("*");
        letters.add("-");
        letters.add("+");
        letters.add("=");

        int randomLetter = GetNumberBetween(0,70);
        letter = letters.get(randomLetter); // sets letter
        }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int panelWidth = getWidth();
        int panelHeight = getHeight();
        
        
        System.out.println(this);

        if (drawFace) {
            myFace.setWidth(panelWidth);
            myFace.setHeight(panelHeight);

            myFace.paintComponent(g);
        }
        else {


            if (randomShape == 1) { // oval
                g.setColor(new Color(red,green,blue));
                g.fillOval(1, 1, panelWidth, panelHeight);
            }
            else { // rectangle
            g.setColor(new Color(red,green,blue));
            g.fillRect(1, 1, panelWidth, panelHeight);
            }

            g.setColor(new Color(GetContrastingColor(red), GetContrastingColor(green), GetContrastingColor(blue))); // sets letter color
            
            final int fontSize = 25;
            g.setFont(new Font("Comic Sans MS", Font.BOLD, fontSize)); // letter details
            int stringX = (panelWidth / 2) - 10;
            int stringY = (panelHeight / 2) + 10;
            g.drawString(letter, stringX, stringY);
        }   
    }

    public static int GetContrastingColor(int colorIn) {
        return ((colorIn +128) % 256);
    }

    private static int GetNumberBetween(int min, int max) {
        Random myRandom = new Random();
        return min + myRandom.nextInt(max-min+1);
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}

    public void mouseClicked(MouseEvent e) {
        System.out.println("mouseClicked");
        drawFace = true;
        MosaicFrame.play2(); //Plays sound
        repaint();
    }
    
    public void mouseExited(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}

    public String toString() {
        String myString = "LETTER: " + letter + ",  COLOR: red: " + red + ", green: " + green + ", blue: " + blue;
        return(myString);
    }
}
