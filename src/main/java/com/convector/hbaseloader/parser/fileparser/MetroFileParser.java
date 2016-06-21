package com.convector.hbaseloader.parser.fileparser;

import com.convector.hbaseloader.constants.regex.RegexList;
import com.convector.hbaseloader.constants.sequence.RowKeySequenceNumber;
import com.convector.hbaseloader.constants.values.MetroColumn;
import com.convector.hbaseloader.transform.MetroTranslator;

import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Created by aquirogb on 09/05/2016.
 */
public class MetroFileParser extends FileParser {

    private MetroColumn[] columns;

    public MetroFileParser (Path filePath) {
        super(filePath);
    }

    public void parseFile() {
        System.out.println("Working on file " + filePath.getFileName() + "...");
        //First line is column names
        String line = readLine();
        columns = parseColumns(line);

        //The rest are values
        while ((line = readLine()) != null){
            currentRow++;
            super.values = parseValues(line);
            MetroTranslator.translateToRow(currentRow/totalRows, super.makePairs(columns,values));
        }
        RowKeySequenceNumber.getInstance().close();
    }

    private MetroColumn[] parseColumns(String columnLine) {
        return fancyArray(columnLine.replace("\"","").split(RegexList.metroColumnSplitter));
    }

    private String[] parseValues(String valuesLine) {
        return valuesLine.replace("\"","").split(RegexList.metroValueSplitter);
    }

    private MetroColumn[] fancyArray(String[] array) {
        ArrayList<MetroColumn> arrayList = new ArrayList<>();
        for(String s:array) if(!s.isEmpty()) arrayList.add(MetroColumn.valueOf(s.toUpperCase()));
        MetroColumn[] newArray = new MetroColumn[arrayList.size()];
        return arrayList.toArray(newArray);
    }

}
