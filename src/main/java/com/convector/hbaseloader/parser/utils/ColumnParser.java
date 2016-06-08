package com.convector.hbaseloader.parser.utils;

import com.convector.hbaseloader.constants.regex.RegexList;
import com.convector.hbaseloader.constants.values.FileType;

import java.util.ArrayList;

/**
 * Created by pyro_ on 03/05/2016.
 */
public abstract class ColumnParser {

    public static String[] parseColumns (FileType type, String columnLine) {
        String[] columns = null;
        switch(type){
            case BUS:   columns = columnLine.replace("\"","").split(RegexList.busColumnSplitter);
                break;
            case METRO: columns = columnLine.replace("\"","").split(RegexList.metroColumnSplitter);
                break;
        }
        return trimArray(columns);
    }

    private static String[] trimArray (String[] array) {
        ArrayList<String> al = new ArrayList<String>();
        for (String s:array) if(!s.isEmpty()) al.add(s.toUpperCase());
        String[] newArray = new String[al.size()];
        return al.toArray(newArray);
    }

}
