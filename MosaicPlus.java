/******************************************************************************
 * Copyright (C) 2019 Sarah Mahram and Luciano Petrongelli
 * 
 * This file is a stand-alone library liscensed under the BSD-3-Clause
 * 
 * You are free to reuse the unmodified version of this file in your projects
 * as long as you give credit to the library in your source code.  
 * 
 *****************************************************************************/


public class MosaicPlus {
    public static void main (String[] args) {
        System.out.println("\nMosaicPlus Starting...\n");

        int layoutNum = 0;
        MosaicFrame myMosaicFrame = new MosaicFrame(layoutNum);
        myMosaicFrame.setVisible(true);
    }
}