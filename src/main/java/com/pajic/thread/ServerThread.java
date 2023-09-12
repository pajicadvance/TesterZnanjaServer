/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pajic.thread;

import com.pajic.communication.Request;
import com.pajic.communication.Response;
import com.pajic.controller.Controller;
import com.pajic.model.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pavle
 */
public class ServerThread extends Thread {
    
    private final Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private final int clientNumber;
    private List<ServerThread> serverThreadList;
    
    public ServerThread(Socket socket, int clientNumber, List<ServerThread> serverThreadList) {
        this.socket = socket;
        this.clientNumber = clientNumber;
        this.serverThreadList = serverThreadList;
    }
    
    private void handleRequest() throws ClassNotFoundException, IOException {
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        Response response = new Response();
        while (!interrupted()) {
            try {
                System.out.println("[Server Thread " + clientNumber + "] Awaiting request from client " + clientNumber + "...");
                Request request = (Request) in.readObject();
                System.out.println("[Server Thread " + clientNumber + "] Request received from client " + clientNumber + "!");
                response = new Response();
                switch (request.getOperation()) {
                    case LOGIN -> {
                        System.out.println("[Server Thread " + clientNumber + "] Operation: LOGIN");
                        String[] credentials = (String[]) request.getData();
                        GenericUser responseUser = Controller.getInstance().login(credentials[0], credentials[1]);
                        response.setData(responseUser);
                    }
                    case GET_ALL_TEST_ZNANJA -> {
                        System.out.println("[Server Thread " + clientNumber + "] Operation: GET_ALL_TEST_ZNANJA");
                        response.setData(Controller.getInstance().ucitajListuTestovaZnanja());
                    }
                    case ADD_TEST_ZNANJA -> {
                        System.out.println("[Server Thread " + clientNumber + "] Operation: ADD_TEST_ZNANJA");
                        Controller.getInstance().zapamtiTestZnanja((TestZnanja) request.getData());
                    }
                    case GET_TEST_ZNANJA -> {
                    }
                    case DELETE_TEST_ZNANJA -> {
                        System.out.println("[Server Thread " + clientNumber + "] Operation: DELETE_TEST_ZNANJA");
                        Controller.getInstance().obrisiTestZnanja((TestZnanja) request.getData());
                    }
                    case FIND_TEST_ZNANJA -> {
                        System.out.println("[Server Thread " + clientNumber + "] Operation: FIND_TEST_ZNANJA");
                        response.setData(Controller.getInstance().nadjiTestoveZnanja((String) request.getData()));
                    }
                    case GET_ALL_UREDNIK -> {
                        System.out.println("[Server Thread " + clientNumber + "] Operation: GET_ALL_UREDNIK");
                        response.setData(Controller.getInstance().ucitajListuUrednika());
                    }
                    case ADD_UREDNIK -> { 
                        System.out.println("[Server Thread " + clientNumber + "] Operation: ADD_UREDNIK");
                        Controller.getInstance().zapamtiUrednika((Urednik) request.getData());
                    }
                    case GET_UREDNIK -> {
                    }
                    case DELETE_UREDNIK -> {
                        System.out.println("[Server Thread " + clientNumber + "] Operation: DELETE_UREDNIK");
                        Controller.getInstance().obrisiUrednika((Urednik) request.getData());
                    }
                    case FIND_UREDNIK -> {
                        System.out.println("[Server Thread " + clientNumber + "] Operation: FIND_UREDNIK");
                        response.setData(Controller.getInstance().nadjiUrednike((String) request.getData()));
                    }
                    case ADD_REZULTAT -> {
                        System.out.println("[Server Thread " + clientNumber + "] Operation: GENEARATE_REZULTAT");
                        Controller.getInstance().odrediRezultat((Rezultat) request.getData());
                    }
                    case ADD_KORISNIK -> {
                        System.out.println("[Server Thread " + clientNumber + "] Operation: ADD_KORISNIK");
                        Controller.getInstance().zapamtiKorisnika((Korisnik) request.getData());
                    }
                    case FIND_REZULTAT -> {
                        System.out.println("[Server Thread " + clientNumber + "] Operation: FIND_REZULTAT");
                        response.setData(Controller.getInstance().nadjiRezultat((String) request.getData()));
                    }
                }
                out.writeObject(response);
                System.out.println("[Server Thread " + clientNumber + "] Sent response to client " + clientNumber + ": " + response.getData());
                out.flush();
            } catch (SocketException sx) {
                System.out.println("[Server Thread " + clientNumber + "] Client " + clientNumber + " disconnected.");
                interrupt();
            } catch (Exception ex) {
                response.setException(ex);
                out.writeObject(response);
            }
        }
        close();
    }

    private void close() throws IOException {
        out.close();
        in.close();
        serverThreadList.remove(this);
        System.out.println("[Server Thread " + clientNumber + "] Server thread for client " + clientNumber + " stopped.");
    }

    @Override
    public void run() {
        System.out.println("[Server Thread " + clientNumber + "] Started new server thread for client " + clientNumber + ".");
        try {
            handleRequest();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
