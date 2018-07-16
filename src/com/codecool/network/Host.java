package com.codecool.network;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Host {
    private InetAddress servAddress = null;
    private final int port = 2101;
    private String message;

    public Host() {
        try {
            servAddress = InetAddress.getLocalHost();
            this.start();
        } catch (UnknownHostException e) {
            message = "The host is unknown.";
        }
    }

    public String getServerAddress() {
        return servAddress.getHostAddress();

    }

    private void start() {
        message = "Server IP address : " + servAddress.getHostAddress() + "\nport: " + port;
        Runnable serverTask = () -> {
            try {
                ServerSocket server = new ServerSocket(port);
                while (true) {                                              // infinite for testing
                    Socket sSocket = server.accept();
                    ObjectOutputStream outToClient = new ObjectOutputStream(sSocket.getOutputStream());
                    ObjectInputStream inFromClient = new ObjectInputStream(sSocket.getInputStream());
                    outToClient.writeObject(inFromClient.readObject()); // sending back the data as is right now
                    sSocket.close();
                }

                //server.close();  temporary not used

            } catch (IOException e) {
                message = "Error during processing client requests";
            } catch (NullPointerException n) {
                message = "No server ip established";
            } catch (ClassNotFoundException e) {
                message = "Error, no class found, might did not get data from the client";
            }
        };
        Thread serverThread = new Thread(serverTask);
        serverThread.start();
    }

    public String getMessage() {
        return message;
    }
}

