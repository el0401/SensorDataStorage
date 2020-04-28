package sensorData;

import streamMachine.SensorDataStorage;
import transmission.DataConnection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class SensorDataReceiver {
    private final DataConnection connection;
    private final SensorDataStorage storage;
    public String name;
    public long time;
    public float[] value;

    public SensorDataReceiver(DataConnection connection, SensorDataStorage storage) {
        this.connection = connection;
        this.storage = storage;
    }

    SensorDataStorage getStorage() {
        return storage;
    }

    public void recieveData() throws IOException {
        DataInputStream dis = connection.getDataInputStream();
        this.name = dis.readUTF();
        this.time = dis.readLong();
        int length = dis.readInt();
        for (int i = 0; i < length; i++) {
            this.value[i] = dis.readFloat();
        }
        dis.close();
    }
}