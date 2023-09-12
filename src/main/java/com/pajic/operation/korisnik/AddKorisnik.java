/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pajic.operation.korisnik;


import com.pajic.model.Korisnik;
import com.pajic.operation.AbstractGenericOperation;

/**
 *
 * @author pajic
 */
public class AddKorisnik extends AbstractGenericOperation {

    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.add((Korisnik) param);
    }
    
}
