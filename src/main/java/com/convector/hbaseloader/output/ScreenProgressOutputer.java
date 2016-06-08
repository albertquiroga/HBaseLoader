package com.convector.hbaseloader.output;

import java.util.Observable;

/**
 * Created by pyro on 6/6/16.
 */
public class ScreenProgressOutputer extends Outputer {

    private long startTime;

    public ScreenProgressOutputer() {
        startTime = System.currentTimeMillis();
    }
    @Override
    public void update(Observable o, Object arg) {
        ProgressBarPrinter.update((double) arg,System.currentTimeMillis()-startTime);
    }
}
