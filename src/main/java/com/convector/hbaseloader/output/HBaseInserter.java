package com.convector.hbaseloader.output;

import com.convector.hbaseloader.constants.maps.QualifierToFamily;
import com.convector.hbaseloader.constants.values.Qualifier;
import com.convector.hbaseloader.transform.SingleRow;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.BufferedMutator;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by pyro_ on 08/05/2016.
 */
public class HBaseInserter extends Outputer {

    private String tableName;
    private Configuration config;
    private Connection connection;
    private BufferedMutator buffer;

    private Put put;

    protected HBaseInserter(String tableName) {
        this.tableName = tableName;
        config = HBaseConfiguration.create();
        connect();
    }

    private void connect() {
        try {
            connection = ConnectionFactory.createConnection(config);
            buffer = connection.getBufferedMutator(TableName.valueOf(tableName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createPut(SingleRow r) {
        put = new Put(r.getRowKey().getBytes());
        for (Qualifier q : r.getQualifiers()){
            put.addColumn(QualifierToFamily.getFamily(q).toString().getBytes(), q.toString().getBytes(), r.getTimestamp().getTime(), r.getValue(q).getBytes());
        }
    }

    private void insertPut(){
        try {
            buffer.mutate(put);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            System.out.print("Closing... ");
            buffer.flush();
            buffer.close();
            connection.close();
            System.out.println("All closed.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(o.toString());
        createPut((SingleRow) o);
        insertPut();
    }
}