/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pajic.operation.rezultat;

import com.pajic.model.Rezultat;
import com.pajic.operation.AbstractGenericOperation;

import java.util.List;


/**
 * Predstavlja specificnu operaciju koja pretrazuje sve rezultate po datom parametru i stavlja ih u listu.
 *
 * Sadrzi listu rezultata i parametar za pretragu.
 *
 * @author Pavle Pajic
 * @since 1.0.0
 */
public class FindRezultat extends AbstractGenericOperation {

    /**
     * Lista rezultata.
     */
    private List<Rezultat> rezultati;
    /**
     * Parametar za pretragu.
     */
    private final String searchParameter;

    /**
     * Konstruktor koji vraca instancu klase FindRezultat sa prosledjenim parametrom za pretragu.
     * @param searchParameter - Parametar za pretragu.
     *
     * @throws NullPointerException - Ukoliko je parametar za pretragu null.
     */
    public FindRezultat(String searchParameter) {
        if(searchParameter == null)
            throw new NullPointerException("Parametar za pretragu ne sme biti null.");

        this.searchParameter = searchParameter;
    }
    
    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        rezultati = repository.findAll((Rezultat) param, searchParameter);
    }

    /**
     * Vraca listu rezultata.
     * @return rezultati - Lista rezultata.
     */
    public List<Rezultat> getRezultati() {
        return rezultati;
    }
}
