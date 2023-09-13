package com.pajic.operation.korisnik;

import com.pajic.controller.Controller;
import com.pajic.crypto.BCryptManager;
import com.pajic.model.Korisnik;
import com.pajic.repository.RepositoryDBGeneric;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class AddKorisnikTest {

    @Test
    void testExecuteOperation() throws Exception {
        Korisnik korisnik = new Korisnik();
        korisnik.setId(1000L);
        korisnik.setIme("TestIme");
        korisnik.setPrezime("TestPrezime");
        korisnik.setUsername("testuser");
        korisnik.setPassword(BCryptManager.passwordToHash("testpass"));
        korisnik.setDatumRodjenja(LocalDate.of(2020, 1, 8));
        Controller.getInstance().zapamtiKorisnika(korisnik);
        GetAllKorisnik operation = new GetAllKorisnik();
        operation.execute(korisnik);
        List<Korisnik> korisnici = operation.getKorisnici();
        boolean containsKorisnik = false;
        for (Korisnik k : korisnici)
            if (k.getId() == korisnik.getId()) {
                containsKorisnik = true;
                break;
            }
        Assertions.assertTrue(containsKorisnik);
    }
}
