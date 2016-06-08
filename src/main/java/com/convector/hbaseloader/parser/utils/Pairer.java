package com.convector.hbaseloader.parser.utils;

import com.convector.hbaseloader.constants.values.BusColumn;
import com.convector.hbaseloader.constants.values.FileType;
import com.convector.hbaseloader.constants.values.MetroColumn;
import com.convector.hbaseloader.transform.Pair;

/**
 * Created by pyro_ on 03/05/2016.
 */
public abstract class Pairer {

    public static Pair[] makePairs(FileType type, String[] columns, String[] values){
        Enum[] enumColumns = columnToEnum(type,columns);
        Pair[] pairs = new Pair[columns.length];
        for(int i=0; i<columns.length; i++) {
            pairs[i] = new Pair(enumColumns[i],values[i]);
        }
        return pairs;
    }

    private static Enum[] columnToEnum (FileType type, String[] columns){
        Enum[] enumColumns = new Enum[columns.length];
        if(type == FileType.BUS){
            for (int i=0; i<columns.length; i++){
                enumColumns[i] = BusColumn.valueOf(columns[i]);
            }
        }
        else if(type == FileType.METRO) {
            for (int i=0; i<columns.length; i++){
                enumColumns[i] = MetroColumn.valueOf(columns[i]);
            }
        }
        return enumColumns;
    }
}
