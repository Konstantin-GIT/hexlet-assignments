package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Connected implements Connection {

    private TcpConnection tcpConnection;

    public Connected(TcpConnection tcpConnection) {
        this.tcpConnection = tcpConnection;
    }

    public void connect() {
        System.out.println("Error! Connection already connected");

    }

    public void disconnect() {
        System.out.println("Connection is broken.");
        this.tcpConnection.setCurrentState(new Disconnected(this.tcpConnection));
    }

    public void write(String data) {
        System.out.println("The data is entered into the buffer.");
    }

    public String toString() {
        return "connected";
    }
}
// END
