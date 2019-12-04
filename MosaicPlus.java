/*******************************************************************************
 * Copyright (C) 2019 Luciano Petrongelli & Sarah Mahram All rights reserved. 
 * 
 * This file is part of the MosaicPlus project.
 * 
 * This file can not be copied and/or distributed without 
 * the express permission of Luciano Petrongelli & Sarah Mahram
 * 
 ******************************************************************************/



public class MosaicPlus {
    public static void main (String[] args) {
        System.out.println("\nMosaicPlus Starting...\n");

        int layoutNum = 0;
        MosaicFrame myMosaicFrame = new MosaicFrame(layoutNum);
        myMosaicFrame.setVisible(true);
    }
}