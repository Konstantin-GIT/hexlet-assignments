package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Disconnected implements Connection {

    private TcpConnection tcpConnection;
    public Disconnected(TcpConnection tcpConnection) {
        this.tcpConnection = tcpConnection;
    }

    public void connect() {
        System.out.println("The connection is established.");
        this.tcpConnection.setCurrentState(new Connected(this.tcpConnection));
    }

    public void disconnect() {
        System.out.println("Error! Connection already disconnected");
    }

    public void write(String data) {
        System.out.println("Error! Not connection");
    }

    public String toString() {
        return "disconnected";
    }
}
// END
