package com.convector.hbaseloader.parser.fileparser;

import com.convector.hbaseloader.constants.sequence.RowKeySequenceNumber;
import com.convector.hbaseloader.constants.values.FileType;
import com.convector.hbaseloader.parser.utils.ColumnParser;
import com.convector.hbaseloader.parser.utils.LineParser;
import com.convector.hbaseloader.parser.utils.Pairer;
import com.convector.hbaseloader.transform.BusTranslator;
import com.convector.hbaseloader.transform.Pair;
import com.convector.hbaseloader.transform.SingleRow;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Created by aquirogb on 09/05/2016.
 */
public class BusFileParser implements FileParser {

    private Path filePath;
    private BufferedReader fileReader;

    private String[] values;
    private Pair[] pairs;

    private double totalRows;
    private double currentRow;

    public BusFileParser (Path filePath) {
        this.filePath = filePath;
        totalRows = countLines();
        currentRow = 1;
        try {
            fileReader = new BufferedReader(new FileReader(filePath.toString()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void parseFile() {
        System.out.println("Parsing file " + filePath.getFileName() + "...");
        String line = readLine();
        String[] qualifiers = null;
        if(line != null) qualifiers = ColumnParser.parseColumns(FileType.BUS,line); //TODO fix this filetype.metro

        while ((line = readLine()) != null){
            ++currentRow;
            values = LineParser.parseValues(FileType.BUS,line);
            pairs = Pairer.makePairs(FileType.BUS,qualifiers,values);
            BusTranslator.translateToRow(currentRow/totalRows, pairs);
        }
        RowKeySequenceNumber.getInstance().close();
    }

    @Override
    public int countLines() {
        int counter = 0;
        try {
            BufferedReader lineCounter = new BufferedReader(new FileReader(filePath.toString()));
            while(lineCounter.readLine() != null) counter++;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return counter;
    }

    private String readLine() {
        String line = null;
        try {
            line = fileReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

}
