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
 * Predstavlja specificnu operaciju koja iz baze podataka izvlaci sve testove znanja i stavlja ih u listu.
 *
 * Sadrzi listu testova znanja.
 *
 * @author Pavle Pajic
 * @since 1.0.0
 */
public class GetAllTestZnanja extends AbstractGenericOperation {

    /**
     * Lista testova znanja.
     */
    private List<TestZnanja> testoviZnanja;
    
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        testoviZnanja = repository.getAll((TestZnanja) param);
        for (TestZnanja tz : testoviZnanja) {
            List<Pitanje> pitanja = repository.getAllFiltered(new Pitanje(), tz.getId());
            for (Pitanje p : pitanja) {
                p.setTestZnanja(tz);
                List<Odgovor> odgovori = repository.getAllFiltered(new Odgovor(), p.getId());
                for (Odgovor o : odgovori) {
                    o.setPitanje(p);
                    o.setTestZnanja(tz);
                }
                p.setListaOdgovora(odgovori);
            }
            tz.setListaPitanja(pitanja);
        }
    }

    /**
     * Vraca listu testova znanja.
     * @return testoviZnanja - Lista testova znanja.
     */
    public List<TestZnanja> getTestoviZnanja() {
        return testoviZnanja;
    }
    
}
