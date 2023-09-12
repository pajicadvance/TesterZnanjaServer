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
 * Predstavlja specificnu operaciju koja pretrazuje sve testove znanja po datom parametru i stavlja ih u listu.
 *
 * Sadrzi listu testova znanja i parametar za pretragu.
 *
 * @author Pavle Pajic
 * @since 1.0.0
 */
public class FindTestZnanja extends AbstractGenericOperation {

    /**
     * Lista testova znanja.
     */
    private List<TestZnanja> testoviZnanja;
    /**
     * Parametar za pretragu.
     */
    private final String searchParameter;

    /**
     * Konstruktor koji vraca instancu klase FindTestZnanja sa prosledjenim parametrom za pretragu.
     * @param searchParameter - Parametar za pretragu.
     *
     * @throws NullPointerException - Ukoliko je parametar za pretragu null.
     */
    public FindTestZnanja(String searchParameter) {
        if(searchParameter == null)
            throw new NullPointerException("Parametar za pretragu ne sme biti null.");

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

    /**
     * Vraca listu testova znanja.
     * @return testoviZnanja - Lista testova znanja.
     */
    public List<TestZnanja> getTestoviZnanja() {
        return testoviZnanja;
    }
    
}
