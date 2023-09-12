/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pajic.thread;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pavle
 */
public class ServerThreadManager extends Thread {
    
    private Socket socket;
    private final ServerSocket ss;
    private List<ServerThread> serverThreadList;
    private int clientCounter;
    
    public ServerThreadManager(ServerSocket ss, Socket socket) {
        this.socket = socket;
        this.ss = ss;
        this.serverThreadList = new ArrayList<>();
        this.clientCounter = 0;
    }
    
    @Override
    public void run() {
        System.out.println("[Server Thread Manager] Server thread manager started.");
        while (!interrupted()) {
            try {
                System.out.println("[Server Thread Manager] Waiting on new client...");
                socket = ss.accept();
                clientCounter++;
                System.out.println("[Server Thread Manager] New client connected. Assigned client number " + clientCounter + ".");
                ServerThread server = new ServerThread(socket, clientCounter, serverThreadList);
                server.start();
                serverThreadList.add(server);
            } catch (SocketException se) {
                System.out.println("[Server Thread Manager] Server stopped, stopping all server threads.");
            } catch (Exception ex) {
                Logger.getLogger(ServerThreadManager.class.getName()).log(Level.SEVERE, null, ex);
                break;
            }
        }
        stopServers();
    }
    
    public void stopServers() {
        for (ServerThread s : serverThreadList) if (s.isAlive()) s.interrupt();
        System.out.println("[Server Thread Manager] All threads closed.");
    }
}
