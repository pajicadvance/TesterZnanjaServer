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
 * Predstavlja menadzera serverskih niti koji inicijalizuje i pokrece novu serversku nit za svakog novog povezanog klijenta.
 *
 * Sadrzi podatak o soketu, serverski soket, listu trenutno aktivnih serverskih niti i brojac povezanih klijenata.
 *
 * @author Pavle Pajic
 * @since 1.0.0
 */
public class ServerThreadManager extends Thread {

    /**
     * Soket preko koga ce se odvijati komunikacija izmedju servera i klijenta.
     */
    private Socket socket;
    /**
     * Serverski soket koji osluskuje odredjeni port zarad uspostavljanja konekcije sa klijentskim aplikacijama.
     */
    private final ServerSocket ss;
    /**
     * Lista svih trenutno pokrenutih niti.
     */
    private List<ServerThread> serverThreadList;
    /**
     * Brojac povezanih klijenata.
     */
    private int clientCounter;

    /**
     * Konstruktor koji inicijalizuje menadzer serverskih niti.
     * @param ss - Serverski soket.
     * @param socket - Podatak o soketu.
     */
    public ServerThreadManager(ServerSocket ss, Socket socket) {
        this.socket = socket;
        this.ss = ss;
        this.serverThreadList = new ArrayList<>();
        this.clientCounter = 0;
    }

    /**
     * Pokrece nit menadzera serverskih niti. Za svakog povezanog klijenta kreira se nova serverska nit, stavlja se u listu aktivnih serverskih niti, i povecava se brojac povezanih klijenata.
     */
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

    /**
     * Zaustavlja sve aktivne serverske niti interrupt() pozivom, nakon sto je sam menadzer serverskih niti prekinut interrupt() pozivom.
     */
    public void stopServers() {
        for (ServerThread s : serverThreadList) if (s.isAlive()) s.interrupt();
        System.out.println("[Server Thread Manager] All threads closed.");
    }
}
