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

import java.io.*;
import javax.sound.sampled.*;


class MosaicFrame extends JFrame implements ActionListener{
    private ArrayList<myTile> tileList;
    private MosaicFrame myMosaicFrame;



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
        fileMenu.add(newMenuItem); // add new selection
        newMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int layoutNum = 0;

                dispose();
                MosaicFrame myMosaicFrame = new MosaicFrame(layoutNum);
                myMosaicFrame.setVisible(true);
            }
        });
        fileMenu.add(newMenuItem);

        JMenuItem exitMenuItem = new JMenuItem("Exit", KeyEvent.VK_X); // create Exit tab
        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(exitMenuItem);
        

        JMenu shapeMenu = new JMenu("Shape"); // create ShapePicker Tab
        shapeMenu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(shapeMenu); // add ShapePicker tab

        JMenuItem ovalMenuItem = new JMenuItem("Oval", KeyEvent.VK_O); // create Oval selection
        shapeMenu.add(ovalMenuItem); // add Oval selection
        ovalMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (myTile tile : tileList) {
                    tile.SetAllOval(); // sets tiles to oval
                }
                System.out.println("\nMosaicPlus Repainting...\n");
                repaint(); // repainting
            }
        });
        shapeMenu.add(ovalMenuItem);

        JMenuItem rectangleMenuItem = new JMenuItem("Rectangle", KeyEvent.VK_O); // create Rectangle selection
        shapeMenu.add(rectangleMenuItem); // add rectangle selection
        rectangleMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (myTile tile : tileList) {
                    tile.SetAllRectangle(); // sets tiles to oval
                }
                System.out.println("\nMosaicPlus Repainting...\n");
                repaint(); // repainting
            }
        });
        shapeMenu.add(rectangleMenuItem);


        JMenu colorMenu = new JMenu("Color"); // create ColorPicker Tab
        colorMenu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(colorMenu); // add ColorPicker tab


        JMenuItem redMenuItem = new JMenuItem("Red", KeyEvent.VK_O); // create red selection
        colorMenu.add(redMenuItem); // add red selection
        redMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (myTile tile : tileList) {
                    tile.SetRedColor(); // sets tiles to red
                }
                System.out.println("\nMosaicPlus Repainting...\n");
                repaint(); // repainting
            }
        });
        colorMenu.add(redMenuItem);

        JMenuItem greenMenuItem = new JMenuItem("Green", KeyEvent.VK_O); // create green selection
        colorMenu.add(greenMenuItem); // add green selection
        greenMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (myTile tile : tileList) {
                    tile.SetGreenColor(); // sets tiles to green
                }
                System.out.println("\nMosaicPlus Repainting...\n");
                repaint(); // repainting
            }
        });
        colorMenu.add(greenMenuItem);

        JMenuItem blueMenuItem = new JMenuItem("Blue", KeyEvent.VK_O); // create blue selection
        colorMenu.add(blueMenuItem); // add blue selection
        blueMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (myTile tile : tileList) {
                    tile.SetBlueColor(); // sets tiles to blue
                }
                System.out.println("\nMosaicPlus Repainting...\n");
                repaint(); // repainting
            }
        });
        colorMenu.add(blueMenuItem);

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
        play(); //Plays sound
        repaint();
    }

    public static void play(){//Sound for clicking Randomize button
            try
            {

                Clip clip = AudioSystem.getClip(); //Entry point to audio system resources
                clip.open(AudioSystem.getAudioInputStream(new File("C:\\Sound\\Swoosh.wav")));
                clip.start();
            }
            catch (Exception exc)
            {
                exc.printStackTrace(System.out);
            }
    }

    
    public static void play2(){//Sound for clicking tiles
            try
            {

                Clip clip = AudioSystem.getClip(); //Entry point to audio system resources
                clip.open(AudioSystem.getAudioInputStream(new File("C:\\Sound\\MouseDoubleClick.wav")));
                clip.start();
            }
            catch (Exception exc)
            {
                exc.printStackTrace(System.out);
            }
    }

}