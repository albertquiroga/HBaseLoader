package com.convector.hbaseloader.transform;

import com.convector.hbaseloader.constants.maps.QualifierToFamily;
import com.convector.hbaseloader.constants.values.Qualifier;

import java.sql.Timestamp;
import java.util.EnumMap;
import java.util.Observable;
import java.util.Set;

/**
 * Created by pyro_ on 18/05/2016.
 */
public class SingleRow extends Observable {

    private String rowKey;
    private Timestamp timestamp;
    private EnumMap<Qualifier,String> values;
    private double currentRowProgress;

    private static SingleRow ourInstance = new SingleRow();

    public static SingleRow getInstance() {
        return ourInstance;
    }

    private SingleRow() {
        values = new EnumMap<Qualifier, String>(Qualifier.class);
    }

    public void setRow(double currentRowProgress, String rowKey, Timestamp timestamp, EnumMap<Qualifier,String> values) {
        this.currentRowProgress = currentRowProgress;
        this.rowKey = rowKey;
        this.timestamp = timestamp;
        this.values = values;
        setChanged();
        notifyObservers(currentRowProgress);
    }

    public String getRowKey() {
        return rowKey;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getValue(Qualifier q) {
        return values.get(q);
    }

    public Set<Qualifier> getQualifiers () {
        return values.keySet();
    }

    public String toString(){
        return rowKey + "||" + timestamp + "||" + valuesToString();
    }

    private String valuesToString() {
        String str = "";
        for (Qualifier q : values.keySet()) {
            str += QualifierToFamily.getFamily((Qualifier) q) + ":" + q.toString() + ":" + values.get(q) + "|";
        }
        return str;
    }

}
