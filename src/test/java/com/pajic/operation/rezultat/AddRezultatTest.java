package com.pajic.operation.rezultat;

import com.pajic.controller.Controller;
import com.pajic.crypto.BCryptManager;
import com.pajic.model.Korisnik;
import com.pajic.model.Rezultat;
import com.pajic.model.TestZnanja;
import com.pajic.model.TipTestaZnanja;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AddRezultatTest {

    @Test
    void testExecuteOperation() throws Exception {
        Korisnik korisnik = new Korisnik();
        korisnik.setId(1001L);
        korisnik.setIme("TestIme");
        korisnik.setPrezime("TestPrezime");
        korisnik.setUsername("testuser");
        korisnik.setPassword(BCryptManager.passwordToHash("testpass"));
        korisnik.setDatumRodjenja(LocalDate.of(2020, 1, 8));
        Controller.getInstance().zapamtiKorisnika(korisnik);
        TestZnanja testZnanja = new TestZnanja();
        testZnanja.setId(1000L);
        testZnanja.setNaziv("TestTest");
        testZnanja.setPoeniZaProlaz(10);
        testZnanja.setTipTestaZnanja(new TipTestaZnanja(1L, "Kviz"));
        testZnanja.setListaPitanja(new ArrayList<>());
        Controller.getInstance().zapamtiTestZnanja(testZnanja);
        Rezultat rezultat = new Rezultat();
        rezultat.setKorisnik(korisnik);
        rezultat.setTestZnanja(testZnanja);
        rezultat.setId(1000L);
        rezultat.setProsao(false);
        rezultat.setBrojPoena(3);
        rezultat.setDatumRada(LocalDate.of(2023, 3, 1));
        Controller.getInstance().odrediRezultat(rezultat);
        String searchParameter = " WHERE korisnik_id=" + korisnik.getId() + " AND test_znanja_id=" + testZnanja.getId();
        List<Rezultat> rezultati = Controller.getInstance().nadjiRezultat(searchParameter);
        boolean containsRezultat = !rezultati.isEmpty();
        Assertions.assertTrue(containsRezultat);
    }
}
