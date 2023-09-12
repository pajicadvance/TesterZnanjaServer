/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.pajic.form;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Predstavlja graficki korisnicki interfejs za upravljanje podacima za pristup bazi podataka.
 *
 * @author Pavle Pajic
 * @since 1.0.0
 *
 */
public class SettingsPanel extends javax.swing.JPanel {

    /**
     * Konstruktor koji vrsi pocetno kreiranje i konfigurisanje elemenata forme.
     *
     * @throws IOException - Ukoliko je otvaranje konfiguracionog fajla neuspesno.
     */
    public SettingsPanel() throws IOException {
        initComponents();
        prepareView();
    }

    /**
     * Kreira graficke komponente i postavlja i podesava njihov izgled, poziciju i ponasanje.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtURL = new javax.swing.JTextField();
        txtUsername = new javax.swing.JTextField();
        lblUrl = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        lblStatus = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();

        txtURL.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtURLKeyReleased(evt);
            }
        });

        txtUsername.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUsernameKeyReleased(evt);
            }
        });

        lblUrl.setText("URL:");

        lblUsername.setText("Username:");

        lblPassword.setText("Password:");

        btnSave.setText("Sacuvaj");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        txtPassword.setText("jPasswordField1");
        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPasswordKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUsername)
                            .addComponent(lblUrl)
                            .addComponent(lblPassword))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtURL)
                            .addComponent(txtUsername)
                            .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblStatus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSave)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtURL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUrl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUsername))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPassword)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(lblStatus))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Prikuplja podatke vezane za URL, username i password za pristup bazi i cuva ih u database.properties fajl.
     * @param evt - Predstavlja dogadjaj koji se desio nad dugmetom (klik).
     */
    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        String url = txtURL.getText();
        String user = txtUsername.getText();
        String password = String.copyValueOf(txtPassword.getPassword());
        Properties properties = new Properties();
        
        try {
            properties.load(new FileInputStream("./database.properties"));
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        
        properties.setProperty("url", url);
        properties.setProperty("username", user);
        properties.setProperty("password", password);
        
        try {
            OutputStream output = new FileOutputStream("./database.properties");
            properties.store(output, null);
            lblStatus.setText("Uspesno sacuvano");
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    
    }//GEN-LAST:event_btnSaveActionPerformed

    /**
     * Prazni status labelu nakon bilo kog unosa u tekstualno polje.
     * @param evt - Predstavlja dogadjaj koji se desio nad tekstualnim poljem (pustanje tastera).
     */
    private void txtURLKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtURLKeyReleased
        lblStatus.setText("");
    }//GEN-LAST:event_txtURLKeyReleased

    /**
     * Prazni status labelu nakon bilo kog unosa u tekstualno polje.
     * @param evt - Predstavlja dogadjaj koji se desio nad tekstualnim poljem (pustanje tastera).
     */
    private void txtUsernameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsernameKeyReleased
        lblStatus.setText("");
    }//GEN-LAST:event_txtUsernameKeyReleased

    /**
     * Prazni status labelu nakon bilo kog unosa u tekstualno polje.
     * @param evt - Predstavlja dogadjaj koji se desio nad tekstualnim poljem (pustanje tastera).
     */
    private void txtPasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyReleased
        lblStatus.setText("");
    }//GEN-LAST:event_txtPasswordKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    /**
     * Dugme za pokretanje procesa cuvanja konfuguracionog fajla za pristup bazi.
     */
    private javax.swing.JButton btnSave;
    /**
     * Labela koja opisuje tekstualno polje za unos lozinke.
     */
    private javax.swing.JLabel lblPassword;
    /**
     * Labela koja prikazuje rezultat cuvanja konfiguracionog fajla.
     */
    private javax.swing.JLabel lblStatus;
    /**
     * Labela koja opisuje tekstualno polje za unos URL parametra.
     */
    private javax.swing.JLabel lblUrl;
    /**
     * Labela koja opisuje tekstualno polje za unos korisnickog imena.
     */
    private javax.swing.JLabel lblUsername;
    /**
     * Tekstualno polje za unos lozinke.
     */
    private javax.swing.JPasswordField txtPassword;
    /**
     * Tekstualno polje za unos URL parametra.
     */
    private javax.swing.JTextField txtURL;
    /**
     * Tekstualno polje za unos korisnickog imena.
     */
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables

    /**
     * Vrsi pripremu pocetnog stanja komponenti nakon njihove inicijalizacije.
     */
    private void prepareView() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("./database.properties"));
        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");
        txtURL.setText(url);
        txtUsername.setText(username);
        txtPassword.setText(password);
    }
}
