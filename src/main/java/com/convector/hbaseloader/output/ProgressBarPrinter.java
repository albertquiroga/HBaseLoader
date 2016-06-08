package com.convector.hbaseloader.output;

import com.convector.hbaseloader.utils.Utils;

/**
 * Created by aquirogb on 09/05/2016.
 */
public abstract class ProgressBarPrinter {

    private static final int size = 30;

    public static void update (double currentProgress, long currentElapsedTimeMillis) {
        System.out.print("\r" + (int) Math.ceil(currentProgress*100) + "%|");
        int i = 0;
        for(; i<=(int)(currentProgress*size);i++) {
            System.out.print("#");
        }
        for(; i<size; i++) {
            System.out.print("-");
        }
        System.out.print("| ");
        if(currentProgress == 1){
            System.out.println("done!" + " " + Utils.formatTimeInMs(currentElapsedTimeMillis));
        } else {
            System.out.print((int)currentProgress*100+"/100%" + " " + Utils.formatTimeInMs(currentElapsedTimeMillis));
        }
    }

}
