package com.convector.hbaseloader.constants.sequence;

import com.cartodb.CartoDBClientIF;
import com.cartodb.CartoDBException;
import com.cartodb.impl.ApiKeyCartoDBClient;
import com.convector.hbaseloader.constants.cartodb.CartoDBConnectionDataManager;
import com.convector.hbaseloader.constants.cartodb.User;
import org.json.JSONObject;

/**
 * Created by pyro_ on 19/05/2016.
 */
public class CartoDBIdSequenceNumber {

    private CartoDBClientIF cartoDBCLient;
    private Integer number;
    private User user;

    public static CartoDBIdSequenceNumber getInstance(User u) {
        return new CartoDBIdSequenceNumber(u);
    }

    private CartoDBIdSequenceNumber(User u) {
        this.user = u;
        try{
            cartoDBCLient = new ApiKeyCartoDBClient(CartoDBConnectionDataManager.getUserName(user), CartoDBConnectionDataManager.getApiKey(user));
        } catch (CartoDBException e) {
            e.printStackTrace();
        }
        this.number = count()+1;
    }

    private int count() {
        String result = null;
        try {
            result = cartoDBCLient.executeQuery("SELECT max(cartodb_id) FROM " + CartoDBConnectionDataManager.getTableName(user) + ";");
        } catch (CartoDBException e) {
            e.printStackTrace();
        }
        String jsonresult = new JSONObject(result).getJSONArray("rows").getJSONObject(0).get("max").toString();
        return jsonresult.equals("null") ? 0 : Integer.parseInt(jsonresult);
    }

    public Integer getNumber() {
        return number++;
    }

}
