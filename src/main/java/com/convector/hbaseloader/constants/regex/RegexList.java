package com.convector.hbaseloader.constants.regex;

/**
 * Created by pyro_ on 03/05/2016.
 */

public abstract class RegexList {

    public static final String metroFilePattern     = "validacion_metro_[0-9]{8}.csv";
    //public static final String metroColumnSplitter  = "(^\\\"|\"$|\\\"\\|\\\")";
    public static final String metroColumnSplitter  = "\\|";
    public static final String metroValueSplitter   = "\\|";
    public static final String metroTimeFormat = "dd/MM/yy HH:mm:ss";

    public static final String busFilePattern       = "validacion_bus_[0-9]{8}.csv";
    //public static final String busColumnSplitter    = "(^\\\"|\"$|\\\"\\|\\\")";
    public static final String busColumnSplitter    = "\\|";
    public static final String busValueSplitter     = "\\|";
    public static final String busTimeFormat = "dd/MM/yy HH:mm:ss";

}
