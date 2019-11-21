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


class XAndOTile extends JPanel implements MouseListener {
    private int red, green, blue;
    private String letter;
    private int randomShape;
    private Face myFace;
    private boolean drawFace;

    XAndOTile() {
        super();
        SetRandomValue();

        addMouseListener(this);
        randomShape = GetNumberBetween(0,1);
        drawFace = false;
        myFace = new Face(16,10,40,40);     
    }

    final public void SetRandomValue() {
        red = GetNumberBetween(0,255);
        green = GetNumberBetween(0,255);
        blue = GetNumberBetween(0,255);

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

        int randomLetter = GetNumberBetween(0,25);
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

            if (randomShape == 1) {
                g.setColor(new Color(red,green,blue));
                g.fillOval(10, 10, panelWidth-15, panelHeight-10);
            }
            else {
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
        repaint();
    }
    
    public void mouseExited(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}

    public String toString() {
        String myString = "LETTER: " + letter + ",  COLOR: red: " + red + ", green: " + green + ", blue: " + blue;
        return(myString);
    }
}


class MosaicFrame extends JFrame implements ActionListener{
    private ArrayList<XAndOTile> tileList;
    

    public MosaicFrame() {
        
        setBounds(40,40,900,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        JButton randomizeButton = new JButton("Randomize");
        buttonPanel.add(randomizeButton);
        randomizeButton.addActionListener(this);

        JPanel xAndOPanel = new JPanel();
        contentPane.add(xAndOPanel, BorderLayout.CENTER);
        xAndOPanel.setLayout(new GridLayout(10,10));

        tileList = new ArrayList<XAndOTile>();
        for(int i = 0; i < 100; i++) {
            XAndOTile tile = new XAndOTile();
            xAndOPanel.add(tile);
            tileList.add(tile);
        }
    }

    public void actionPerformed(ActionEvent e) {
        for (XAndOTile tile : tileList) {
            tile.SetRandomValue();
        }
        System.out.println("\nMosaicPlus Repainting...\n");
        repaint();
    }
}



public class MosaicPlus {
    public static void main (String[] args) {
        System.out.println("\nMosaicPlus Starting...\n");

        MosaicFrame myMosaicFrame = new MosaicFrame();
        myMosaicFrame.setVisible(true);
    }
}