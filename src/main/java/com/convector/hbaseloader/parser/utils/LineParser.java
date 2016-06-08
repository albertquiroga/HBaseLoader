package com.convector.hbaseloader.parser.utils;

import com.convector.hbaseloader.constants.regex.RegexList;
import com.convector.hbaseloader.constants.values.FileType;


/**
 * Created by pyro_ on 03/05/2016.
 */
public abstract class LineParser {

    public static String[] parseValues (FileType type, String line) {
        String[] splitLine = null;
        switch(type) {
            case BUS:   splitLine = line.split(RegexList.busValueSplitter);
                break;
            case METRO: splitLine = line.split(RegexList.metroValueSplitter);
                break;
        }
        return splitLine;
    }
}
