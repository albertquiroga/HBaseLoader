package com.convector.hbaseloader.constants.maps;

import com.convector.hbaseloader.constants.values.MetroColumn;
import com.convector.hbaseloader.constants.values.Qualifier;

import java.util.EnumMap;

/**
 * Created by pyro_ on 05/05/2016.
 */
public abstract class MetroToQualifier {

    private static EnumMap<MetroColumn, Qualifier> metroTranslateMap;

    static {
        metroTranslateMap = new EnumMap<MetroColumn, Qualifier>(MetroColumn.class);

        metroTranslateMap.put(MetroColumn.ESTACION_COD,         Qualifier.STOP);
        metroTranslateMap.put(MetroColumn.VESTIBULO,            Qualifier.STOPENTRANCE);
        metroTranslateMap.put(MetroColumn.TITULO_COD,           Qualifier.TICKETTYPE);
        metroTranslateMap.put(MetroColumn.NUM_ZONAS,            Qualifier.ZONEAMOUNT);
        metroTranslateMap.put(MetroColumn.NUM_IDENTIFICACION,   Qualifier.TICKETID);
        metroTranslateMap.put(MetroColumn.NUM_CORRESP,          Qualifier.TRANSFERAMOUNT);
        metroTranslateMap.put(MetroColumn.TRANS_ANT_COD,        Qualifier.PREVIOUSTYPE);
        metroTranslateMap.put(MetroColumn.LINIA_ANT,            Qualifier.PREVIOUSLINE);
        metroTranslateMap.put(MetroColumn.ESTACION_ANT,         Qualifier.PREVIOUSSTOP);
    }

    public static Qualifier getQualifier(MetroColumn mc) {
        return metroTranslateMap.get(mc);
    }
}
