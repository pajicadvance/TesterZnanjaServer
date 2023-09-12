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
 *
 * @author Pavle
 */
public class Controller {
            
    private static Controller controller;
    
    private Controller() {
    }
    
    public static Controller getInstance() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }
    
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
    
    public void zapamtiTestZnanja(TestZnanja testZnanja) throws Exception {
        AddTestZnanja operation = new AddTestZnanja();
        operation.execute(testZnanja);
    }
    
    public List<TestZnanja> ucitajListuTestovaZnanja() throws Exception {
        GetAllTestZnanja operation = new GetAllTestZnanja();
        operation.execute(new TestZnanja());
        return operation.getTestoviZnanja();
    }
    
    public void odrediRezultat(Rezultat rezultat) throws Exception {
        AddRezultat operation = new AddRezultat();
        operation.execute(rezultat);
    }
    
    public void obrisiTestZnanja(TestZnanja testZnanja) throws Exception {
        DeleteTestZnanja operation = new DeleteTestZnanja();
        operation.execute(testZnanja);
    }
    
    public void zapamtiUrednika(Urednik urednik) throws Exception {
        AddUrednik operation = new AddUrednik();
        operation.execute(urednik);
    }
    
    public List<Urednik> ucitajListuUrednika() throws Exception {
        GetAllUrednik operation = new GetAllUrednik();
        operation.execute(new Urednik());
        return operation.getUrednici();
    }
    
    public void obrisiUrednika(Urednik urednik) throws Exception {
        DeleteUrednik operation = new DeleteUrednik();
        operation.execute(urednik);
    }
    
    public List<Urednik> nadjiUrednike(String searchParameter) throws Exception {
        FindUrednik operation = new FindUrednik(searchParameter);
        operation.execute(new Urednik());
        return operation.getUrednici();
    }
    
    public List<TestZnanja> nadjiTestoveZnanja(String searchParameter) throws Exception {
        FindTestZnanja operation = new FindTestZnanja(searchParameter);
        operation.execute(new TestZnanja());
        return operation.getTestoviZnanja();
    }
    
    public void zapamtiKorisnika(Korisnik korisnik) throws Exception {
        AddKorisnik operation = new AddKorisnik();
        operation.execute(korisnik);
    }

    public List<Rezultat> nadjiRezultat(String searchParameter) throws Exception {
        FindRezultat operation = new FindRezultat(searchParameter);
        operation.execute(new Rezultat());
        return operation.getRezultati();
    }
    
}
