package transmission;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class DataConnector implements DataConnection {
    private Socket socket;
    private int port;
    private ServerSocket server;
    private final boolean isServer;
    private final String address;

    /**
     * Create client side - open connection to address / port
     * @param address
     */
    public DataConnector(String address, int port, boolean isServer) throws IOException {
        this.address = address;
        this.port = port;
        this.isServer = isServer;
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

    @Override
    public void run() {
        try {
            if (isServer) {
                ServerSocket server = new ServerSocket(this.port);
                this.socket = server.accept();
                System.out.println("Client  " + this.socket.getRemoteSocketAddress()
                        + "  connected successfully.");
            } else {
                this.socket = new Socket(this.address, this.port);
                System.out.println("Client created.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}