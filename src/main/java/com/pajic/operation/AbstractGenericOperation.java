/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pajic.operation;

import com.pajic.repository.DbRepository;
import com.pajic.repository.Repository;
import com.pajic.repository.RepositoryDBGeneric;

/**
 * Predstavlja specifikaciju koja naznacava kako bi svaka operacija u okviru aplikacije trebala da se odvija.
 *
 * @author Pavle Pajic
 * @since 1.0.0
 *
 */
public abstract class AbstractGenericOperation {

    /**
     * Repozitorijum koji ce biti na raspolaganju operaciji da komunicira sa bazom podataka i izvrsi neophodne akcije.
     */
    protected final Repository repository;

    /**
     * Konstruktor koji postavlja da atribut repository za sve operacije koje nasledjuju ovu klasu bude RepositoryDbGeneric
     */
    public AbstractGenericOperation() {
        this.repository = new RepositoryDBGeneric();
    }

    /**
     * Predstavlja tok izvrsenja svake operacije koja nasledjuje ovu klasu.
     *
     * Ne moze se menjati u nasledjenim klasama.
     * @param param - Objekat koji ce biti od odredjenog znacaja za izvrsenje razlicitih operacija.
     * @throws Exception - U slucaju da se desi odredjena greska prilikom izvrsenja operacije, provere preduslova za izvrsenje, uspostavljanja konekcije ili commit-ovanja transakcije. Ukoliko se to desi izvrsava se rollback transakcije.
     */
    public final void execute(Object param) throws Exception {
        try {
            preconditions(param);
            startTransaction();
            executeOperation(param);
            commitTransaction();
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            rollbackTransaction();
            throw ex;
        } finally {
            disconnect();
        }
    }

    /**
     * Akcije koje se moraju izvrsiti pre same operacije.
     * @param param - Objekat od znacaja za izvrsenje preduslova.
     * @throws Exception - Ukoliko dodje do greske prilikom izvrsenja preduslova.
     */
    protected abstract void preconditions(Object param) throws Exception;

    /**
     * Poziva metodu connect() od repozitorijuma.
     * @throws Exception - Ukoliko dodje do greske prilikom ostavarivanja konekcije sa bazom.
     */
    private void startTransaction() throws Exception {
        ((DbRepository) repository).connect();
    }

    /**
     * Izvrsava naredbe vezane za specificnu operaciju.
     * @param param - Objekat od znacaja za izvrsenje operacije.
     * @throws Exception - Ukoliko dodje do greske prilikom izvrsavanja operacije.
     */
    protected abstract void executeOperation(Object param) throws Exception;

    /**
     * Poziva metodu commit() od repozitorijuma.
     * @throws Exception - Ukoliko dodje do greske prilikom commit-ovanja transakcije.
     */
    private void commitTransaction() throws Exception {
        ((DbRepository) repository).commit();
    }

    /**
     * Poziva metodu rollback() od repozitorijuma.
     * @throws Exception - Ukoliko dodje do greske prilikom rollback-ovanja transakcije.
     */
    private void rollbackTransaction() throws Exception {
        ((DbRepository) repository).rollback();
    }

    /**
     * Poziva metodu disconnect() od repozitorijuma.
     * @throws Exception - Ukoliko dodje do greske prilikom prekidanja veze sa bazom podataka.
     */
    private void disconnect() throws Exception {
        ((DbRepository) repository).disconnect();
    }

}
