/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.pajic.form;

import com.pajic.thread.ServerThreadManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JDialog;

/**
 * Predstavlja graficki korisnicki interfejs za pokretanje i zaustavljanje servera, kao i pristup formi za upravljanje podacima za pristup bazi podatka.
 *
 * Sadrzi serverski soket i instancu menadzera serverskih niti, radi pokretanja servera.
 *
 * @author Pavle Pajic
 * @since 1.0.0
 *
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Serverski soket koji osluskuje odredjeni port zarad uspostavljanja konekcije sa klijentskim aplikacijama.
     */
    private ServerSocket ss;
    /**
     * Instanca menadzera serverskih niti.
     */
    private ServerThreadManager stm;
    /**
     * Konstruktor koji vrsi pocetno kreiranje i konfigurisanje elemenata forme.
     */
    public MainFrame() {
        initComponents();
        prepareView();
    }

    /**
     * Kreira graficke komponente i postavlja i podesava njihov izgled, poziciju i ponasanje.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnStopServer = new javax.swing.JButton();
        btnStartServer = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        menuServer = new javax.swing.JMenu();
        menuItemSettings = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnStopServer.setText("Zaustavi server");
        btnStopServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopServerActionPerformed(evt);
            }
        });

        btnStartServer.setText("Pokreni server");
        btnStartServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartServerActionPerformed(evt);
            }
        });

        menuServer.setText("Server");

        menuItemSettings.setText("Podesavanja");
        menuItemSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemSettingsActionPerformed(evt);
            }
        });
        menuServer.add(menuItemSettings);

        menuBar.add(menuServer);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnStartServer, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnStopServer, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStartServer, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnStopServer, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Pokrece menadzer serverskih niti.
     * @param evt - Predstavlja dogadjaj koji se desio nad dugmetom (klik).
     */
    private void btnStartServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartServerActionPerformed
        try {
            ss = new ServerSocket(9000);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        stm = new ServerThreadManager(ss, new Socket());
        stm.start();
        btnStartServer.setEnabled(false);
        btnStopServer.setEnabled(true);
        System.out.println("[Server UI] Server started.");
    }//GEN-LAST:event_btnStartServerActionPerformed

    /**
     * Zaustavlja menadzer serverskih niti.
     * @param evt - Predstavlja dogadjaj koji se desio nad dugmetom (klik).
     */
    private void btnStopServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopServerActionPerformed
        stm.interrupt();
        try {
            ss.close();
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        btnStartServer.setEnabled(true);
        btnStopServer.setEnabled(false);
        System.out.println("[Server UI] Server stopped.");
    }//GEN-LAST:event_btnStopServerActionPerformed

    /**
     * Otvara formu za podesavanje parametara za pristup bazi podataka kada se klikne na odgovarajuce dugme.
     * @param evt - Predstavlja dogadjaj koji se desio nad stavkom padajuceg menija (klik).
     */
    private void menuItemSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemSettingsActionPerformed
        JDialog dialog = new JDialog(this, true);
        try {
            dialog.add(new SettingsPanel());
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        dialog.setLocationRelativeTo(null);
        dialog.pack();
        dialog.setVisible(true);
    }//GEN-LAST:event_menuItemSettingsActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    /**
     * Dugme za pokretanje menadzera serverskih niti.
     */
    private javax.swing.JButton btnStartServer;
    /**
     * Dugme za zaustavljanje menadzera serverskih niti.
     */
    private javax.swing.JButton btnStopServer;
    /**
     * Glavni meni u okviru prozora.
     */
    private javax.swing.JMenuBar menuBar;
    /**
     * Stavka padajuceg menija za pristup formi za podesavanje pristupnih parametara za bazu podataka.
     */
    private javax.swing.JMenuItem menuItemSettings;
    /**
     * Stavka glavnog menija za otvaranje padajuceg menija koji sadrzi opcije vezane za server.
     */
    private javax.swing.JMenu menuServer;
    // End of variables declaration//GEN-END:variables

    /**
     * Vrsi pripremu pocetnog stanja komponenti nakon njihove inicijalizacije.
     */
    private void prepareView() {
        btnStopServer.setEnabled(false);
    }
}
