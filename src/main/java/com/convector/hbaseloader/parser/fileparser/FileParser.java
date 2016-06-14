package com.convector.hbaseloader.parser.fileparser;

import com.convector.hbaseloader.transform.Pair;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Created by aquirogb on 09/05/2016.
 */
public class FileParser {

    protected Path filePath;
    protected BufferedReader fileReader;

    protected String[] values;
    protected Pair[] pairs;

    protected double totalRows;
    protected double currentRow;

    protected FileParser(Path filePath) {
        this.filePath = filePath;
        totalRows = countLines();
        currentRow = 1;
        try {
            fileReader = new BufferedReader(new FileReader(filePath.toString()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected int countLines() {
        int counter = 0;
        try {
            BufferedReader lineCounter = new BufferedReader(new FileReader(filePath.toString()));
            while(lineCounter.readLine() != null) counter++;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return counter;
    }

    protected String readLine() {
        String line = null;
        try {
            line = fileReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    protected Pair[] makePairs(Object[] columns, Object[] values) {
        Pair[] pairs = new Pair[columns.length];
        for(int i=0; i<columns.length; i++) {
            pairs[i] = new Pair<Object, Object>(columns[i],values[i]);
        }
        return pairs;
    }

    public void parseFile(){}


}
