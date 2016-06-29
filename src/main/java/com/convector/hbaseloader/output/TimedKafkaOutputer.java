package com.convector.hbaseloader.output;

import com.convector.hbaseloader.transform.SingleRow;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Observable;
import java.util.Timer;

/**
 * Created by pyro_ on 29/06/2016.
 */
public class TimedKafkaOutputer extends Outputer {

    private long startMillis;
    private long startTime;
    private Timer t;
    private SingleRow currentRow;
    private long minus;

    public TimedKafkaOutputer() {
        //startMillis = System.currentTimeMillis();
        try {
            startMillis = new SimpleDateFormat("dd/MM/yy HH:mm:ss").parse("29/06/16 22:00:00").getTime();
            startTime = new SimpleDateFormat("dd/MM/yy HH:mm:ss").parse("01/04/16 22:00:00").getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        t = new Timer();
    }

    public void update(Observable o, Object arg) {
        currentRow = (SingleRow) o;
        minus = currentRow.getTimestamp().getTime()-startTime;
        if (startMillis + minus-System.currentTimeMillis() >= 0) {
            t.schedule(new TimedPrinter(currentRow.getRowKey(),currentRow.toString()), new Date(startMillis + minus));
            System.out.println("new event will trigger at " + new Timestamp(startMillis + minus).toString());
        }
        //ProgressBarPrinter.update((double) arg,System.currentTimeMillis()-startTime);
    }

}
