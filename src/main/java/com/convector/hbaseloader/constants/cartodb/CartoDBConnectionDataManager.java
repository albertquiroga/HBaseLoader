package com.convector.hbaseloader.constants.cartodb;

import java.util.EnumMap;

/**
 * Created by pyro_ on 19/05/2016.
 */
public abstract class CartoDBConnectionDataManager {

    private static final String pyroUserName    = "pyrospade";
    private static final String pyroApiKey      = "20577222cc4aee7b8a56807698407fabc6690cde";
    private static final String pyroTableName   = "convectortest";

    private static final String carlesUserName  = "cteixidogalvez";
    private static final String carlesApiKey    = "7977f5eaebeb6279ca4b9224a1f2988f14ee1824";
    private static final String carlesTableName = "validacions_online";

    private static EnumMap<User,String> userNameMap;
    private static EnumMap<User,String> apiKeyMap;
    private static EnumMap<User,String> tableNameMap;

    static {
        userNameMap     = new EnumMap<>(User.class);
        userNameMap.put(User.PYRO,      pyroUserName);
        userNameMap.put(User.CARLES,    carlesUserName);

        apiKeyMap       = new EnumMap<>(User.class);
        apiKeyMap.put(User.PYRO,        pyroApiKey);
        apiKeyMap.put(User.CARLES,      carlesApiKey);

        tableNameMap    = new EnumMap<>(User.class);
        tableNameMap.put(User.PYRO,     pyroTableName);
        tableNameMap.put(User.CARLES,   carlesTableName);
    }

    public static String getUserName(User user) {
        return userNameMap.get(user);
    }

    public static String getApiKey(User user) {
        return apiKeyMap.get(user);
    }

    public static String getTableName(User user) {
        return tableNameMap.get(user);
    }

}