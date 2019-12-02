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

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.MouseMotionListener;

class myTile extends JPanel implements MouseListener  {
    public int red, green, blue;
    private String letter;
    public int randomShape;
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
    private ArrayList<myTile> tileList;
    private MosaicFrame myMosaicFrame;
    myTile tile = new myTile(); 


    public MosaicFrame(int layoutNum) {
        
        setBounds(40,40,900,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        JButton randomizeButton = new JButton("Randomize");
        buttonPanel.add(randomizeButton);
        randomizeButton.addActionListener(this);

        JPanel myPanel = new JPanel();
        contentPane.add(myPanel, BorderLayout.CENTER);


        if (layoutNum != 0) {
            myPanel.setLayout(new GridLayout(layoutNum,layoutNum));

            tileList = new ArrayList<myTile>();
            for(int i = 0; i < layoutNum*layoutNum; i++) {
                myTile tile = new myTile();
                myPanel.add(tile);
                tileList.add(tile);
            }
        } else {
            myPanel.setLayout(new GridLayout(10,10));

            tileList = new ArrayList<myTile>();
            for(int i = 0; i < 100; i++) {
                myTile tile = new myTile();
                myPanel.add(tile);
                tileList.add(tile);
            }
        }

        // Create menu bar and menus. 
        JMenuBar menuBar = new JMenuBar();
        add(menuBar, BorderLayout.NORTH); 


        JMenu fileMenu = new JMenu("File"); // create File tab
        fileMenu.setMnemonic(KeyEvent.VK_E);
        menuBar.add(fileMenu); // add File tab
        
        JMenuItem newMenuItem = new JMenuItem("New", KeyEvent.VK_C); // create New selection
        newMenuItem.addActionListener(this); // if clicked then random
        fileMenu.add(newMenuItem); // add new selector
        

        JMenuItem exitMenuItem = new JMenuItem("Exit", KeyEvent.VK_X); // create Exit tab
        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(exitMenuItem);
        
        JMenu colorMenu = new JMenu("Color"); // create Color tab
        colorMenu.setMnemonic(KeyEvent.VK_E);
        menuBar.add(colorMenu); // add Color tab
         
        JMenuItem redMenuItem = new JMenuItem("Red", KeyEvent.VK_C); // create New selection
        newMenuItem.addActionListener(this); // if clicked then random
        colorMenu.add(redMenuItem);

        JMenu shapeMenu = new JMenu("Shape"); // create ShapePicker Tab
        shapeMenu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(shapeMenu); // add ShapePicker tab

        JMenuItem ovalMenuItem = new JMenuItem("Oval", KeyEvent.VK_O); // create Oval selection
        shapeMenu.add(ovalMenuItem); // add Oval selection
        // ovalMenuItem.addActionListener(new ActionListener() {
        //     public void actionPerformed(ActionEvent e) {
        //         if(tile.randomShape == 0){
                    
        //             dispose();
        //             MosaicFrame myMosaicFrame = new MosaicFrame(tile.randomShape);
        //             myMosaicFrame.setVisible(true);
        //         }
               
        //     }
        // });
        ovalMenuItem.add(ovalMenuItem);

        JMenuItem squareMenuItem = new JMenuItem("Square", KeyEvent.VK_O); // create Square selection
        shapeMenu.add(squareMenuItem); // add Square selection

        JMenuItem randomMenuItem = new JMenuItem("Random", KeyEvent.VK_O); // creates Random selection
        shapeMenu.add(randomMenuItem); // add Random selection

        JMenu layoutMenu = new JMenu("Layout"); // create LayoutPicker Tab
        layoutMenu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(layoutMenu); // add layoutPicker tab

        JMenuItem layout6MenuItem = new JMenuItem("6", KeyEvent.VK_O); // create layout 6 selection
        layoutMenu.add(layout6MenuItem); // add layout 6 selection
        layout6MenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int layoutNum = 6;
                
                dispose();
                MosaicFrame myMosaicFrame = new MosaicFrame(layoutNum);
                myMosaicFrame.setVisible(true);
            }
        });
        layoutMenu.add(layout6MenuItem);

        JMenuItem layout7MenuItem = new JMenuItem("7", KeyEvent.VK_O); // create layout 7 selection
        layoutMenu.add(layout7MenuItem); // add layout 7 selection
        layout7MenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int layoutNum = 7;
                
                dispose();
                MosaicFrame myMosaicFrame = new MosaicFrame(layoutNum);
                myMosaicFrame.setVisible(true);
            }
        });
        layoutMenu.add(layout7MenuItem);

        JMenuItem layout8MenuItem = new JMenuItem("8", KeyEvent.VK_O); // create layout 8 selection
        layoutMenu.add(layout8MenuItem); // add layout 8 selection
        layout8MenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int layoutNum = 8;
                
                dispose();
                MosaicFrame myMosaicFrame = new MosaicFrame(layoutNum);
                myMosaicFrame.setVisible(true);
            }
        });
        layoutMenu.add(layout8MenuItem);

        JMenuItem layout9MenuItem = new JMenuItem("9", KeyEvent.VK_O); // create layout 9 selection
        layoutMenu.add(layout9MenuItem); // add layout 9 selection
        layout9MenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int layoutNum = 9;
                
                dispose();
                MosaicFrame myMosaicFrame = new MosaicFrame(layoutNum);
                myMosaicFrame.setVisible(true);
            }
        });
        layoutMenu.add(layout9MenuItem);

        JMenuItem layout10MenuItem = new JMenuItem("10", KeyEvent.VK_O); // create layout 10 selection
        layoutMenu.add(layout10MenuItem); // add layout 10 selection
        layout10MenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int layoutNum = 10;
                
                dispose();
                MosaicFrame myMosaicFrame = new MosaicFrame(layoutNum);
                myMosaicFrame.setVisible(true);
            }
        });
        layoutMenu.add(layout10MenuItem);

        JMenuItem layout11MenuItem = new JMenuItem("11", KeyEvent.VK_O); // create layout 11 selection
        layoutMenu.add(layout11MenuItem); // add layout 11 selection
        layout11MenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int layoutNum = 11;
                
                dispose();
                MosaicFrame myMosaicFrame = new MosaicFrame(layoutNum);
                myMosaicFrame.setVisible(true);
            }
        });
        layoutMenu.add(layout11MenuItem);

        JMenuItem layout12MenuItem = new JMenuItem("12", KeyEvent.VK_O); // create layout 12 selection
        layoutMenu.add(layout12MenuItem); // add layout 12 selection
        layout12MenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int layoutNum = 12;
                
                dispose();
                MosaicFrame myMosaicFrame = new MosaicFrame(layoutNum);
                myMosaicFrame.setVisible(true);
            }
        });
        layoutMenu.add(layout12MenuItem);
        


    }

    public void actionPerformed(ActionEvent e) {
        for (myTile tile : tileList) {
            tile.SetRandomValue();
        }
        System.out.println("\nMosaicPlus Repainting...\n");
        repaint();
    }

}



public class MosaicPlus {
    public static void main (String[] args) {
        System.out.println("\nMosaicPlus Starting...\n");

        int layoutNum = 0;
        MosaicFrame myMosaicFrame = new MosaicFrame(layoutNum);
        myMosaicFrame.setVisible(true);
    }
}