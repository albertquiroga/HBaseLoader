package com.convector.hbaseloader.parser.fileparser;

import com.convector.hbaseloader.constants.regex.RegexList;
import com.convector.hbaseloader.constants.sequence.RowKeySequenceNumber;
import com.convector.hbaseloader.constants.values.BusColumn;
import com.convector.hbaseloader.transform.BusTranslator;

import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Created by aquirogb on 09/05/2016.
 */
public class BusFileParser extends FileParser {

    private BusColumn[] columns;

    public BusFileParser (Path filePath) {
        super(filePath);
    }

    public void parseFile() {
        System.out.println("Working on file " + filePath.getFileName() + "...");
        //First line is column names
        String line = readLine();
        columns = parseColumns(line);

        //The rest are values
        while ((line = readLine()) != null){
            ++currentRow;
            super.values = parseValues(line);
            BusTranslator.translateToRow(currentRow/totalRows, super.makePairs(columns,values));
        }
        RowKeySequenceNumber.getInstance().close();
    }

    private BusColumn[] parseColumns(String columnLine) {
        return fancyArray(columnLine.replace("\"","").split(RegexList.busColumnSplitter));
    }

    private String[] parseValues(String valuesLine) {
        return valuesLine.split(RegexList.busValueSplitter);
    }

    private BusColumn[] fancyArray(String[] array) {
        ArrayList<BusColumn> arrayList = new ArrayList<>();
        for (String s:array) if(!s.isEmpty()) arrayList.add(BusColumn.valueOf(s.toUpperCase()));
        BusColumn[] newArray = new BusColumn[arrayList.size()];
        return arrayList.toArray(newArray);
    }

}
