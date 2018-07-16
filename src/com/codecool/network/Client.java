package com.codecool.network;

import com.codecool.api.Card;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private final String serverAddress;
    private final int port = 2101;

    public Client(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public void run(){
        String clientIp;
        try{
            InetAddress address = InetAddress.getLocalHost();
            clientIp = address.getHostAddress();
            System.out.println("client ip address: " + clientIp);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        Socket client = null;
        try {
            Card test = new Card("nameOk", 0, 0, 0, "sampleURL");
            client = new Socket(serverAddress, port);
            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
            out.writeObject(test);
            ObjectInputStream in = new ObjectInputStream(client.getInputStream());
            Card obj = (Card) in.readObject();
            System.out.println(obj.getName());

            client.close();
        }
        catch (NotSerializableException s){
            System.err.println("Object is not serializable");
        } catch (IOException i) {
            System.err.println("Failed to connect or no server is running!");;
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found, might not got data fro mthe server");
        }


    }

}
