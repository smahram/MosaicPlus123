/*******************************************************************************
 * Copyright (C) 2019 Luciano Petrongelli & Sarah Mahram All rights reserved. 
 * 
 * This file is part of the MosaicPlus project.
 * 
 * This file can not be copied and/or distributed without 
 * the express permission of Luciano Petrongelli & Sarah Mahram
 * 
 ******************************************************************************/

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

    myTile() {
        super();
        SetRandomValue();

        addMouseListener(this);
        randomShape = GetNumberBetween(0,1);
        drawFace = false;
        myFace = new Face(16,10,40,40);     
    }

        final public void SetAllOval() {
            randomShape = 1;
    }

        final public void SetAllRectangle() {
            randomShape = 0;
    }

    final public void SetRandomValue() {
        red = GetNumberBetween(0,255);
        green = GetNumberBetween(0,255);
        blue = GetNumberBetween(0,255);
        setLetters();
    }

    final public void SetRedColor() {
        red = GetNumberBetween(0,255);
        green = GetNumberBetween(0,0);
        blue = GetNumberBetween(0,0);
        setLetters();
    }

    final public void SetGreenColor() {
        red = GetNumberBetween(0,0);
        green = GetNumberBetween(0,255);
        blue = GetNumberBetween(0,0);
        setLetters();
    }

    final public void SetBlueColor() {
        red = GetNumberBetween(0,0);
        green = GetNumberBetween(0,0);
        blue = GetNumberBetween(0,255);
        setLetters();
    }

    final public void setLetters() {
        ArrayList<String> letters = new ArrayList<>();
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
        letters.add("0");
        letters.add("1");
        letters.add("2");
        letters.add("3");
        letters.add("4");
        letters.add("5");
        letters.add("7");
        letters.add("8");
        letters.add("9");

        int randomLetter = GetNumberBetween(0,34);
        letter = letters.get(randomLetter);
        }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        System.out.println(this);

        if (drawFace) {
            myFace.paintComponent(g);
        }
        else {
            int panelWidth = getWidth();
            int panelHeight = getHeight();

            if (randomShape == 1) { // oval
                g.setColor(new Color(red,green,blue));
                g.fillOval(10, 10, panelWidth-15, panelHeight-10);
            }
            else { // rectangle
            g.setColor(new Color(red,green,blue));
            g.fillRect(10, 10, panelWidth, panelHeight);
            }

            g.setColor(new Color(GetContrastingColor(red), GetContrastingColor(green), GetContrastingColor(blue)));
            
            final int fontSize = 25;
            g.setFont(new Font("Comic Sans MS", Font.BOLD, fontSize));
            int stringX = (panelWidth / 2) - 5;
            int stringY = (panelHeight / 2) + 15;
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

