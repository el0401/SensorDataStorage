package sensorData;

import streamMachine.PersistenceException;
import streamMachine.SensorDataStorage;
import streamMachine.SensorDataStorageImpl;
import org.junit.Assert;
import org.junit.Test;
import transmission.DataConnection;
import transmission.DataConnector;

import java.io.IOException;

public class SensorDataTransmissionTests {
    private static final int PORTNUMBER = 9876;

    @Test
    public void gutTransmissionTest() throws IOException, PersistenceException {
        // create example data set
        String sensorName = "MyGoodOldSensor"; // does not change
        long timeStamp = System.currentTimeMillis();
        float[] valueSet = new float[3];
        valueSet[0] = (float) 0.7;
        valueSet[1] = (float) 1.2;
        valueSet[2] = (float) 2.1;

        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        //                                              receiver side                                        //
        ///////////////////////////////////////////////////////////////////////////////////////////////////////

        // create storage
        // TODO: create object that implements SensorDataStorage
        SensorDataStorage dataStorage = new SensorDataStorageImpl("sensor");

        // create connections
        DataConnection receiverConnection = new DataConnector(8888);

        // create receiver
        SensorDataReceiver sensorDataReceiver = new SensorDataReceiver(receiverConnection, dataStorage);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        //                                              sender side                                          //
        ///////////////////////////////////////////////////////////////////////////////////////////////////////

        // create connections
        DataConnection senderConnection = new DataConnector("localhost", 8888);

        // create sender
        SensorDataSender sensorDataSender = new SensorDataSender(senderConnection);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        //                               execute communication and test                                      //
        ///////////////////////////////////////////////////////////////////////////////////////////////////////

        // send data with TCP
        sensorDataSender.sendData(sensorName, timeStamp, valueSet);

        // test if stored
        SensorDataStorage dataStorageReceived = sensorDataReceiver.getStorage();

        SensorDataStorage storage = sensorDataReceiver.getStorage();
        storage.saveData(sensorDataReceiver.time,sensorDataReceiver.value);

        String sensorNameReceived = sensorDataReceiver.name;
        long timeStampReceived = sensorDataReceiver.time;
        float[] valueSetReceived = sensorDataReceiver.value;

        // test
        Assert.assertEquals(sensorName, sensorNameReceived);
        Assert.assertEquals(timeStamp, timeStampReceived);
        Assert.assertEquals((float) 0.7, valueSetReceived[0],0);
        Assert.assertEquals((float) 1.2, valueSetReceived[1],0);
        Assert.assertEquals((float) 2.1, valueSetReceived[2],0);
    }
}