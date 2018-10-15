package server;

import server.http.HttpResponseImpl;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.FutureTask;

public class Server {
    private ServerSocket server;
    private Integer port;

    public Server(Integer port) {
        this.port = port;
    }

    public void run() throws IOException {
        this.server = new ServerSocket(this.port);
        this.server.setSoTimeout(WebConstants.SOCKET_TIMEOUT_MILLISECONDS);

        while(true) {
            try (Socket clientSocket = this.server.accept()) {
                clientSocket.setSoTimeout(WebConstants.SOCKET_TIMEOUT_MILLISECONDS);

                ConnectionHandler connectionHandler = new ConnectionHandler(clientSocket, new RequestHandler(new HttpResponseImpl()));

                FutureTask<?> task = new FutureTask<>(connectionHandler, null);
                task.run();
                System.out.println();
            } catch (SocketTimeoutException e) {
                System.out.println("Socket Timeout Exception");
            }
        }
    }
}