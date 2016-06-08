package com.convector.hbaseloader.constants.sequence;

import java.io.*;

/**
 * Created by pyro_ on 19/05/2016.
 */
public class RowKeySequenceNumber {

    private static final String fileName = "hbaseseqnum.ser";
    private Integer number;

    private static RowKeySequenceNumber ourInstance = new RowKeySequenceNumber("hbaseseqnum.ser");

    public static RowKeySequenceNumber getInstance() {
        return ourInstance;
    }

    private RowKeySequenceNumber(String fileName) {
        number = fileExists(fileName) ? readFile(fileName) : 0;
    }

    private boolean fileExists(String file) {
        File f = new File(file);
        return f.exists() && !f.isDirectory();
    }

    private Integer readFile(String fileName) {
        ObjectInputStream in = null;
        Integer num = -1;
        try {
            in = new ObjectInputStream(new FileInputStream(fileName));
            num = (int) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return num;
    }

    private void writeFile(Integer number) {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(number);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int getNumber() {
        return number++;
    }

    public void close() {
        writeFile(number);
    }

}

