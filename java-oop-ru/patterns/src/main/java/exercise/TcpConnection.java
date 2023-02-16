package exercise;
import exercise.connections.Connected;
import exercise.connections.Connection;
import exercise.connections.Disconnected;

import java.util.List;
import java.util.ArrayList;

// BEGIN
public class TcpConnection {

    private String ip;
    private int port;

    private String buffer;

    private Connection currentState = new Disconnected(this);

    TcpConnection(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public String getCurrentState() {
        return this.currentState.toString();
    }

    public void setCurrentState(Connection connection) {
        this.currentState = connection;
    }

    public void connect() {
        currentState.connect();
        this.currentState = new Connected(this);
    }

    public void disconnect() {
        currentState.disconnect();
    }

    public void write(String data) {
        currentState.write(data);
        this.buffer = buffer + " " + data;
    }

}
// END
