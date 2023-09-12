/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pajic.operation.rezultat;


import com.pajic.model.Rezultat;
import com.pajic.operation.AbstractGenericOperation;

/**
 *
 * @author pajic
 */
public class AddRezultat extends AbstractGenericOperation {

    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        repository.add((Rezultat) param);
    }
    
}
