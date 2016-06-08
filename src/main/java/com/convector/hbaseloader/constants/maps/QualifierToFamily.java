package com.convector.hbaseloader.constants.maps;

import com.convector.hbaseloader.constants.values.Family;
import com.convector.hbaseloader.constants.values.Qualifier;

import java.util.EnumMap;

/**
 * Created by pyro_ on 05/05/2016.
 */
public abstract class QualifierToFamily {

    private static EnumMap<Qualifier,Family> familyMap;

    static {
        familyMap = new EnumMap<Qualifier, Family>(Qualifier.class);

        familyMap.put(Qualifier.COMPANY,        Family.Stop);
        familyMap.put(Qualifier.STOP,           Family.Stop);
        familyMap.put(Qualifier.STOPENTRANCE,   Family.Stop);
        familyMap.put(Qualifier.LINE,           Family.Stop);
        familyMap.put(Qualifier.VEHICLE,        Family.Stop);
        familyMap.put(Qualifier.TURN,           Family.Stop);
        familyMap.put(Qualifier.LAP,            Family.Stop);
        familyMap.put(Qualifier.DIRECTION,      Family.Stop);

        familyMap.put(Qualifier.TICKETTYPE,     Family.Ticket);
        familyMap.put(Qualifier.ZONEAMOUNT,     Family.Ticket);
        familyMap.put(Qualifier.TICKETID,       Family.Ticket);

        familyMap.put(Qualifier.TRANSFERAMOUNT, Family.Transfer);
        familyMap.put(Qualifier.PREVIOUSTYPE,   Family.Transfer);
        familyMap.put(Qualifier.PREVIOUSLINE,   Family.Transfer);
        familyMap.put(Qualifier.PREVIOUSZONE,   Family.Transfer);
        familyMap.put(Qualifier.PREVIOUSSTOP,   Family.Transfer);
    }

    public static Family getFamily(Qualifier q) {
        return familyMap.get(q);
    }
}
