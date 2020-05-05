package sensorData;

import streamMachine.PersistenceException;
import streamMachine.SensorDataSet;
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
    public void gutTransmissionTest() throws IOException, PersistenceException, InterruptedException {
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
        SensorDataStorage dataStorage = new SensorDataStorageImpl(sensorName);

        dataStorage.clean();       // needed so that no false data will be read out in "Received"

        // create connections
        DataConnection receiverConnection = new DataConnector("localhost", PORTNUMBER, true);
        Thread t1 = new Thread(receiverConnection);
        t1.start();

        // create receiver
        SensorDataReceiver receiver = new SensorDataReceiver(receiverConnection, dataStorage);
        Thread t3 = new Thread(receiver);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////
        //                                              sender side                                          //
        ///////////////////////////////////////////////////////////////////////////////////////////////////////

        // create connections
        DataConnection senderConnection = new DataConnector("localhost", PORTNUMBER, false);
        Thread t2 = new Thread(senderConnection);
        t2.start();
        Thread.sleep(100);

        // create sender
        SensorDataSender sender = new SensorDataSender(senderConnection);


        // send data with TCP
        sender.sendData(sensorName, timeStamp, valueSet);
        //receiver.readDataSet();
        t3.start();

        // test if stored
        SensorDataStorage dataStorageReceived = receiver.getStorage();

        SensorDataSet sd = dataStorageReceived.getDataSet(0);

        String sensorNameReceived = sd.getName();
        long timeStampReceived = sd.getTime();
        float[] valueSetReceived = sd.getValues();


        // test
        Assert.assertEquals(sensorName, sensorNameReceived);
        Assert.assertEquals(timeStamp, timeStampReceived);
        Assert.assertArrayEquals(valueSet, valueSetReceived, 0);
    }
}