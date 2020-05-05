package sensorData;

import streamMachine.PersistenceException;
import streamMachine.SensorDataStorage;
import transmission.DataConnection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class SensorDataReceiver implements Runnable {
    private final DataConnection connection;
    private final SensorDataStorage storage;


    public SensorDataReceiver(DataConnection connection, SensorDataStorage storage) {
        this.connection = connection;
        this.storage = storage;
    }

    SensorDataStorage getStorage() {
        return storage;
    }

    public void recieveData() throws IOException, PersistenceException {
        DataInputStream dis = connection.getDataInputStream();
        String name = dis.readUTF();
        Long time = dis.readLong();
        int length = dis.readInt();
        float values[] = new float[length];
        for (int i = 0; i < length; i++) {
            values[i] = dis.readFloat();
        }
        dis.close();
        this.storage.saveData(time, values);
    }

    @Override
    public void run() {
        try {
            recieveData();
            Thread.sleep(10);
        } catch (IOException | PersistenceException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}