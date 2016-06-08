package com.convector.hbaseloader.output;

import java.util.Observable;

/**
 * Created by pyro_ on 18/05/2016.
 */

public class ScreenOutputer extends Outputer {

    public ScreenOutputer() {
    }

    public void update(Observable o, Object arg) {
        System.out.println(o.toString());
    }

}
