/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pajic.main;

import com.pajic.form.MainFrame;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Pavle
 */
public class Main {
    public static void main(String[] args) throws IOException {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}
