package com.convector.hbaseloader.output;

/**
 * Created by pyro_ on 19/05/2016.
 */
public class CartoDBColumn {

    String column_name;
    String data_type;
    int ordinal_position;
    boolean is_nullable;

    protected CartoDBColumn(String column_name, String data_type, String ordinal_position, String is_nullable) {
        this.column_name = column_name;
        this.data_type = data_type;
        this.ordinal_position = Integer.parseInt(ordinal_position);
        this.is_nullable = is_nullable.equals("YES");
    }

    public String toString() {
        return column_name + ":" + data_type + ":" + ordinal_position + ":" + is_nullable;
    }

}
