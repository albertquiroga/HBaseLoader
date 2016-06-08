package com.convector.hbaseloader.constants.maps;

import com.convector.hbaseloader.constants.values.BusColumn;
import com.convector.hbaseloader.constants.values.Qualifier;

import java.util.EnumMap;

/**
 * Created by pyro_ on 05/05/2016.
 */
public abstract class BusToQualifier {

    private static EnumMap<BusColumn,Qualifier> busTranslateMap;

    static {
        busTranslateMap = new EnumMap<BusColumn,Qualifier>(BusColumn.class);
        busTranslateMap.put(BusColumn.PARADA,               Qualifier.STOP);
        busTranslateMap.put(BusColumn.LINEA,                Qualifier.LINE);
        busTranslateMap.put(BusColumn.VEHICULO,             Qualifier.VEHICLE);
        busTranslateMap.put(BusColumn.TURNO,                Qualifier.TURN);
        busTranslateMap.put(BusColumn.VIAJE,                Qualifier.LAP);
        busTranslateMap.put(BusColumn.SENTIDO,              Qualifier.DIRECTION);
        busTranslateMap.put(BusColumn.TITULO_COD,           Qualifier.TICKETTYPE);
        busTranslateMap.put(BusColumn.NUM_ZONAS,            Qualifier.ZONEAMOUNT);
        busTranslateMap.put(BusColumn.NUM_IDENTIFICACION,   Qualifier.TICKETID);
        busTranslateMap.put(BusColumn.NUM_CORRESP,          Qualifier.TRANSFERAMOUNT);
        busTranslateMap.put(BusColumn.MODO_TRANSP_ANT,      Qualifier.PREVIOUSTYPE);
        busTranslateMap.put(BusColumn.LINEA_ANT,            Qualifier.PREVIOUSLINE);
        busTranslateMap.put(BusColumn.ZONA_ANT,             Qualifier.PREVIOUSZONE);
    }

    public static Qualifier getQualifier(BusColumn bc) {
        return busTranslateMap.get(bc);
    }
}
