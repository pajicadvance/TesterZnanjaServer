/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pajic.controller;

import com.pajic.crypto.BCryptManager;
import com.pajic.model.*;
import com.pajic.operation.administrator.GetAllAdministrator;
import com.pajic.operation.korisnik.AddKorisnik;
import com.pajic.operation.korisnik.GetAllKorisnik;
import com.pajic.operation.rezultat.AddRezultat;
import com.pajic.operation.rezultat.FindRezultat;
import com.pajic.operation.test_znanja.AddTestZnanja;
import com.pajic.operation.test_znanja.DeleteTestZnanja;
import com.pajic.operation.test_znanja.FindTestZnanja;
import com.pajic.operation.test_znanja.GetAllTestZnanja;
import com.pajic.operation.urednik.AddUrednik;
import com.pajic.operation.urednik.DeleteUrednik;
import com.pajic.operation.urednik.FindUrednik;
import com.pajic.operation.urednik.GetAllUrednik;

import java.util.List;


/**
 * Predstavlja kontroler koji ima mogucnost da poziva razlicite sistemske operacije. Implementira singleton patern.
 *
 * @author Pavle Pajic
 * @since 1.0.0
 *
 */
public class Controller {

    /**
     * Jedinstvena instanca Controller klase.
     */
    private static Controller controller;

    /**
     * Privatni konstruktor koji se poziva samo jednom kako bi se kreirala jedina instanca klase Controller.
     */
    private Controller() {
    }

