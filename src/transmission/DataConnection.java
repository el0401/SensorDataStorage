package transmission;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public interface DataConnection extends Runnable {
    DataInputStream getDataInputStream() throws IOException;
    DataOutputStream getDataOutputStream() throws IOException;
}