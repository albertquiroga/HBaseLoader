package com.convector.hbaseloader.main;

import com.convector.hbaseloader.output.Outputer;
import com.convector.hbaseloader.output.OutputerFactory;
import com.convector.hbaseloader.parser.fileparser.FileParser;
import com.convector.hbaseloader.parser.fileparser.FileParserFactory;
import com.convector.hbaseloader.transform.SingleRow;
import com.convector.hbaseloader.utils.Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by pyro_ on 18/05/2016.
 */
public class Main {

    public static void main (String[] args) {

        if(args.length < 2 || args[0].equals("help")){
            printOptions();
            System.exit(1);
        }

        long startTime = System.currentTimeMillis();
        Outputer out = OutputerFactory.getOutputter(args[0]);
        SingleRow.getInstance().addObserver(out);

        FileParser fp;
        for (int i=1; i<args.length; i++) {
            System.out.println("File " + i + "/" + (args.length-1));
            fp = FileParserFactory.getFileParser(args[i]);
            fp.parseFile();
        }

        out.close();
        System.out.println("Task executed in " + Utils.formatTimeInMs(System.currentTimeMillis()-startTime));

    }

    private static void printOptions() {
        System.out.println("Use like the following:");
        System.out.println("\t> hadoop-2.5.1/bin/hadoop jar HBaseLoader.jar <output> <input>");
        System.out.println("Where <output> can be:");
        System.out.println("\t hbase:\t\toutputs data to HBase");
        System.out.println("\t screen:\tprints output to the screen");
        System.out.println("\t cartodb:\toutputs data to a CartoDB table");
        System.out.println("And <input> is the file (or files) to read.");
    }

}
