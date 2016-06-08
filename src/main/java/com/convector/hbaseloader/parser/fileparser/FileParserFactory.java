package com.convector.hbaseloader.parser.fileparser;

import com.convector.hbaseloader.constants.regex.RegexList;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by aquirogb on 09/05/2016.
 */
public abstract class FileParserFactory {

    public static FileParser getFileParser (String filePath) {
        Path p = Paths.get(filePath);
        FileParser fp = null;

        if(p.getFileName().toString().matches(RegexList.busFilePattern)){
            fp = new BusFileParser(p);
        }
        else if(p.getFileName().toString().matches(RegexList.metroFilePattern)) {
            fp = new MetroFileParser(p);
        }
        else {
            //TODO add exception
            System.err.println("File could not be classified.");
        }
        return fp;
    }

}
