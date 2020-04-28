package transmission;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class DataConnector implements DataConnection {
    private final Socket socket;
    private int port;
    private ServerSocket server;

    /**
     * Create client side - open connection to address / port
     * @param address
     */
    public DataConnector(String address, int port) throws IOException {
        socket = new Socket(address,port);

        System.out.println("successfully connected to: "+address+":"+"port");
    }

    /**
     * Create server side - open port on this port and wait for one client
     * @param port
     */
    public DataConnector(int port) throws IOException {
        server = new ServerSocket(port);
        socket = server.accept();

        System.out.println("connection successfully established");
    }

    @Override
    public DataInputStream getDataInputStream() throws IOException {
        InputStream is = socket.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        return dis;
    }

    @Override
    public DataOutputStream getDataOutputStream() throws IOException {
        OutputStream os = socket.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        return dos;
    }
}