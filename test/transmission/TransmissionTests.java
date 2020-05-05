package transmission;

import org.junit.Assert;
import org.junit.Test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class TransmissionTests {
    private static final int PORTNUMBER = 9876;
    private static final int TEST_INT = 42;

    @Test
    public void gutConnectionTest1() throws IOException, InterruptedException {
        // open server side
        DataConnection serverSide = new DataConnector("localhost",PORTNUMBER, true);
        Thread t1 = new Thread(serverSide);
        t1.start();

        // open client side
        DataConnection clientSide = new DataConnector("localhost", PORTNUMBER, false);
        Thread t2 = new Thread(clientSide);
        t2.start();

        Thread.sleep(100);

        DataOutputStream dataOutputStream = clientSide.getDataOutputStream();
        dataOutputStream.writeInt(TEST_INT);

        DataInputStream dataInputStream = serverSide.getDataInputStream();
        int readValue = dataInputStream.readInt();

        Assert.assertEquals(TEST_INT, readValue);
    }


}