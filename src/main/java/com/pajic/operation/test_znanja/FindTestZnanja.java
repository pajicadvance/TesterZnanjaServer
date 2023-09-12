/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pajic.operation.test_znanja;

import com.pajic.model.Odgovor;
import com.pajic.model.Pitanje;
import com.pajic.model.TestZnanja;
import com.pajic.operation.AbstractGenericOperation;

import java.util.List;


/**
 *
 * @author pajic
 */
public class FindTestZnanja extends AbstractGenericOperation {

    private List<TestZnanja> testoviZnanja;
    private final String searchParameter;

    public FindTestZnanja(String searchParameter) {
        this.searchParameter = searchParameter;
    }
    
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        testoviZnanja = repository.findAll((TestZnanja) param, searchParameter);
        for (TestZnanja tz : testoviZnanja) {
            List<Pitanje> pitanja = repository.getAllFiltered(new Pitanje(), tz.getId());
            for (Pitanje p : pitanja) {
                List<Odgovor> odgovori = repository.getAllFiltered(new Odgovor(), p.getId());
                p.setListaOdgovora(odgovori);
            }
            tz.setListaPitanja(pitanja);
        }
    }

    public List<TestZnanja> getTestoviZnanja() {
        return testoviZnanja;
    }
    
}
