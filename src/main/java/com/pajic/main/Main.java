/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pajic.main;

import com.pajic.form.MainFrame;

import java.io.IOException;

/**
 * Main klasa projekta TesterZnanjaServer.
 *
 * Sadrzi main metodu.
 *
 * @author Pavle Pajic
 * @since 1.0.0
 */
public class Main {
    /**
     * Entrypoint serverske aplikacije.
     *
     * Inicijalizuje glavnu serversku formu i prikazuje je.
     *
     * @param args - Argumenti pri pokretanju aplikacije iz komandne linije (neiskorisceno).
     */
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }
}