    /**
     * Vraca instancu kontrolera ukoliko ona postoji, ali ukoliko ne postoji kreira je.
     * @return controller - Instanca kontrolera.
     */
    public static Controller getInstance() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }

    /**
     * Pretrazuje bazu podataka kako bi se utvrdilo da li postoji korisnik sa prosledjenim pristupnim parametrima.
     * @param username - Prosledjeno korisnicko ime.
     * @param password - Prosledjena lozinka.
     * @return user / null - Pronadjeni korisnik. / Ukoliko nije pronadjen nijedan korisnik.
     * @throws Exception - Ukoliko se javi greska prilikom komunikacije sa bazom podataka.
     */
    public GenericUser login(String username, String password) throws Exception {
        GetAllKorisnik op1 = new GetAllKorisnik();
        GetAllUrednik op2 = new GetAllUrednik();
        GetAllAdministrator op3 = new GetAllAdministrator();
        op1.execute(new Korisnik());
        op2.execute(new Urednik());
        op3.execute(new Administrator());
        List<Korisnik> listKorisnik = op1.getKorisnici();
        List<Urednik> listUrednik = op2.getUrednici();
        List<Administrator> listAdministrator = op3.getAdministratori();
        for (Korisnik user : listKorisnik) if (user.getUsername().equals(username) && BCryptManager.checkPasswordMatch(password, user.getPassword())) return user;
        for (Urednik user : listUrednik) if (user.getUsername().equals(username) && BCryptManager.checkPasswordMatch(password, user.getPassword())) return user;
        for (Administrator user : listAdministrator) if (user.getUsername().equals(username) && BCryptManager.checkPasswordMatch(password, user.getPassword())) return user;
        return null;
    }

    /**
     * Dodaje prosledjeni test znanja u bazu podataka.
     * @param testZnanja - Prosledjeni test znanja.
     * @throws Exception - Ukoliko se javi greska prilikom komunikacije sa bazom podataka.
     */
    public void zapamtiTestZnanja(TestZnanja testZnanja) throws Exception {
        AddTestZnanja operation = new AddTestZnanja();
        operation.execute(testZnanja);
    }

    /**
     * Vraca listu svih testova znanja iz baze podataka.
     * @return testoviZnanja - Lista svih testova znanja iz baze podataka.
     * @throws Exception - Ukoliko se javi greska prilikom komunikacije sa bazom podataka.
     */
    public List<TestZnanja> ucitajListuTestovaZnanja() throws Exception {
        GetAllTestZnanja operation = new GetAllTestZnanja();
        operation.execute(new TestZnanja());
        return operation.getTestoviZnanja();
    }

    /**
     * Dodaje prosledjeni rezultat u bazu podataka.
     * @param rezultat - Prosledjeni rezultat.
     * @throws Exception - Ukoliko se javi greska prilikom komunikacije sa bazom podataka.
     */
    public void odrediRezultat(Rezultat rezultat) throws Exception {
        AddRezultat operation = new AddRezultat();
        operation.execute(rezultat);
    }

    /**
     * Brise prosledjeni test znanja iz baze podataka.
     * @param testZnanja - Prosledjeni test znanja.
     * @throws Exception - Ukoliko se javi greska prilikom komunikacije sa bazom podataka.
     */
    public void obrisiTestZnanja(TestZnanja testZnanja) throws Exception {
        DeleteTestZnanja operation = new DeleteTestZnanja();
        operation.execute(testZnanja);
    }

    /**
     * Dodaje prosledjenog urednika u bazu podataka.
     * @param urednik - Prosledjeni urednik.
     * @throws Exception - Ukoliko se javi greska prilikom komunikacije sa bazom podataka.
     */
    public void zapamtiUrednika(Urednik urednik) throws Exception {
        AddUrednik operation = new AddUrednik();
        operation.execute(urednik);
    }

    /**
     * Vraca listu svih urednika iz baze podataka.
     * @return urednici - Lista svih urednika iz baze podataka.
     * @throws Exception - Ukoliko se javi greska prilikom komunikacije sa bazom podataka.
     */
    public List<Urednik> ucitajListuUrednika() throws Exception {
        GetAllUrednik operation = new GetAllUrednik();
        operation.execute(new Urednik());
        return operation.getUrednici();
    }

    /**
     * Brise prosledjenog urednika iz baze podataka.
     * @param urednik - Prosledjeni urednik.
     * @throws Exception - Ukoliko se javi greska prilikom komunikacije sa bazom podataka.
     */
    public void obrisiUrednika(Urednik urednik) throws Exception {
        DeleteUrednik operation = new DeleteUrednik();
        operation.execute(urednik);
    }

    /**
     * Vraca listu pronadjenih urednika iz baze podataka.
     * @return urednici - Lista pronadjenih urednika iz baze podataka.
     * @throws Exception - Ukoliko se javi greska prilikom komunikacije sa bazom podataka.
     */
    public List<Urednik> nadjiUrednike(String searchParameter) throws Exception {
        FindUrednik operation = new FindUrednik(searchParameter);
        operation.execute(new Urednik());
        return operation.getUrednici();
    }

    /**
     * Vraca listu pronadjenih testova znanja iz baze podataka.
     * @return testoviZnanja - Lista pronadjenih testova znanja iz baze podataka.
     * @throws Exception - Ukoliko se javi greska prilikom komunikacije sa bazom podataka.
     */
    public List<TestZnanja> nadjiTestoveZnanja(String searchParameter) throws Exception {
        FindTestZnanja operation = new FindTestZnanja(searchParameter);
        operation.execute(new TestZnanja());
        return operation.getTestoviZnanja();
    }

    /**
     * Dodaje prosledjenog korisnika u bazu podataka.
     * @param korisnik - Prosledjeni korisnik.
     * @throws Exception - Ukoliko se javi greska prilikom komunikacije sa bazom podataka.
     */
    public void zapamtiKorisnika(Korisnik korisnik) throws Exception {
        AddKorisnik operation = new AddKorisnik();
        operation.execute(korisnik);
    }

    /**
     * Vraca listu pronadjenih rezultata znanja iz baze podataka.
     * @return rezultati - Lista pronadjenih rezultata iz baze podataka.
     * @throws Exception - Ukoliko se javi greska prilikom komunikacije sa bazom podataka.
     */
    public List<Rezultat> nadjiRezultat(String searchParameter) throws Exception {
        FindRezultat operation = new FindRezultat(searchParameter);
        operation.execute(new Rezultat());
        return operation.getRezultati();
    }
    
}
