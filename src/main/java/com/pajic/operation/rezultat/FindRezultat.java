/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pajic.operation.rezultat;

import com.pajic.model.Rezultat;
import com.pajic.operation.AbstractGenericOperation;

import java.util.List;


/**
 *
 * @author pajic
 */
public class FindRezultat extends AbstractGenericOperation {

    private List<Rezultat> rezultati;
    private final String searchParameter;
    
    public FindRezultat(String searchParameter) {
        this.searchParameter = searchParameter;
    }
    
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        rezultati = repository.findAll((Rezultat) param, searchParameter);
    }
    
    public List<Rezultat> getRezultati() {
        return rezultati;
    }
}
