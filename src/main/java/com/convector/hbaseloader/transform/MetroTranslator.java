package com.convector.hbaseloader.transform;

import com.convector.hbaseloader.constants.maps.MetroToQualifier;
import com.convector.hbaseloader.constants.regex.RegexList;
import com.convector.hbaseloader.constants.sequence.RowKeySequenceNumber;
import com.convector.hbaseloader.constants.values.MetroColumn;
import com.convector.hbaseloader.constants.values.Qualifier;
import com.convector.hbaseloader.parser.utils.TimeParser;

import java.util.EnumMap;

/**
 * Created by pyro_ on 03/05/2016.
 */
public abstract class MetroTranslator {

    private static String rowKey, id, cod_titulo, num_zonas;
    private static String timestamp;
    private static RowKeySequenceNumber sequenceNumber = RowKeySequenceNumber.getInstance();

    public static void translateToRow (double progress, Pair[]pairs) {
        rowKey = id = cod_titulo = num_zonas = "";
        timestamp = "";
        EnumMap<Qualifier,String> values = new EnumMap<Qualifier, String>(Qualifier.class);
        values.put(Qualifier.COMPANY,"1");

        for (Pair<MetroColumn,String> p : pairs) {
            MetroColumn mc = (MetroColumn) p.getFirst();
            Qualifier q;
            switch (mc){
                case INSTANTE_PASO:         timestamp = p.getSecond();
                    break;
                case NUM_IDENTIFICACION:    id = p.getSecond();
                    break;
                case TITULO_COD:            cod_titulo = p.getSecond();
                    break;
                case NUM_ZONAS:             num_zonas = p.getSecond();
                    break;
                default:                    if((q = MetroToQualifier.getQualifier(mc)) != null) values.put(q,p.getSecond().replace("\"",""));
            }
        }
        rowKey += id + cod_titulo + num_zonas + "-" + sequenceNumber.getNumber();
        SingleRow.getInstance().setRow(progress, rowKey, TimeParser.parseDate(RegexList.metroTimeFormat,timestamp),values);
    }

}
