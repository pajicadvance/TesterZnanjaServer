/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pajic.operation.korisnik;

import com.pajic.model.Korisnik;
import com.pajic.operation.AbstractGenericOperation;

import java.util.List;


/**
 * Predstavlja specificnu operaciju koja iz baze podataka izvlaci sve korisnike i stavlja ih u listu.
 *
 * Sadrzi listu korisnika.
 *
 * @author Pavle Pajic
 * @since 1.0.0
 */
public class GetAllKorisnik extends AbstractGenericOperation {

    /**
     * Lista korisnika.
     */
    private List<Korisnik> korisnici;

    @Override
    protected void preconditions(Object param) throws Exception {
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        korisnici = repository.getAll((Korisnik) param);
    }

    /**
     * Vraca listu korisnika.
     * @return korisnici - Lista korisnika.
     */
    public List<Korisnik> getKorisnici() {
        return korisnici;
    }
    
}
