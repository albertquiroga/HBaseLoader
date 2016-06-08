package com.convector.hbaseloader.output;

import com.cartodb.CartoDBClientIF;
import com.cartodb.CartoDBException;
import com.cartodb.impl.ApiKeyCartoDBClient;
import com.convector.hbaseloader.constants.cartodb.CartoDBConnectionDataManager;
import com.convector.hbaseloader.constants.cartodb.User;
import com.convector.hbaseloader.constants.sequence.CartoDBIdSequenceNumber;
import com.convector.hbaseloader.constants.values.Qualifier;
import com.convector.hbaseloader.transform.SingleRow;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Observable;

/**
 * Created by pyro_ on 19/05/2016.
 */
public class CartoDBInserter extends Outputer {

    private CartoDBIdSequenceNumber sequenceNumber;
    private CartoDBClientIF cartoDBCLient;
    private User user;
    private SingleRow currentRow;
    private CartoDBColumn[] tableColumns;
    private String query;

    public CartoDBInserter(String user) {
        this.user = User.valueOf(user);
        sequenceNumber = CartoDBIdSequenceNumber.getInstance(this.user);
        try {
            cartoDBCLient = new ApiKeyCartoDBClient(CartoDBConnectionDataManager.getUserName(this.user), CartoDBConnectionDataManager.getApiKey(this.user));
        } catch (CartoDBException e) {
            e.printStackTrace();
        }
        tableColumns = getColumns();
        System.out.println(getFormattedColumnNames());
    }

    @Override
    public void update(Observable o, Object arg) {
        currentRow = (SingleRow) o;
        insert();
    }

    private void insert() {
        query = "INSERT INTO " + CartoDBConnectionDataManager.getTableName(this.user) + " (" + getFormattedColumnNames() + ") VALUES (" + getFormattedValues() + ");";
        System.out.println(query);
        try {
            cartoDBCLient.executeQuery(query);
        } catch (CartoDBException e) {
            e.printStackTrace();
        }
    }

    private CartoDBColumn[] getColumns() {
        String result = null;
        try {
            result = cartoDBCLient.executeQuery("SELECT column_name, data_type, ordinal_position, is_nullable FROM information_schema.columns WHERE table_name = '"+CartoDBConnectionDataManager.getTableName(this.user)+"'");
        } catch (CartoDBException e) {
            e.printStackTrace();
        }
        JSONArray array = new JSONObject(result).getJSONArray("rows");
        CartoDBColumn[] columns = new CartoDBColumn[array.length()];
        for (int i = 0; i < array.length(); i++) {
            columns[i] = new CartoDBColumn(array.getJSONObject(i).get("column_name").toString(), array.getJSONObject(i).get("data_type").toString(), array.getJSONObject(i).get("ordinal_position").toString(), array.getJSONObject(i).get("is_nullable").toString());
        }
        return columns;
    }

    private String getFormattedColumnNames() {
        String str = "";
        for(CartoDBColumn c : tableColumns) str += c.column_name + ",";
        return str.substring(0,str.lastIndexOf(","));
    }

    private String getFormattedValues() {
        String str = "";
        for(CartoDBColumn c : tableColumns) {
            switch(c.column_name){
                case "_order":                  str += formatValue(currentRow.getValue(Qualifier.TURN),c.data_type);
                    break;
                case "cartodb_id":              str += formatValue(sequenceNumber.getNumber().toString(),c.data_type);
                    break;
                case "the_geom":                str += "ST_SetSRID(ST_Point(-110, 43),4326),";
                    break;
                case "the_geom_webmercator":    str += "ST_SetSRID(ST_Point(-110, 43),3857),";
                    break;
                case "date_validation":         str += "TO_DATE('" + currentRow.getTimestamp().toString() + "','YYYY-MM-DD HH24:MI:SS.MS'),";
                    break;
                case "num_validations":         str += "1,";
                    break;
                default:                        str += formatValue(currentRow.getValue(Qualifier.valueOf(c.column_name.toUpperCase())),c.data_type);
            }
        }
        return str.substring(0,str.lastIndexOf(","));
    }

    private String formatValue(String str2, String columnType) {
        //Posa cometes simples si es un camp de tipus text, no les posa si es qualsevol altre tipus (number, date, geo)
        if(columnType.equals("text")){
            return "'" + str2 + "',";
        } else {
            return str2 + ",";
        }
    }
}